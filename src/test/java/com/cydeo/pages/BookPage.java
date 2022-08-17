package com.cydeo.pages;

import com.cydeo.utility.Driver;
import io.cucumber.java.zh_cn.假如;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//a[@class='btn btn-lg btn-outline btn-primary btn-sm add_book_btn']")
    public WebElement addBookBtn;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(name = "name")
    public WebElement bookName;

    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "description")
    public WebElement description;

    @FindBy(id = "book_group_id")
    public WebElement bookCategorySelect;

    public void selectBook(String str){
        Select selectByText = new Select(bookCategorySelect);
        selectByText.selectByVisibleText(str);
    }


    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement saveChangesBtn;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement popUpVerify;


    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//tbody/tr/td/a")
    public List<WebElement> borrowBook;

    public void clickableBorrowBook(){
        for (WebElement element : borrowBook){
            if(element.isEnabled()){
                element.click();
            }
        }
    }

    public void isReturnBook(String book) {
        String xpath = "//td[2][.='"+book+"']/../td[6]";
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath(xpath));
        for (WebElement element : elements){
            if(element.getText().contains("NOT RETURNED")){

            }
        }
    }






}
