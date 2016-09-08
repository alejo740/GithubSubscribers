package com.github.globant.githubsubscribers.commons.utils;

import com.github.globant.githubsubscribers.R;

/**
 * Helper class to manage error messages
 *
 * @author edwin.cobos
 * @since 05/09/2016
 */
public class ErrorMessagesHelper {

    /**
     * List of types errors
     */
    public enum TypeError {
        NO_CONNECTION,
        BAD_ANSWER,
        REQUEST_CANCELLED
    }

    /**
     * Static method to get a error message according with the type error
     *
     * @param error
     * @return
     */
    public static int getMessage(TypeError error) {
        int messageId;
        switch (error) {
            case BAD_ANSWER:
                messageId = R.string.api_client_error_bad_answer;
                break;
            case REQUEST_CANCELLED:
                messageId = R.string.api_client_error_request_cancelled;
                break;
            case NO_CONNECTION:
            default:
                messageId = R.string.api_client_error_no_connection;
                break;
        }
        return messageId;
    }
}
