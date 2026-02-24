package dataProviders;

import org.testng.annotations.DataProvider;

public class FilterColorDataProvider {
    @DataProvider(name = "filterColorData")
    public static Object[][] filterColorData() {
        return new Object[][]{
                {"Red"},
                {"Blue"},
                {"Green"},
                {"Orange"},
                {"Pink"}
        };
    }

    @DataProvider(name="filterSizeData")
    public static Object[][] filterSizeData() {
        return new Object[][]{
                {"36"}, //XL
                {"37"}, //XXL
                {"38"}, //L
                {"39"}, //M
                {"40"}, //XS

        };
    }
}
