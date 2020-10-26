package com.gabeharms.projectName.api.rest.root;

import com.gabeharms.projectName.api.rest.RestApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RestApiApplication.class, RootController.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
class RootControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testIndex() {
        var entity = restTemplate.getForEntity("http://localhost:" + port + "/", String.class);
        assertAll(
            () -> assertEquals(HttpStatus.OK, entity.getStatusCode()),
            () -> assertEquals("Hello world!\n", entity.getBody())
        );
    }

}
