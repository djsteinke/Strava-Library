package rn5.djs.stravalib.exception;

public class StravaUnauthorizedException extends RuntimeException {
    private static final String message = "401:Unauthorized";
    public StravaUnauthorizedException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
