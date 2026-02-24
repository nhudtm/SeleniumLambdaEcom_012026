package utils;

import net.datafaker.Faker;

import java.util.Locale;

public class DataFaker {
    private final Faker faker;

    public DataFaker () {
        faker = new Faker(new Locale("en-US"));
    }

    public DataFaker(Locale locale) {
        this.faker = new Faker(locale);
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getPhone() {
        return faker.phoneNumber().phoneNumber();
    }

    public String getPassword() {
        return faker.internet().password();
    }


}
