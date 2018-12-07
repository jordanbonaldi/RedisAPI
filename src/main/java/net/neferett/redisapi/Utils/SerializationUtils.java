package net.neferett.redisapi.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SerializationUtils {

    @SneakyThrows
    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        is.close();

        return sb.toString();
    }

    @SneakyThrows
    public static Object convertStreamToClass(InputStream is, Class<?> clazz) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        is.close();

        return new ObjectMapper().readValue(sb.toString(), clazz);
    }

    @SneakyThrows
    public static JSONObject serialize(Object obj) {
        return new JSONObject(serializeToString(obj));
    }

    @SneakyThrows
    public static String serializeToString(Object obj) {
        return new ObjectMapper().writeValueAsString(obj);
    }

    @SneakyThrows
    public static Object deSerialize(Class clazz, String json) {
        return new ObjectMapper().readValue(json, clazz);
    }

}
