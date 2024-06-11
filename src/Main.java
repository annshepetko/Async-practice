
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    private static final HashMap<Integer, String> uris = new HashMap<>(){
        {
            put(1, "https://jsonplaceholder.typicode.com/photos");
            put(2, "https://jsonplaceholder.typicode.com/users");
            put(3, "https://jsonplaceholder.typicode.com/todos");
            put(4, "https://jsonplaceholder.typicode.com/comments");

        }
    };

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        Scanner scanner = new Scanner(System.in);
        ;
        System.out.printf("Choice api: ");
        Integer inputChoice = scanner.nextInt();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uris.get(inputChoice)))
                .GET()
                .build();


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        Future<String> future = executorService.submit(callable);

        Thread thread = new Thread(() -> {
            try {
                System.out.printf(future.get());
                executorService.shutdown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();

        System.out.printf("waiting for response...");


        thread.join();

//        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }).thenAccept(System.out::println);
//
//        System.out.printf("Waitig for response");
//
//        completableFuture.join();

    }

}
