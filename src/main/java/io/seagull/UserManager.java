package io.seagull;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by seagull on 11/9/15.
 */
public class UserManager {

    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_USER_NOT_FOUND = 404;

    private static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public static void main(String[] args) {
        UserWorker userWorker = new UserWorker();

        post("/newUser", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Interpreter.CreateInterpreter createInterpreter =
                        mapper.readValue(request.body(), Interpreter.CreateInterpreter.class);

                if(!createInterpreter.isValid()) {
                    response.status(HTTP_BAD_REQUEST);
                    return "";
                }

                int id = userWorker.newUser(createInterpreter.getId(), createInterpreter.getFirstName(),
                        createInterpreter.getMiddleName(), createInterpreter.getLastName(), createInterpreter.getAge(),
                        createInterpreter.getGender(), createInterpreter.getPhoneNumber(), createInterpreter.getZip());

                response.status(200);
                response.type("application/json");
                return id;
            } catch (JsonParseException jpe) {
                  response.status(HTTP_BAD_REQUEST);
                  return "";
            }
        });

        delete("/deleteUser", (request, response) -> {
            try{
                ObjectMapper mapper = new ObjectMapper();
                Interpreter.DeleteInterpreter deleteInterpreter =
                        mapper.readValue(request.body(), Interpreter.DeleteInterpreter.class);

                boolean flag = userWorker.deleteUser(deleteInterpreter.getId());

                if(flag) {
                    response.status(200);
                    response.type("application/json");
                } else {
                    response.status(HTTP_USER_NOT_FOUND);
                    response.body("User Not Found");
                    response.type("application/json");
                }
                return deleteInterpreter.getId();
            } catch (Exception e) {
                response.status(HTTP_BAD_REQUEST);
                return "";
            }
        });

        put("/updateUser", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Interpreter.UpdateInterpreter updateInterpreter =
                        mapper.readValue(request.body(), Interpreter.UpdateInterpreter.class);

                if(!updateInterpreter.isValid()) {
                    response.status(HTTP_BAD_REQUEST);
                    return "";
                }

                boolean flag  = userWorker.updateUser(updateInterpreter.getId(), updateInterpreter.getFirstName(),
                        updateInterpreter.getMiddleName(), updateInterpreter.getLastName(),
                        updateInterpreter.getAge(), updateInterpreter.getGender(),
                        updateInterpreter.getPhoneNumber(), updateInterpreter.getZip());

                if(flag) {
                    response.status(200);
                    response.type("application/json");
                } else {
                    response.status(HTTP_USER_NOT_FOUND);
                    response.body("User Not Found");
                    response.type("application/json");
                }
                return updateInterpreter.getId();
            } catch (JsonParseException jpe) {
                response.status(HTTP_BAD_REQUEST);
                return "";
            }
        });

        get("/users", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return dataToJson(userWorker.getAllUsers());
        });
    }
}