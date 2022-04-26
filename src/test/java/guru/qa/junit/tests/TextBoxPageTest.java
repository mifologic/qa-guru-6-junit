package guru.qa.junit.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.junit.data.CreateTestData;
import guru.qa.junit.pages.BookStorePage;
import guru.qa.junit.pages.TextBoxPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class TextBoxPageTest {

    TextBoxPage textBoxPage = new TextBoxPage();
    BookStorePage bookStorePage = new BookStorePage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {
            "sv",
            "hu"
    })
    @ParameterizedTest(name = "User can fill form in {0} language")
    void fillTextBoxFormInDifferentLanguagesTest(String locale) {

        CreateTestData testData = new CreateTestData(locale);
        String fullName = testData.getFullName();
        String email = testData.getEmail();
        String currentAddress = testData.getCurrentAddress();
        String permanentAddress = testData.getPermanentAddress();


        textBoxPage.openPage()
                .setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submitForm()
                .checkResult(fullName)
                .checkResult(email)
                .checkResult(currentAddress)
                .checkResult(permanentAddress);
    }

    @CsvSource(value = {
            "JavaScript, Programming JavaScript Applications "
    })
    @ParameterizedTest(name = "Find book about {0} with expected title {1}")
    void FindBookInBookStoreTest(String testData, String bookTitle) {
        bookStorePage.openPage()
                .setSearchWord(testData)
                .checkBookInSearchResults(bookTitle);
    }

    static Stream<Arguments> booksByPublisherData() {
        return Stream.of(
                Arguments.of(
                        "No Starch Press",
                        List.of(
                                "Eloquent JavaScript, Second Edition",
                                "Understanding ECMAScript 6")),
        Arguments.of(
                "O'Reilly Media",
                List.of(
                        "Git Pocket Guide",
                        "Learning JavaScript Design Patterns",
                        "Designing Evolvable Web APIs with ASP.NET",
                        "Speaking JavaScript",
                        "You Don't Know JS",
                        "Programming JavaScript Applications")));
    }

    @MethodSource("booksByPublisherData")
    @ParameterizedTest
    void checkBooksByPublisher(String publisher, List<String> books) {
        bookStorePage.openPage()
                .setSearchWord(publisher)
                .checkBooksInResult(books);
    }
}
