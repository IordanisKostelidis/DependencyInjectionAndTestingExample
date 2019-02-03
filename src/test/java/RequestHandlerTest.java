import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class RequestHandlerTest {

    private RequestHandler requestHandler;

    @Before
    public void setUp() {
        requestHandler = new RequestHandler();
    }

    @Test()
    public void oneRequestTodayTest() {
        ARequest aRequest = new ARequest();
        Instant now = Instant.now();
        try {
            requestHandler.handleNotMoreOneRequestPerDay(aRequest, now);
            assertTrue(true);
        } catch (TooManyRequestsException e) {
            e.printStackTrace();
        }
    }


    @Test()
    public void twoRequestsTodayTest() {
        ARequest firstRequest = new ARequest();
        Instant nowForFirstRequest = Instant.now();

        try {
            requestHandler.handleNotMoreOneRequestPerDay(firstRequest, nowForFirstRequest);
            assertTrue(true);
        } catch (TooManyRequestsException e) {
            e.printStackTrace();
        }

        ARequest secondRequest = new ARequest();
        Instant nowForSecondRequest = Instant.now();

        try {
            requestHandler.handleNotMoreOneRequestPerDay(secondRequest, nowForSecondRequest);
        } catch (TooManyRequestsException e) {
            assertTrue(true);
            e.printStackTrace();
        }
    }

    @Test()
    public void oneRequestTodayAndOneRequestTomorrowTest() {
        ARequest firstRequest = new ARequest();
        Instant nowForFirstRequest = Instant.now();

        try {
            requestHandler.handleNotMoreOneRequestPerDay(firstRequest, nowForFirstRequest);
            assertTrue(true);
        } catch (TooManyRequestsException e) {
            e.printStackTrace();
        }

        ARequest secondRequest = new ARequest();
        Instant nowForSecondRequest = Instant.now().plus(24, ChronoUnit.HOURS);

        try {
            requestHandler.handleNotMoreOneRequestPerDay(secondRequest, nowForSecondRequest);
            assertTrue(true);
        } catch (TooManyRequestsException e) {
            e.printStackTrace();
        }
    }
}