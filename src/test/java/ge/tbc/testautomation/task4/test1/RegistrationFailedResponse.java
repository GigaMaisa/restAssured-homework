package ge.tbc.testautomation.task4.test1;

public class RegistrationFailedResponse {
    private String error;

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "RegistrationFailedResponse{" +
                "error='" + error + '\'' +
                '}';
    }
}
