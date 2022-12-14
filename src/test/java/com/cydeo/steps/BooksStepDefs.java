package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BooksStepDefs {
    BookPage bookPage=new BookPage();
    List<String> actualCategoryList;


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
    }


    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("expectedCategoryList = " + actualCategoryList);
    }

    @Then("user should be able to see following categories")
    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {
        Assert.assertEquals(expectedCategoryList, actualCategoryList);

    }

    @When("I open book {string}")
    public void i_open_book(String bookName) {
        System.out.println("bookName = " + bookName);
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }

    @Then("verify book categories must match book categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        String query = "select name from book_categories";

        DB_Util.runQuery(query);

        List<String> expectedDataList = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedDataList, actualCategoryList);


    }

    @Then("book information must match the database for {string}")
    public void book_information_must_match_the_database_for(String bookName) {

        BrowserUtil.waitFor(3);

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName  = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDescription = bookPage.description.getAttribute("value");

        String query = "select * from books where name = '" + bookName + "'";

        DB_Util.runQuery(query);

        Map<String, String> rowMap = DB_Util.getRowMap(1);

        String expectedName = rowMap.get("name");
        String expectedAuthor = rowMap.get("author");
        String expectedIsbn = rowMap.get("isbn");
        String expectedDescription = rowMap.get("description");
        String expectedYear = rowMap.get("year");

        Assert.assertEquals(expectedName, actualBookName);
        Assert.assertEquals(expectedAuthor, actualAuthorName);
        Assert.assertEquals(expectedIsbn, actualISBN);
        Assert.assertEquals(expectedDescription, actualDescription);
        Assert.assertEquals(expectedYear, actualYear);

    }


}
