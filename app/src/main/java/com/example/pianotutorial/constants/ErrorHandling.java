package com.example.pianotutorial.constants;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;

public class ErrorHandling {
    private static final String TAG = "ErrorHandling";

    public static <T> void httpErrorHandler(Response<T> response, Context context, Runnable onSuccess) {
        if (response.isSuccessful()) {
            onSuccess.run();
        } else {
            try {
                if (response.errorBody() != null) {
                    String errorBody = response.errorBody().string();
                    if (response.code() >= 400 && response.code() < 500) {
                        handleClientError(context, errorBody);
                    } else if (response.code() >= 500 && response.code() < 600) {
                        handleServerError(context, errorBody);
                    } else {
                        handleOtherErrors(context, errorBody);
                    }
                } else {
                    handleUnknownError(context);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException while handling error response: " + e.getMessage());
                handleUnknownError(context);
            } catch (JsonSyntaxException e) {
                Log.e(TAG, "JsonSyntaxException while parsing error response: " + e.getMessage());
                handleUnknownError(context);
            }
        }
    }

    private static void handleClientError(Context context, String errorBody) {
        try {
            MsgResponse msgResponse = new Gson().fromJson(errorBody, MsgResponse.class);
            toast(context, msgResponse.getMsg());
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "JsonSyntaxException while parsing client error response: " + e.getMessage());
            toast(context, "Client error occurred");
        }
    }

    private static void handleServerError(Context context, String errorBody) {
        try {
            ErrorResponse errorResponse = new Gson().fromJson(errorBody, ErrorResponse.class);
            toast(context, errorResponse.getError());
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "JsonSyntaxException while parsing server error response: " + e.getMessage());
            toast(context, "Server error occurred");
        }
    }

    private static void handleOtherErrors(Context context, String errorBody) {
        toast(context, errorBody); // Handle other specific errors
    }

    private static void handleUnknownError(Context context) {
        toast(context, "Unknown error occurred"); // Handle unknown errors
    }

    private static void toast(Context context, String message) {
        // Implement your toast message handling here
        // Example: Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

class MsgResponse {
    private final String msg;

    public MsgResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

class ErrorResponse {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
