package com.github.fabriciolfj.order;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
@EmbeddedKafka
public abstract class OrderApplicationTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void beforeEach() {
		RestAssured.port = this.port;
	}

	@BeforeAll
	static void beforeAll() {
		FixtureFactoryLoader.loadTemplates("com.github.fabriciolfj.order.template");
		RestAssuredWebTestClient.with().contentType(ContentType.JSON);
		RestAssuredWebTestClient.enableLoggingOfRequestAndResponseIfValidationFails();
	}

}
