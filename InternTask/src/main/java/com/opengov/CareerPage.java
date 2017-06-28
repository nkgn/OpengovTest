package com.opengov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by nitu on 6/27/2017.
 */
public class CareerPage {
    private WebDriver driver;


    @FindBy(xpath = "//div[@class='section']//span[text()='View Open Positions']")
    WebElement viewOpenPositionsButton;

    //@FindBy(xpath = "//ul[@class='boxTriggerList']/li")
    //WebElement departmentOpeningList;



    @FindBy(xpath = "//div[@class='section']//h2[text()='Current Openings']")
    WebElement currentOpeningHeading;

    @FindBy(xpath = "//*[@id=\"currentOpenings\"]//select[@name='department']")
    WebElement selectByDepartment;

    @FindBy(xpath = "//*[@id=\"currentOpenings\"]//select[@name='location']")
    WebElement selectByLocation;

    @FindBy(xpath = "//div[@class='section']//h2[contains(text(),'Engineering')]")
    WebElement selectedDepartmentHeading;



    public CareerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnViewOpenPositionsButton() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(viewOpenPositionsButton));
        viewOpenPositionsButton.click();
    }

    public boolean isCurrentOpeningHeadingVisible() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(currentOpeningHeading));
        return currentOpeningHeading.isDisplayed();
    }

    public void selectDepartment(String department) {
        Select ele = new Select(selectByDepartment);
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(selectByDepartment));
        ele.selectByVisibleText(department);
    }

    public void selectLocation(String location) {
        Select ele = new Select(selectByLocation);
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(selectByLocation));
        ele.selectByVisibleText(location);
    }

    /*public String getDepartmentHeading(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(selectedDepartmentHeading));
        return selectedDepartmentHeading.getText() ;
    }*/

    public int getOpeningsCount() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[div/h2[text()='Engineering']]//ul/li"));
        return elements.size();
    }
    /*public void saveOpeningPositionInFile(){
        List<WebElement> elements = driver.findElements(By.xpath("//div[div/h2[text()='Engineering']]//ul/li"));
        String pName ="";
        int num = elements.size() ;
        System.out.println(num);
        if(num > 0 ){
            //WriteInFile.saveInFile("Total number of openings in "  + num);
            for (WebElement myElement : elements) {
                pName = myElement.getText();
                System.out.println("pName= " + pName);
                WriteInFile.saveInFile(pName);
            }
        }
    return true;
    }*/


}
