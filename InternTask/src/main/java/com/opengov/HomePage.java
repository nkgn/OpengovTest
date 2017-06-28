package com.opengov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *  * Created by nitu on 6/27/2017.
 *
 *  PageFactory for Home Page containing all the elements of Home Page
 *  Following are some of the elements and it not the complete list of elements on home page
 */

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//a[@data-nav-target='#companyDropdown']/span")
    WebElement companyTab;


    @FindBy(xpath = "//span[@class='navPrimary__hd'][contains(text(),'Products')]")
    WebElement productTab;


    @FindBy(xpath = "//span[@class='navPrimary__hd'][contains(text(),'Customers')]")
    WebElement customersTab;

    @FindBy(xpath = "//span[@class='navPrimary__hd'][contains(text(),'Learn')]")
    WebElement learnLogo;


    @FindBy(xpath = "//div[@class='branding']/a[contains(@title,'Go to the home page.')]")
    WebElement companyLogo;

    @FindBy(xpath = "//span[@class='navPrimary__hd'][contains(text(),'Search')]")
    WebElement searchTab;

    @FindBy(xpath = "//a[@class='navBtn  navBtn--outline'][contains(text(),'Login')]")
    WebElement loginButton;



    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCompanyTab() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(companyTab));
        companyTab.click();
    }


}
