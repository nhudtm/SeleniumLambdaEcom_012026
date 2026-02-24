package dataProviders;

import utils.DBUtils;
import org.testng.annotations.DataProvider;
import java.util.Map;

public class DatabaseDataProviders {
    @DataProvider(name="categoryProductQuantityFromDatabase")
    public Object[][] categoryProductQuantityFromDatabase() {
        Map<String, Integer> categoryMap = DBUtils.getCategoryProductQuantityModelFromDB();
        if(categoryMap == null) {
            throw new RuntimeException("Category list from DB is null");
        }
        System.out.println("Category Map from DB: " + categoryMap);
        return new Object[][] { { categoryMap }};
    }
}
