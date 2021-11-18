package com.example.last_task.utils;

import com.example.last_task.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserUtils {

    private final RestTemplate restTemplate;
    private final String URL = "http://91.241.64.178:7081/api/users";

    @Autowired
    public UserUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Печать в консоли пользователей
     * в формате JSON.
     * @return строка, содержащая заголовок "Set-Cookie".
     */
    public String getAllUsers() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                URL, String.class
        );
        System.out.println(responseEntity.getBody());
        return responseEntity.getHeaders().get("Set-Cookie").get(0);
    }

    /**
     * Создание пользователя.
     * @param cookie для возможности продолжения операций.
     * @param user новый пользователь.
     * @return первый код по условию задачи.
     */
    public String createUser(String cookie, User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Cookie", cookie);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        final ResponseEntity<String> exchange = restTemplate.exchange(
                URL, HttpMethod.POST, httpEntity, String.class
        );
        return exchange.getBody();
    }

    /**
     * Изменение пользователя.
     * @param cookie для возможности продолжения операций.
     * @param user пользователь для изменения.
     * @return второй код по условию задачи.
     */
    public String updateUser(String cookie, User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Cookie", cookie);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        final ResponseEntity<String> exchange = restTemplate.exchange(
                URL, HttpMethod.PUT, httpEntity, String.class
        );
        return exchange.getBody();
    }

    /**
     * Удаление пользователя.
     * @param cookie для возможности продолжения операций.
     * @param id ID пользователя для удаления.
     * @return третий код по условию задачи.
     */
    public String deleteUser(String cookie, Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cookie", cookie);
        HttpEntity<User> httpEntity = new HttpEntity<>(httpHeaders);
        final ResponseEntity<String> exchange = restTemplate.exchange(
                URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class
        );
        return exchange.getBody();
    }

}
