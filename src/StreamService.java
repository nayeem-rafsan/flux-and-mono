import java.util.stream.Stream;

public class StreamService{
    public Stream<String> stringNumber(){
        return Stream.of("one", "two", "three", "four");
    }
    public Stream<Integer> intNumber(){
        return Stream.iterate(0, i-> i+2).limit(10);
    }
    public Stream<User> userStream(){
        return Stream.of(
                new User(0,"Nayeem"),
                new User(1, "Araf"),
                new User(2, "Niloy")
        );
    }
}