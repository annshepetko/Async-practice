import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Handler<Number> numberHandler =  new Handler<>();

        List<Integer> integerList = new ArrayList<>(List.of(1,2,3,4));
        numberHandler.doAction(integerList);

    }

}

class Handler <T>{
    public void doAction(List<T> list){

    }
}