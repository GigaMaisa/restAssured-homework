package ge.tbc.testautomation.task1;

public class DataProvider {
    @org.testng.annotations.DataProvider
    public static Object[][] dataProviderMethod(){
        return new Object[][]{
                {1, "USA"},
                {5, "Hungary"}
        };
    }
}
