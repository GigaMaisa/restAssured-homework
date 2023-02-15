package ge.tbc.testautomation.task7.models.response;

import java.util.ArrayList;

public record AddUserFullResponse(String userID, String username, ArrayList<Book> books) {
}
