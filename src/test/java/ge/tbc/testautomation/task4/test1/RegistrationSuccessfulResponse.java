package ge.tbc.testautomation.task4.test1;

public class RegistrationSuccessfulResponse {
    private int id;
    private String token;

    public int getId() {
        return id;
    }


    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "RegistrationSuccessfulResponse{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
