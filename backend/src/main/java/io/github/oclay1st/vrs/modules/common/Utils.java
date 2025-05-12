package io.github.oclay1st.vrs.modules.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    private static final JsonMapper mapper = new JsonMapper();

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String convertObjectToString(Object object) {
        var serialized = "";
        try {
            serialized = mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error("Error to convert object {} into string ", object.toString(), ex);
            log.error("JsonProcessingException: ", ex);
        }
        return serialized;
    }

    public static <T> T convertStringToObject(String json, Class<T> clazz) {
        T object = null;
        try {
            object = mapper.readValue(json, clazz);
        } catch (JsonProcessingException ex) {
            log.error("Error to convert string {} into object ", json, ex);
            log.error("JsonProcessingException: ", ex);
        }
        return object;
    }

    public static Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return null;
        }
    }

}
