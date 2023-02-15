package ge.tbc.testautomation.task7.data;

import ge.tbc.testautomation.task7.utils.GenerateRandomString;

public class StaticData {
    public static final String USER_NAME = GenerateRandomString.generateString();
    public static final String PASSWORD = "Giga_TBC$2023";
    public static final String EXPECTED_TOKEN_RESULT = "User authorized successfully.";
    public static final String EXPECTED_TOKEN_STATUS = "Success";
}
