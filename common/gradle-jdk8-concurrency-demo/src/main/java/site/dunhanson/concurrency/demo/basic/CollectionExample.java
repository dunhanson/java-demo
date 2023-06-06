package site.dunhanson.concurrency.demo.basic;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 集合示例
 */
@Slf4j
public class CollectionExample {
    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    Type type = new TypeToken<List<Employee>>(){}.getType();

    @Data
    static class Employee {
        private Integer empNo;
        private String birthDate;
        private String firstName;
        private String lastName;
        private Gender gender;
        private String hireDate;
        enum Gender {
            M,F
        }
    }

    /**
     * 获取数据
     * @return List<Employee>
     * @throws IOException IO异常
     */
    public List<Employee> listData() throws IOException {
        String path = "D:\\test\\mysql\\employees.json";
        String json = FileUtils.readFileToString(new File(path), Charset.defaultCharset());
        return gson.fromJson(json, type);
    }

    /**
     * 测试数据
     * @throws IOException IO异常
     */
    public void testData() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        CollectionExample example = new CollectionExample();
        List<Employee> employees = example.listData();
        List<LocalDate> dateList = employees.stream().map(employee -> {
            String birthDate = employee.getBirthDate();
            return LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
        }).collect(Collectors.toList());
        log.info("testDate size:{}", dateList.size());
        log.info("testDate 耗时:{}ms", Duration.between(start, LocalDateTime.now()).toMillis());
    }

    /**
     * 测试数据1
     * @throws IOException IO异常
     */
    public void testData1() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        CollectionExample example = new CollectionExample();
        List<Employee> employees = example.listData();
        List<LocalDate> dateList = employees.stream().parallel().map(employee -> {
            String birthDate = employee.getBirthDate();
            return LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
        }).collect(Collectors.toList());
        log.info("testDate1 size:{}", dateList.size());
        log.info("testDate1 耗时:{}ms", Duration.between(start, LocalDateTime.now()).toMillis());
    }

    /**
     * 测试数据用线程池
     * @throws IOException IO异常
     */
    public void testDataWithExecutorService() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        CollectionExample example = new CollectionExample();
        // 线程池对象
        ExecutorService service = Executors.newFixedThreadPool(12);
        // Future集合
        List<Future<LocalDate>> futureList = Collections.synchronizedList(new ArrayList<>());
        // List<Employee>
        List<Employee> employees = example.listData();
        // 遍历
        employees.forEach(employee -> {
            Future<LocalDate> future = service.submit(() -> {
                String birthDate = employee.getBirthDate();
                return LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
            });
            futureList.add(future);
        });
        List<LocalDate> dateList = futureList.stream().map(future-> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        log.info("testDataWithExecutorService size:{}", dateList.size());
        log.info("testDataWithExecutorService 耗时:{}ms", Duration.between(start, LocalDateTime.now()).toMillis());
        service.shutdown();
    }

    public static void main(String[] args) throws IOException {
        CollectionExample example = new CollectionExample();
        // example.testData();
        example.testData1();
        example.testData1();
        example.testDataWithExecutorService();
    }
}
