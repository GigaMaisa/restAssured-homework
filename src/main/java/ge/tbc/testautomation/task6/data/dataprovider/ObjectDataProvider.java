package ge.tbc.testautomation.task6.data.dataprovider;

import ge.tbc.testautomation.task6.pojos.BookingDates;
import ge.tbc.testautomation.task6.pojos.request.UpdateBookingRequest;
import org.testng.annotations.DataProvider;

public class ObjectDataProvider {
    @DataProvider
    public static Object[][] requestObjectsProvider() {
        return new Object[][]{
                {new UpdateBookingRequest("Charles", "Leclerc", 16, false, new BookingDates("2019-12-01", "2023-01-01"), "new strategist", 0, null)},
                {new UpdateBookingRequest("Lewis", "Hamilton", 44, true, new BookingDates("2007-10-01", "2023-01-02"), "8th title", 7, null)},
        };
    }
}
