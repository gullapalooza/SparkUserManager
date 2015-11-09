package io.seagull;

import lombok.Data;

/**
 * Created by seagull on 11/9/15.
 */
public class Interpreter {

    @Data
    static class UpdateInterpreter {
        private int id;
        private String firstName;
        private String middleName;
        private String lastName;
        private int age;
        private char gender;
        private String phoneNumber;
        private int zip;

        public boolean isValid() {
            return ValidateJSON.isValid(firstName, middleName, lastName, age, getGender(), phoneNumber, zip);
        }
    }

    @Data
    static class CreateInterpreter {
        private int id;
        private String firstName;
        private String middleName;
        private String lastName;
        private int age;
        private char gender;
        private String phoneNumber;
        private int zip;

        public boolean isValid() {
            return ValidateJSON.isValid(firstName, middleName, lastName, age, getGender(), phoneNumber, zip);
        }
    }

    @Data
    static class DeleteInterpreter {
        private int id;
    }
}
