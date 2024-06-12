
import java.util.Map;
import java.util.function.Function;

public class HelloMessageIllustrator {

    public static  <T> void helloInstruction(Map<Integer, T> map, Function<T, String> descriptionProvider) {
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            System.out.println(descriptionProvider.apply(entry.getValue()) + " - " + entry.getKey());
        }
    }
}
