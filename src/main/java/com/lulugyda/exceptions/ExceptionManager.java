package com.lulugyda.exceptions;

import com.lulugyda.exceptions.models.ErrorCode;
import com.lulugyda.utils.Constants;
import com.twilio.exception.TwilioException;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.transaction.exceptions.CannotCreateTransactionException;
import jakarta.validation.ConstraintViolationException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
@NoArgsConstructor
public class ExceptionManager {


    public static void handleException(Exception exception) {
        if (exception instanceof CannotCreateTransactionException) {
            throw new MovieException(ErrorCode.CANNOT_CONNECT_TO_DB.getId(),
                    Constants.LOST_CONNECTION_TO_DATABASE);
        }
        if (exception instanceof SQLException) {
            throw new MovieException(ErrorCode.DB_OPERATION_NOT_PERMITTED.getId(),
                    Constants.DATABASE_OPERATION_COULD_NOT_BE_PERFORMED);
        }
        if (exception instanceof DataAccessException) {
            throw new MovieException(ErrorCode.DB_CONSTRAINT_VIOLATED.getId(),
                    Constants.ONE_OF_THE_DATABASE_CONSTRAINT_WAS_VIOLATED);
        }
        if (exception instanceof UserNotFoundException) {
            throw new UserNotFoundException();
        }
        if (exception instanceof ConstraintViolationException) {
            throw new ConstraintViolationException(((ConstraintViolationException) exception).getConstraintViolations());
        }
        if (exception instanceof InvalidUsernameOrPasswordException) {
            throw new MovieException(ErrorCode.INVALID_USERNAME_OR_PASSWORD.getId(),
                    ErrorCode.INVALID_USERNAME_OR_PASSWORD.getMessage());
        }
        if (exception instanceof UsernameAlreadyExistException) {
            throw new MovieException(ErrorCode.USERNAME_EXIST.getId(),
                    ErrorCode.USERNAME_EXIST.getMessage());
        }
        if (exception instanceof TwilioException) {
            throw new MovieException(ErrorCode.TWILIO.getId(),
                    ErrorCode.TWILIO.getMessage() + ":" + exception.getMessage());
        }

        throw new MovieException(ErrorCode.INTERNAL_SERVER_ERROR.getId(),
               Constants.AN_EXCEPTION_OCCURRED_TRYING_TO_COMMUNICATE_WITH_THE_DATABASE);
    }
}