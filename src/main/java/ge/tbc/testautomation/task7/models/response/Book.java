package ge.tbc.testautomation.task7.models.response;

import java.util.Date;

public record Book(String isbn, String title, String subTitle, String author,
                   Date publish_date, String publisher, int pages, String description,String website) {
}
