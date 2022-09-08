package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US01_step_def {
    DashBoardPage dashBoardPage = new DashBoardPage();
    String expectedBD;
    List<String> expectedHeader;


    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection();
        new LoginPage().login("librarian");
        BrowserUtil.waitFor(4);
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        DB_Util.runQuery("select count(*) from users");
        expectedBD = DB_Util.getFirstRowFirstColumn();

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        Assert.assertEquals(expectedBD, dashBoardPage.usersNumber.getText());

    }
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");
        expectedHeader = DB_Util.getAllColumnNamesAsList();

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> actualHeader) {
        Assert.assertEquals(expectedHeader, actualHeader);

    }


}
