package guru.qa.junit.pages;

import com.codeborne.selenide.Condition;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BookStorePage {

    public BookStorePage openPage() {
        open("/books");
        return this;
    }

    public BookStorePage setSearchWord(String testData) {
        $("#searchBox").setValue(testData);
        return this;
    }

    public BookStorePage checkBookInSearchResults(String bookTitle){
        $$(".rt-tbody").find(Condition.text(bookTitle)).should(Condition.visible);
        return this;
    }

    public BookStorePage checkBooksInResult(List<String> booksList) {
        for(String book : booksList) {
            $$(".rt-tbody").find(Condition.text(book)).should(Condition.visible);
        }
        return this;
    }
}
