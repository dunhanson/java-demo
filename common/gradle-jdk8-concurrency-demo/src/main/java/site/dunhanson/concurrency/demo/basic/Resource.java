package site.dunhanson.concurrency.demo.basic;

import lombok.Data;

@Data
public class Resource {
    private String name;
    private int count;

    public Resource(String name) {
        this.name = name;
    }

    public void statisticsResource() {
        synchronized (this) {
            System.out.println("statistics resource");
            count++;
        }
    }

    public void saveResource(Resource resource) {
        synchronized (this) {
            System.out.println("save resource");
            resource.statisticsResource();
        }
    }
}
