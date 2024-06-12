
import representation.ResourceRepresentation;

import java.util.HashMap;

public class Main {

    private static HashMap<Integer, ResourceRepresentation> uris = new HashMap<>() {
        {
            put(1, new ResourceRepresentation("https://jsonplaceholder.typicode.com/photos", "photos"));
            put(2, new ResourceRepresentation("https://jsonplaceholder.typicode.com/users", "users"));
            put(3, new ResourceRepresentation("https://jsonplaceholder.typicode.com/todos", "todos"));
            put(4, new ResourceRepresentation("https://jsonplaceholder.typicode.com/comments", "comments"));
        }
    };

    private final static HashMap<Integer, String> options = new HashMap<>(){{
        put(8, "all");
        put(9, "exit");

    }};

    private static final InputHandler inputHandler = new InputHandler(options, uris);

    public static void main(String[] args) {
        inputHandler.startHandlingInput();
    }
}

