import java.util.List;

public class ResourceRepresentation {


    private String url;
    private String description;
    private List<String> urls;

    public ResourceRepresentation(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public ResourceRepresentation(List<String> urls, String description) {
        this.urls = urls;
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public List<String> getUrls() {
        return urls;
    }

    public String getDescription() {
        return description;
    }

}
