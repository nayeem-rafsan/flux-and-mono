import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;

public class ReactiveStreams {
    public static Flux<String> stringNumber(){
        return Flux.just("one", "two", "three", "four", "five")
                .delayElements(Duration.ofSeconds(1));
    }
    public static Flux<Integer> intNumber(){
        return Flux.just(1,2,3,4,5,6,7,8,9)
                .delayElements(Duration.ofSeconds(1));
    }
    public static Mono<Integer> intNumberMono(){
        return Mono.just(1);
    }
    public static Flux<String> alphaString(){
        return Flux.just("a", "b", "c", "d", "e");
    }
}
