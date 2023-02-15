package ge.tbc.testautomation.task7;

import ge.tbc.testautomation.task7.models.response.AddUserFullResponse;
import ge.tbc.testautomation.task7.models.response.GenerateTokenResponse;
import ge.tbc.testautomation.task7.steps.IsAuthorizedSteps;
import ge.tbc.testautomation.task7.steps.AddUserSteps;
import ge.tbc.testautomation.task7.steps.GenerateTokenSteps;
import org.testng.annotations.Test;

public class Task7Test {
    AddUserSteps addUserSteps = new AddUserSteps();
    GenerateTokenSteps generateTokenSteps = new GenerateTokenSteps();
    IsAuthorizedSteps isAuthorizedSteps = new IsAuthorizedSteps();

    @Test(priority = 1)
    public void creatingNewUserTest(){
        AddUserFullResponse createdUser = addUserSteps.getAddUserResponse();
        addUserSteps.validateBooksList(createdUser);
    }

    @Test(dependsOnMethods = "creatingNewUserTest", priority = 2)
    public void tokenGenerationTest(){
        GenerateTokenResponse tokenResponse = generateTokenSteps.generateToken();
        generateTokenSteps.validateResponseStatus(tokenResponse).validateResponseResult(tokenResponse);
    }

    @Test(dependsOnMethods = {"creatingNewUserTest", "tokenGenerationTest"}, priority = 3)
    public void isUserAuthorizedTest(){
        Boolean isUserAuthorized = isAuthorizedSteps.isUserAuthorized();
        isAuthorizedSteps.validateUserIsAuthorized(isUserAuthorized);
    }
}
