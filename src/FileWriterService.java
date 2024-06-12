import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileWriterService {


    public void writeFile(String name, CompletableFuture<String> content )  {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        content.thenAccept((result) -> {
            try {
                bufferedWriter.write(result);
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void writeFiles(List<CompletableFuture<String>> content, List<String> names) {
        ExecutorService executorService = Executors.newFixedThreadPool(content.size());

        for (int i = 0; i < content.size(); i ++){
            int finalI = i;
            executorService.submit(() -> writeFile(names.get(finalI), content.get(finalI)));
        }
        executorService.shutdown();
    }
}
