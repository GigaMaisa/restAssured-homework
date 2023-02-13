package ge.tbc.testautomation.task6;

import ge.tbc.testautomation.task6.data.dataprovider.ObjectDataProvider;
import ge.tbc.testautomation.task6.pojos.request.UpdateBookingRequest;
import ge.tbc.testautomation.task6.pojos.response.UpdateBookingResponse;
import ge.tbc.testautomation.task6.steps.GetTokenResponse;
import ge.tbc.testautomation.task6.steps.UpdateBookingSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task6Test {
    GetTokenResponse getTokenResponse;
    UpdateBookingSteps updateBookingSteps;

    @BeforeClass
    public void setUp(){
        getTokenResponse = new GetTokenResponse();
        updateBookingSteps = new UpdateBookingSteps();
    }

    @Test(dataProviderClass = ObjectDataProvider.class, dataProvider = "requestObjectsProvider")
    public void runFirstTest(UpdateBookingRequest bookingRequest){
        String token = getTokenResponse.getTokenFromApi();
        UpdateBookingResponse updateBooking = updateBookingSteps.updateBookingApiCall(token, bookingRequest);
        updateBookingSteps.validateFirstName(updateBooking).validateAdditionalNeed(updateBooking);
    }
}
