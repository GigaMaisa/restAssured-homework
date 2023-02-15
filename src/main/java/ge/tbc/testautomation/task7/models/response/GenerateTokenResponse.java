package ge.tbc.testautomation.task7.models.response;

import java.util.Date;

public record GenerateTokenResponse(String token, Date expires, String status, String result) {
}
