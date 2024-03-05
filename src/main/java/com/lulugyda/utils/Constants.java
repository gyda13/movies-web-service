package com.lulugyda.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Constants {

    public static final ObjectMapper objectMapper = new ObjectMapper();


    public static final String HEADER_X_CORRELATION_ID = "x-correlation-id";

    public static final String DATABASE_OPERATION_COULD_NOT_BE_PERFORMED = "Database operation could not be performed";

    public static final String LOST_CONNECTION_TO_DATABASE = "Lost connection to database Failed to "
            + "communicate with database";

    public static final String ONE_OF_THE_DATABASE_CONSTRAINT_WAS_VIOLATED = "One of the database "
            + "constraint was violated";

    public static final String AN_EXCEPTION_OCCURRED_TRYING_TO_COMMUNICATE_WITH_THE_DATABASE =  "An exception "
            + "occurred while trying to communicate with the database";

}
