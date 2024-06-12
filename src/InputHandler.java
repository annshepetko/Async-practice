import api.ApiService;
import representation.ResourceRepresentation;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputHandler {

    private final HashMap<Integer, String> options;
    private final HashMap<Integer, ResourceRepresentation> menu;
    private final ApiService apiService = new ApiService();
    private final FileWriterService fileWriterService = new FileWriterService();

    public InputHandler(HashMap<Integer, String> options, HashMap<Integer, ResourceRepresentation> menu) {
        this.options = options;
        this.menu = menu;
    }

    public void startHandlingInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            HelloMessageIllustrator.helloInstruction(menu, ResourceRepresentation::description);
            HelloMessageIllustrator.helloInstruction(options, Function.identity());

            System.out.print("Choose option: ");
            int userChoice = scanner.nextInt();
            System.out.println("File will saved when you terminated program");

            switch (userChoice) {
                case 1, 2, 3, 4  -> {
                    System.out.print("Enter a file name: ");
                    String name = scanner.next();
                    CompletableFuture<String> requestResult = apiService.sendRequest(userChoice, menu);
                    fileWriterService.writeFile("src/files/" + name, requestResult);
                    System.out.println("Writing file...");

                }
                case 8 -> {
                    System.out.println("When you fetching all options, files names will be default");
                    List<String> uris = getUrisForRequest(menu);
                    List<String> names = getFileNamesForRequest(menu);
                    List<CompletableFuture<String>> tempFutures = apiService.performListOfRequests(uris);
                    fileWriterService.writeFiles(tempFutures, names);
                    return;
                }
                case 9 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private List<String> getUrisForRequest(HashMap<Integer, ResourceRepresentation> menu) {
        return menu.values().stream()
                .map(ResourceRepresentation::url)
                .collect(Collectors.toList());
    }

    private List<String> getFileNamesForRequest(HashMap<Integer, ResourceRepresentation> menu) {
        return menu.values().stream()
                .map(r -> "src/files/" + r.description())
                .collect(Collectors.toList());
    }
}
