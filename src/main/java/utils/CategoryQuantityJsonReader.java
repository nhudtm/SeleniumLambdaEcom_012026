package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CategoryQuantityJsonReader {
    public static Map<String, Integer> getCategoryQuantity() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.TEST_RESOURCES_PATH + "productQuantity.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
