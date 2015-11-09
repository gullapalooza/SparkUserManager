package io.seagull;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by seagull on 11/9/15.
 */
public class UserWorker {

    private final Map<Integer, User> users = new HashMap<>();

    @Data
    class User {
        private int id;
        private String firstName;
        private String middleName;
        private String lastName;
        private int age;
        private char gender;
        private String phoneNumber;
        private int zip;
    }

    public User createUser(User user, int id, String firstName, String middleName, String lastName,
                           int age, char gender, String phoneNumber, int zip) {
        user.setId(id);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setZip(zip);

        return user;
    }

    public int newUser(int id, String firstName, String middleName, String lastName,
                       int age, char gender, String phoneNumber, int zip) {
        User user = createUser(new User(), id, firstName, middleName, lastName, age, gender, phoneNumber, zip);

        if (!users.containsKey(id))
            users.put(id, user);

        return id;
    }

    public boolean updateUser(int id, String firstName, String middleName, String lastName,
                              int age, char gender, String phoneNumber, int zip) {

        if (users.containsKey(id)) {
            User user = createUser(new User(), id, firstName, middleName, lastName, age, gender, phoneNumber, zip);
            users.replace(id, user);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public List getAllUsers() {
        return users.keySet().stream().sorted().map(users::get).collect(Collectors.toList());
    }
}