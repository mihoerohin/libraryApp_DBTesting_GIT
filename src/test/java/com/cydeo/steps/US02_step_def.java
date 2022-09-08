package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_step_def {

    DashBoardPage dashBoardPage = new DashBoardPage();
    String fromDB;
    String actualResUlt;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
        DB_Util.createConnection();
        new LoginPage().login(user);
        BrowserUtil.waitFor(3);
    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        fromDB = DB_Util.getFirstRowFirstColumn();
        actualResUlt = dashBoardPage.borrowedBooksNumber.getText();
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        Assert.assertEquals(fromDB, actualResUlt);
    }

}
