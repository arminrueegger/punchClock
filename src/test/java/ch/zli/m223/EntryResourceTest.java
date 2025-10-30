package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

  @Test
  public void testCreateEntryEndpoint() {
    String json = "{"
      + "\"checkIn\": \"2025-10-30T09:00:00\","
      + "\"checkOut\": \"2025-10-30T11:00:00\","
      + "\"category\": { \"id\": 1 },"
      + "\"tag\": { \"id\": 1 }"
      + "}";

    given()
      .header("Content-Type", "application/json")
      .body(json)
      .when()
      .post("/entries")
      .then()
      .statusCode(anyOf(is(200), is(201)))
      .body("id", notNullValue())
      .body("checkIn", notNullValue())
      .body("checkOut", notNullValue());
  }
}
