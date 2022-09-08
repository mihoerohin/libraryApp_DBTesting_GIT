package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US07_step_def {
    BookPage bookPage =new BookPage();

    @Given("the user searches book name called {string}")
    public void the_user_searches_book_name_called(String headFirstJava) {
        bookPage.search.sendKeys(headFirstJava);
        BrowserUtil.waitFor(3);

    }
    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        bookPage.clickableBorrowBook();
        BrowserUtil.waitFor(2);
        String actualPopUp = bookPage.popUpVerify.getText();
        String expectedPopUp = "The book has been borrowed...";
        Assert.assertEquals(expectedPopUp, actualPopUp);
    }

    @Then("verify that book is shown in {string} page")
    public void verifyThatBookIsShownInPage(String borrowingBooks) {
        bookPage.clickModels(borrowingBooks);
    }

    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

    }


}
