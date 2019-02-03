import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RequestHandler {

    static Instant dateOfLastRequest = null;

    void handleNotMoreOneRequestPerDay(ARequest request) throws TooManyRequestsException {
        // The problem, if we want to test a request the next day, we must wait 24 hours
        // We must fix this with Dependency Injection methodology
        // (pass the dependency from Constructor on Class or as Parameter on method)
        Instant now = Instant.now();


        if (dateOfLastRequest != null) {
            Instant previousDate = now.minus(24, ChronoUnit.HOURS);

            if (previousDate.isBefore(dateOfLastRequest)) {
                throw new TooManyRequestsException();
            }
        }

        dateOfLastRequest = now;
        // handle request ...
    }
}

class ARequest {
    // A Request class
}

class TooManyRequestsException extends Exception {
    // Our Custom Exception
}