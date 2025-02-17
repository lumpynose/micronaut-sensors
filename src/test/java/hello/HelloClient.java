package hello;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;

@Client("/hello") // (1)
public interface HelloClient {
    @Get(consumes = MediaType.TEXT_PLAIN) // (2)
    @SingleResult
    Publisher<String> hello(); // (3)
}
