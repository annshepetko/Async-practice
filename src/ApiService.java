import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ApiService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public CompletableFuture<String> sendRequest(int choice, Map<Integer, ResourceRepresentation> map) {
        HttpRequest httpRequest = buildGetRequest(map.get(choice).getUrl());

        return performRequest(httpRequest);
    }

    private HttpRequest buildGetRequest(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        return request;
    }

    private List<HttpRequest> buildGetRequests(List<String> uris) {
        List<HttpRequest> request = uris.stream()
                .map(this::buildGetRequest)
                .toList();

        return request;
    }

    private CompletableFuture<String> performRequest(HttpRequest httpRequest) {
        CompletableFuture<String> tempFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return tempFuture;
    }

    public List<CompletableFuture<String>> performListOfRequests(List<String> urls) {
        List<HttpRequest> httpRequests = buildGetRequests(urls);

        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (HttpRequest request : httpRequests) {
            futures.add(performRequest(request));
        }
        return futures;
    }

}
