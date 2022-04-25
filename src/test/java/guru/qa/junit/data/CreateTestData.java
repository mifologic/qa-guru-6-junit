package guru.qa.junit.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class CreateTestData {

    String locale;
    Faker faker;

    public CreateTestData(String locale) {
        this.locale = locale;
        this.faker = new Faker(new Locale(locale));
    }

    public String getFullName() {
        return faker.name().fullName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getCurrentAddress() {
        return faker.address().fullAddress();
    }

    public String getPermanentAddress() {
     return faker.address().secondaryAddress();
    }
}
