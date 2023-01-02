import org.reactivestreams.Subscription;
import org.w3c.dom.ls.LSOutput;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
//import reactor.core.StepVerifier;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static reactor.core.publisher.Mono.just;




public class Main{
    public static void main(String[] args) throws IOException {
        // // System.in.read(); to keep the thread alive
        // //Stream and its operators
        // StreamService s = new StreamService();
        // //print each number
        // s.stringNumber().forEach(e -> System.out.println(e));
        // //print number that are less than 5
        // s.intNumber().filter(number -> number<5).forEach(e -> System.out.println(e));
        // // print the second and third number that are less than 5
        // s.intNumber().filter(number->number<5).skip(1).limit(2).forEach(e -> System.out.println(e));
        // // print the first number greater than 5, if not found print -1
        // Integer n = s.intNumber().filter(number->number>5).findFirst().orElse(-1);
        // System.out.println(n);
        // // print the name of the all user
        // s.userStream().forEach(u -> System.out.println(u.getName()));
        // // or
        // map replaces the value in the stream, here it is replacing the user with the user objects.
        // s.userStream().map(user -> user.getName()).forEach(e-> System.out.println(e));
        // // get the user with an id that belongs to intStream
        // i cannot use map because id-> s.userStream() provides me a sream not a single object
        // that's why we have to use flatMap, it replaces the id with each individual users. if right hand provides a stream then use flatMap()
        // s.intNumber().flatMap(id-> s.userStream().filter(u-> u.getId()==id));
        // // or
        // s.userStream().filter(
        //     u->s.intNumber().anyMatch(id->u.getId()==id)
        // ).forEach(e-> System.out.println(e.getName()));

        // // ** Notes on Reactive Streams**
//        ReactiveStreams.stringNumber().subscribe(e -> System.out.println(e) );
        // // subscribe() is like forEach()
        // // when we update the numberflux stream call the subscribe() method
        // // subscribe is basically saying if any event happens then perform S.O.P.
//        System.out.println("Enter a key");
//        System.in.read();
        // // Flux is asynchronous items of 0 or n items.
        // // Mono is asynchornous item of 0 or 1 item, not more than that. it works with max of 1 element, flux works with multiple elements.
        // // Mono can return one list, list is an element.
        // // Mono can emit one item and then emits complete event or a failure event, it does not emit anything even after a failure event/complete event


        // // subscribe can take three parameter, one is the item, second is for error and 3rd is for completion
//        ReactiveStreams.intNumberMono().subscribe(item-> System.out.println(item),
//                err -> System.out.println(err.toString()),
//                () -> System.out.println("Completion")
//        );
//        ReactiveStreams.alphaString().subscribe(item-> System.out.println(item));
//        System.out.println("Enter a key");
//        System.in.read();

        // // **BaseSubscriber**
        // // we can implement class instead of these lambda functions
        // // Backpressure with BaseSubscriber class
//        ReactiveStreams.stringNumber().subscribe(new MyBaseSubscriber<>());


        // // handling unresponsive Monos.
        // // wait for 5 seconds and then block the mono if it doesn't return a value.
        // // block() usually waits for the value to load fully then executes the next line
//        Integer value = ReactiveStreams.intNumberMono().block(Duration.ofSeconds(5));

        // // operators in flux
        // // Log: writes to std.out, copies the flux and prints it. good way to debug

//        List<Integer> numbers = ReactiveStreams.intNumber()
//                .log()
//                .toStream()
//                .collect(Collectors.toList());

        // // print all the values that are greater than 5 and print it using log
//        Mono<Integer> intMono = Mono.just(1);
//        System.out.println("A");
//        ReactiveStreams.intNumber().map(e-> {
//                    if(e==4){
//                        return new Exception("");
//                    }
//                    else return e;
//                })
//                .onErrorReturn("Error")
//                        .subscribe(System.out::println);
//        Flux.just(1,2,0,3,4)
//                .map(e->25/e) // will produce ArithmeticException
//                .onErrorContinue((err,obj)-> System.out.println(err.toString()))
//                        .subscribe(System.out::println);
//        Flux.just(1,2,0,3,4)
//                .map(e->25/e) // will produce ArithmeticException
//                .onErrorReturn(-1)
//                .subscribe(System.out::println);
        Flux.just(1,2,0,3,4)
                .map(e->25/e) // will produce ArithmeticException
                .onErrorResume(e-> System.out::println)
                .subscribe(System.out::println);
        System.in.read();
    }
}
class MyBaseSubscriber<T> extends BaseSubscriber<T>{
    // // this method is used when an event occurs [same ase first lambda above]
    public void hookOnSubscribe(Subscription subscription){
        System.out.println("Subscribe happened");
        // //this method means I can handle two items max, not more than that.
        // //this method says you can give me (when you're ready) one item when the event first occurs.
        request(2);
    }
    public void hookOnNext(T value){
        // // when a new value is processed this method is called. [same as last lambda above]
        System.out.println(value.toString()+" received");
        // // this method says you can give me one item at a time (as next value) when you're ready
        request(1);
    }
}