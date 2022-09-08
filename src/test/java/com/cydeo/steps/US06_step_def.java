package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US06_step_def {
    BookPage bookPage = new BookPage();
    String authorDB;

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
    bookPage.addBookBtn.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
    bookPage.bookName.sendKeys(bookName);
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String ISBN) {
    bookPage.isbn.sendKeys(ISBN);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
    bookPage.year.sendKeys(year);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
    bookPage.author.sendKeys(author);
    authorDB=author;
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String bookCategory){
    bookPage.selectBook(bookCategory);

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChangesBtn.click();

    }
    @Then("verify {string} message is displayed")
    public void verifyMessageIsDisplayed(String expectedPopUp) {
        BrowserUtil.waitFor(2);
        String actualPopUp = bookPage.popUpVerify.getText();
        Assert.assertEquals(expectedPopUp, actualPopUp);
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {
        DB_Util.runQuery("select id,name,author from books where name = '"+expectedBookName+"' and author='"+authorDB+"' order by id desc");
        String actualBookNameDB = DB_Util.getCellValue(1,2);
        Assert.assertEquals(expectedBookName, actualBookNameDB);


    }

}
