package com.example.last_task;

import com.example.last_task.model.User;
import com.example.last_task.utils.UserUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LastTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(LastTaskApplication.class, args);
    }

    public static void getCodeOfCurrentTask() {
        UserUtils userUtils = new UserUtils(new RestTemplate());
        final String cookie = userUtils.getAllUsers();
        System.out.println(cookie);
        System.out.println("------");

        User user = new User(3L, "James", "Brown", (byte) 19);
        final String firstPartOfCode = userUtils.createUser(cookie, user);
        System.out.println(firstPartOfCode);

        user.setName("Thomas");
        user.setLastName("Shelby");
        final String secondPartOfCode = userUtils.updateUser(cookie, user);
        System.out.println(secondPartOfCode);

        final String thirdPartOfCode = userUtils.deleteUser(cookie, 3L);
        System.out.println(thirdPartOfCode);

        System.out.println("------");
        System.out.println(firstPartOfCode + secondPartOfCode + thirdPartOfCode);
    }

}
