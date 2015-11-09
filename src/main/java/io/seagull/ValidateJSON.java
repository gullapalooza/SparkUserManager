package io.seagull;

/**
 * Created by seagull on 11/8/15.
 */
public class ValidateJSON {

    public static boolean isValid(String firstName, String middleName, String lastName,
                           int age, char gender, String phoneNumber, int zip) {

        for(char c : firstName.toCharArray()) {
            if(!Character.isLetter(c))
                return false;
        }

        if(!middleName.isEmpty())
            for(char c : middleName.toCharArray()) {
                if(!Character.isLetter(c))
                    return false;
            }

        for(char c : lastName.toCharArray()) {
            if(!Character.isLetter(c))
                return false;
        }

        if(age <= 0)
            return false;

        if (!(gender == 'm' || gender =='f' || gender == 'M' || gender == 'F'))
            return false;

        if (!(phoneNumber.length() == 10 && Long.parseLong(phoneNumber) > 0))
            return false;

        if(!(zip > 9999 && zip < 100000))
            return false;

        return true;
    }
}

