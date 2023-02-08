package ge.tbc.testautomation.task4.test2;

import java.util.Date;

public class CreatedUserResponse {
    private String name;
    private String job;
    private int id;
    private Date createdAt;

    @Override
    public String toString() {
        return "CreatedUserResponse{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", id=" + id +
                ", createdAt=" + createdAt +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
