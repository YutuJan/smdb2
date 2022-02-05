package gr.sae.smdb2.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.sae.bootstrap.PeopleCreatorRunner;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIntegrationTest {
    @Autowired
    private PeopleCreatorRunner peopleCreatorRunner;
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate;
    private HttpHeaders headers;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        peopleCreatorRunner.createBunchOfPeople();
    }

    @Test
    public void ensureCreatePerson() throws Exception {
        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("firstName", "Aram Emmanouil");
        personJsonObject.put("lastName", "Sirinian");
        personJsonObject.put("birthDate", "1995-02-24");
        personJsonObject.put("birthPlace", "Alexandroupoli, Greece");
        personJsonObject.put("biography", "Buddha");
        personJsonObject.put("occupations", new JSONArray());

        HttpEntity<JSONObject> entity = new HttpEntity<>(personJsonObject, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/people"), HttpMethod.POST, entity, String.class);
        JsonNode responseBody = objectMapper.readTree(response.getBody());

        assertAll("Ensure that the person is created right.",
                () -> assertTrue(responseBody.get("apiError").isNull(),
                        "apiError in response body must be \"null\"."),
                () -> assertEquals("21", responseBody.get("data").get("id").asText(),
                        "id in response body must be \"1\"."),
                () -> assertEquals("Aram Emmanouil", responseBody.get("data").get("firstName").asText(),
                        "firstName in response body must be \"Aram Emmanouil\"."),
                () -> assertEquals("Sirinian", responseBody.get("data").get("lastName").asText(),
                        "lastName in response body must be \"Sirinian\"."),
                () -> assertEquals("1995-02-24", responseBody.get("data").get("birthDate").asText(),
                        "birthDate in response body must be \"1995-02-24\"."),
                () -> assertEquals("Alexandroupoli, Greece", responseBody.get("data").get("birthPlace").asText(),
                        "birthPlace in response body must be \"Alexandroupoli, Greece\"."),
                () -> assertEquals("Buddha", responseBody.get("data").get("biography").asText(),
                        "biography in response body must be \"Buddha\"."),
                () -> assertNull(responseBody.get("occupations"),
                        "occupations in response body must be \"[]\"."));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}