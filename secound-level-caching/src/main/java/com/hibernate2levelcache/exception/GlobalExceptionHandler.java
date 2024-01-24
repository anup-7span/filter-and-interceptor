package com.hibernate2levelcache.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.hibernate2levelcache.response.Response;
import javassist.NotFoundException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({JDBCConnectionException.class})
    public Response<String> jdbcConnectionException(JDBCConnectionException e) {
        LOGGER.error(e.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Response<String> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error(e.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public Response<String> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        LOGGER.error(e.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler({MismatchedInputException.class})
    public Response<String> mismatchedInputException(MismatchedInputException e) {
        LOGGER.error(e.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler({ChangeSetPersister.NotFoundException.class})
    public Response<String> notFoundException(NotFoundException e) {
        LOGGER.error(e.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response<String> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
        LOGGER.error(ex.getLocalizedMessage());
        LOGGER.error(errorMsg);
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), errorMsg);
    }

    @ExceptionHandler({BadDataException.class})
    public Response<String> noDataFoundException(BadDataException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({NoDataFoundException.class})
    public Response<String> noDataFoundException(NoDataFoundException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.NO_CONTENT.value(), ex.getMessage());
    }

    @ExceptionHandler({CommonException.class})
    public Response<String> commonException(CommonException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({FileNotFoundException.class})
    public Response<String> fileNotFoundException(FileNotFoundException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler({ArrayIndexOutOfBoundsException.class})
    public Response<String> arrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({ClassCastException.class})
    public Response<String> classCastException(ClassCastException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public Response<String> illegalArgumentException(IllegalArgumentException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({NullPointerException.class})
    public Response<String> nullPointerException(NullPointerException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({NumberFormatException.class})
    public Response<String> numberFormatException(NumberFormatException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({AssertionError.class})
    public Response<String> assertionError(AssertionError ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({ExceptionInInitializerError.class})
    public Response<String> exceptionInInitializerError(ExceptionInInitializerError ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({StackOverflowError.class})
    public Response<String> stackOverflowError(StackOverflowError ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({NoClassDefFoundError.class})
    public Response<String> noClassDefFoundError(NoClassDefFoundError ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public Response<String> resourceNotFoundException(RuntimeException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Response<String> resourceNotFoundException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Set<String> messages = new HashSet<>(constraintViolations.size());
        messages.addAll((Collection<? extends String>)constraintViolations.stream()
                .map(constraintViolation -> String.format("%s value '%s' %s", new Object[] { constraintViolation.getPropertyPath(), constraintViolation.getInvalidValue(), constraintViolation.getMessage() })).collect(Collectors.toList()));
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), "sdsd");
    }

    @ExceptionHandler({WebExchangeBindException.class})
    public Response<String> webExchangeBindException(WebExchangeBindException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.BAD_REQUEST.value(), ex.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({Exception.class})
    public Response<String> exception(Exception ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({ValidationException.class})
    public Response<String> validationException(ValidationException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({UserUnAuthorizedException.class})
    public Response<String> userUnAuthorizedException(UserUnAuthorizedException ex) {
        LOGGER.error(ex.getLocalizedMessage());
        return setStatusAndMessage(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    private Response<String> setStatusAndMessage(int status, String message) {
        Response<String> reponseMap = new Response();
        reponseMap.setStatus(Integer.valueOf(status));
        reponseMap.setData("");
        reponseMap.setMessage(message);
        return reponseMap;
    }
}
