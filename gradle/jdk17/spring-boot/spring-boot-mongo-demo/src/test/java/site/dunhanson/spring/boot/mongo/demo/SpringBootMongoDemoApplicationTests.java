package site.dunhanson.spring.boot.mongo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import site.dunhanson.spring.boot.mongo.demo.entity.Employee;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
class SpringBootMongoDemoApplicationTests {
    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    void testInsertEmployee() {
        Employee employee = new Employee();
        employee.setEmpNo("10001");
        employee.setBirthDate(LocalDate.parse("1953-09-02"));
        employee.setFirstName("Georgi");
        employee.setLastName("Facello");
        employee.setGender("M");
        employee.setHireDate(LocalDate.parse("1986-06-26"));
        mongoTemplate.insert(employee);
        System.out.println(employee.getId());
    }

}
