package ge.tbc.testautomation.task4.test2;

public class CreateUserParameters {
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public CreateUserParameters(String name, String job){
        this.name = name;
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
