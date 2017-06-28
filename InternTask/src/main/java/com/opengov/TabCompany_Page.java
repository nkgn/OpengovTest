package com.opengov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nitu on 6/27/
 * This is PageFactory for Comapny Tab Page containing all the menu elements of Company Tab
 *  Following are some of the elements and it not the complete list of elements on Comapny Tab
 */
public class TabCompany_Page {
    private WebDriver driver;

    @FindBy(xpath = "//div[@id='companyDropdown']//span[@class='navList__hd'][contains(text(),'Careers')]")
    WebElement careerLink;

    @FindBy(xpath = "//div[@id='companyDropdown']//span[@class='navList__hd'][contains(text(),'Newsroom')]")
    WebElement newsroomLink;

    @FindBy(xpath = "//div[@id='companyDropdown']//span[@class='navList__hd'][contains(text(),'Partners')]")
    WebElement partnersLink;



    public TabCompany_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCareerLink() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(careerLink));
        careerLink.click();
    }

    public void clickOnPartners() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(partnersLink));
        partnersLink.click();
    }

    public void clickOnNewsroomLink() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(newsroomLink));
        newsroomLink.click();
    }




}
