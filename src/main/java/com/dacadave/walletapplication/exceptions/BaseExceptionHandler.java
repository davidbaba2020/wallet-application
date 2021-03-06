package com.dacadave.walletapplication.exceptions;

import com.dacadave.walletapplication.responses.ApiResponse;
import com.dacadave.walletapplication.responses.HttpResponse;
import com.dacadave.walletapplication.responses.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.dacadave.walletapplication.global_constants.Constants.ACCOUNT_NOT_ACTIVATED;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler
{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData handleValidationExceptions(MethodArgumentNotValidException ex)
    {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                {
                    if (errors.containsKey(error.getField()))
                    {
                        errors.put(error.getField(), String.format("%s, %s", errors.get(error.getField()), error.getDefaultMessage()));
                    }
                    else
                    {
                        errors.put(error.getField(), error.getDefaultMessage());
                    }
                }
        );
        return new ResponseData(new ApiResponse(errors, "VALIDATION_FAILED"));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseData> handleBadRequestException (BadRequestException exception)
    {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UserWithEmailNotFound.class)
    public ResponseEntity<ResponseData> handleUserWithEmailNotFoundException (UserWithEmailNotFound exception)
    {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(PinMustBeDifferentFromInitialPinException.class)
    public ResponseEntity<ResponseData> handleBadRequestException (PinMustBeDifferentFromInitialPinException exception)
    {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ResponseData> notFoundException(NoResultException exception)
    {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseData> iOException(IOException exception)
    {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    @ExceptionHandler(AccountIsNotActivatedException.class)
    public ResponseEntity<ResponseData> AccountIsNotActivatedException()
    {
        return createHttpResponse(HttpStatus.FORBIDDEN, ACCOUNT_NOT_ACTIVATED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseData> UserAlreadyExistsException(UserAlreadyExistsException e)
    {
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(WalletAccountWithAccountNumberNotFound.class)
    public ResponseEntity<ResponseData> WalletAccountWithAccountNumberNotFound(WalletAccountWithAccountNumberNotFound e)
    {
        return createHttpResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseData> unauthorizedException()
    {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseData> resourceNotFoundException(ResourceNotFoundException exception)
    {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }


    @ExceptionHandler(RoleAlreadyExistExceptions.class)
    public ResponseEntity<ResponseData> RoleAlreadyExist(RoleAlreadyExistExceptions e)
    {
        return createHttpResponse(HttpStatus.ALREADY_REPORTED, e.getMessage());
    }


    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ResponseData> RoleNotFoundException(RoleNotFoundException e)
    {
        return createHttpResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(WrongActivationException.class)
    public ResponseEntity<ResponseData> WrongActivationException(WrongActivationException e)
    {
        return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }


    private ResponseEntity<ResponseData> createHttpResponse(HttpStatus httpStatus, String message)
    {
        HttpResponse httpResponse = new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message);
        return new ResponseEntity<>(new ResponseData(httpResponse), httpStatus);
    }
}
