package hello;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.server.EmbeddedServer;

@Controller("/hello") // (1)
public class HelloController {
    private final String host;
    private final int port;

    public HelloController(EmbeddedServer embeddedServer) {
        host = embeddedServer.getHost();
        port = embeddedServer.getPort();
    }

    @Get(produces = MediaType.TEXT_PLAIN) // (2)
    public String index() {
        return "Hello World"; // (3)
    }
}
