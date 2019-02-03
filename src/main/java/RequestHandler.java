import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RequestHandler {
    static Instant dateOfLastRequest = null;
    void handleNotMoreOneRequestPerDay(ARequest request) throws
            TooManyRequestsException();
    {
        Instant now = Instant.now();

        if (dateOfLastRequest != null) {
            Instant previousDate = now.minus(24, ChronoUnit.HOURS);
            if (previousDate.isBefore(dateOfLastRequest)) {
                throw TooManyRequestsException();
            }
        }

        dateOfLastRequest = now;
        // handle request ...
    }
}