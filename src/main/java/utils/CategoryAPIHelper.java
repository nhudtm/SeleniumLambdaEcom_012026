package utils;


import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class CategoryAPIHelper {
    public static Map<String, Integer> getCategoryQuantityMap() {
        Response response = RestAssured
                .get("https://16c5fbf5-83f3-4463-93b6-629ad859131b.mock.pstmn.io/");
        return response.jsonPath().getMap("$");
    }
}
