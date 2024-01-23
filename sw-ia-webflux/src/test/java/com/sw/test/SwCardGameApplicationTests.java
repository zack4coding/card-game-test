package com.sw.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwCardGameApplicationTests {

	@Value("${local.server.port}")
	private int port;
	private String URL;

	@Before
	public void setup() {
		URL = "ws://localhost:" + port + "/event-emitter";
//		URL = "ws://localhost:" + port + "/echo";
	}

	@Test
	public void testWebSocket() {
		try {
			WebSocketClient client = new ReactorNettyWebSocketClient();

			client.execute(
					URI.create(URL),
					session -> session.send(
							Mono.just(session.textMessage("event-spring-reactive-client-websocket")))
							.thenMany(session.receive()
									.map(WebSocketMessage::getPayloadAsText)
									.log())
							.then())
					.block(Duration.ofSeconds(2L))
			;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}


}
