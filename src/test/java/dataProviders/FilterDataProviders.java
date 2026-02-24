package dataProviders;

import org.testng.annotations.DataProvider;

public class FilterDataProviders {
    @DataProvider(name="priceRanges")
    public Object[][] getPriceRanges() {
        return new Object[][] {
                { "100", "500" },
                { "500", "1000" },
                { "1000", "2000" },
        };
    }
}
