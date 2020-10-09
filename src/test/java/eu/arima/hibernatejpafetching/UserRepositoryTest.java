package eu.arima.hibernatejpafetching;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import eu.arima.hibernatejpafetching.beans.Department;
import eu.arima.hibernatejpafetching.beans.Project;
import eu.arima.hibernatejpafetching.beans.User;
import eu.arima.hibernatejpafetching.repositories.DepartmentRepository;
import eu.arima.hibernatejpafetching.repositories.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    public void initializeDb() {
    
        this.userRepository.deleteAll();
        this.departmentRepository.deleteAll();

        Department hr = new Department("Human Resources");
        hr.setProjects(Arrays.asList(new Project("hr_2019")));
        Department marketing = new Department("Marketing");
        marketing.setProjects(Arrays.asList(new Project("marketing_2019"), new Project("marketing_2020")));
        List<Department> departments = Arrays.asList(hr, marketing);

        this.departmentRepository.saveAll(departments);

        User urko = new User("urko", departments);
        User telle = new User("telle", Arrays.asList(hr));

        this.userRepository.saveAll(Arrays.asList(urko, telle));
    }

    @AfterEach
    public void emptyDb() {

        this.userRepository.deleteAll();
        this.departmentRepository.deleteAll();
    }

    @Test
    @Transactional

    void getAllUsersJPA() {

        List<User> users = this.userRepository.findAll();

        users.stream().forEach(user -> {System.out.println(user.toString());});
    
        assertEquals(2, users.size());
    }

    @Test
    @Transactional
    void getAllUsersJPQL() {

        List<User> users = this.userRepository.findUsers();

        users.stream().forEach(user -> {System.out.println(user.toString());});
    
        assertEquals(2, users.size());
    }
}
