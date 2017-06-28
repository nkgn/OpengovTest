/* Test to verify open job postions in paricular departments
* or combination of filters like department and location
* */

package com.opengov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageResources.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by nitu on 6/27/2017.
 */
public class OpenPositions_Test {

    private WebDriver driver;
    private String baseURL = "";
    private HomePage homepage;
    private String expectedTitle;


    //Logger class to log error messages to logs folder under project
    private static final Logger log = LogManager.getLogger(OpenPositions_Test.class.getName());

    /**
     * To setup the environment and launch the browser
     * to be execute only once for whole test
     **/
    @BeforeTest
    public void setUp() {

        /* Set Chrome Driver properties for maximize and avoid bubbles*/
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-save-password-bubble");

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Utilities.driver = driver ;
        // launch the browser and open the site
        baseURL = "https://opengov.com";
        driver.get(baseURL);
        expectedTitle = "Government Performance Management | OpenGov";
        try {

            Thread.sleep(2000);
            //assert the title of the webpage desired
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            log.info("Expected title of page Assert Passed");

            homepage = new HomePage(driver);
            Utilities.createTestResultFile();

        } catch (Exception e) {
            log.debug(e);
            e.printStackTrace();
            Assert.fail("Exception generated while opening the homepage ");
        }
        log.info("Setup to run open positions test completed");
    }

    @Test(dataProviderClass = testData.DataProviderClass.class, dataProvider = "searchFilter") // here fieldsinput method provides the data to testcase
    public void test_filterOnDepartmentOnly(String department) {
        /* store the start time of the test*/
        long startTime = System.currentTimeMillis();
        String testResult;
        try {
            //Click on company tab
            homepage.clickOnCompanyTab();

            // on company page, click on Career link
            TabCompany_Page companyTab = new TabCompany_Page(driver);
            companyTab.clickOnCareerLink();

            // verify career page is opened successfully
            CareerPage careerLink = new CareerPage(driver);

            Assert.assertTrue(careerLink.isCurrentOpeningHeadingVisible());
            log.info("Career Page successfully opened  Assert Passed");

            //select specified department from the drop down list
            careerLink.selectDepartment(department);
            Thread.sleep(1000);

            /*get the list of all the job postings for the department*/
            String departmentOpeningsList = "//div[div/h2[text()='" + department + "']]//ul/li";
            List<WebElement> elements = driver.findElements(By.xpath(departmentOpeningsList));
            String pName = "";
            int num = elements.size();
            /*record the number of job openings in text file*/
            testResult = "\n###Total number of openings in department " +
                    department + " is " + num;
            Utilities.writeToTestResultFile(testResult);


            if (num > 0) {
                /* verify that page is updated according to the input in the dropdown boxes and proper
                 information is provided */
                WebElement selectedDepartmentHeading = driver.findElement(By.xpath("//div[@class='section']//h2[contains(text(),'" + department + "')]"));
                Assert.assertEquals(department, selectedDepartmentHeading.getText());
                log.info("Department heading equal to selected department , Assert Passed");
                // assert that location on the output page is same as input from parameter
                for (WebElement myElement : elements) {
                    pName = myElement.getText();

                    // record the job opening in the text file
                    Utilities.writeToTestResultFile(pName);
                }
            }
            // record the completion of test in txt file
            recordInFile(startTime);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /*This test opens the careers page and find the number of job openings
     * for the given department and given location  */

    @Test(dataProviderClass = testData.DataProviderClass.class, dataProvider = "searchFilter")
    /* here fieldsinput method provides the data to testcase*/
    public void test_filterOnDepartmentAndLocation(String department, String location) {

        /* store the start time of the test*/
        long startTime = System.currentTimeMillis();

        String testResult;

        try {
            // click on company tab
            homepage.clickOnCompanyTab();

            // on company page, click on career link
            TabCompany_Page companyTab = new TabCompany_Page(driver);
            companyTab.clickOnCareerLink();

            // verify if career page is opened successfully
            CareerPage careerLink = new CareerPage(driver);

            Assert.assertTrue(careerLink.isCurrentOpeningHeadingVisible());
            log.info("Career Page successfully opened  Assert Passed");

            //select specified department from the drop down list
            careerLink.selectDepartment(department);
            Thread.sleep(1000);

            //select specified location from the drop down list
            careerLink.selectLocation(location);
            Thread.sleep(1000);

            /*get the list of all the job postings in the location for the department*/
            String departmentLocator = "//div[div/h2[text()='" + department + "']]//ul/li";
            List<WebElement> elements = driver.findElements(By.xpath(departmentLocator));
            String pName = "";
            int num = elements.size();

            //record the number of job openings in text file

            testResult = "###\nTotal number of openings in department " +
            department + " at location " + location + " is " + num;
            Utilities.writeToTestResultFile(testResult);

            if (num > 0) {
                // verify that page is updated according to the input in the dropdown boxes and proper
                // information is provided
                WebElement selectedDepartmentHeading = driver.findElement(By.xpath("//div[@class='section']//h2[contains(text(),'" + department + "')]"));
                Assert.assertEquals(department, selectedDepartmentHeading.getText());

                log.info("Department heading equal to selected department , Assert Passed");
                // assert that location on the output page is same as input from parameter
                for (WebElement myElement : elements) {
                    pName = myElement.getText();
                    Assert.assertTrue(pName.contains(location));
                    log.info("Selected location equal to location in job openings , Assert Passed");

                    // record the job opening in the text file
                    Utilities.writeToTestResultFile(pName);
                }
            }
            // record the completion of test in txt file
            recordInFile(startTime);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

/*This methos is used to clean up after after all the tests are done*/
    @AfterTest
    public void cleanUp() {
        driver.quit();
    }

    /* Reusable function to calculate the difference between start time and end time of the tests*/
    public void recordInFile(long startTime){
        // record the data and time  of the test ending in the text file
        long endTime = System.currentTimeMillis();
        long s = endTime - startTime;
        String testResult="Test run time in minutes : " + (s / (1000*60)) + " minutes";
        Utilities.writeToTestResultFile(testResult);
        testResult="Test run time in seconds : " + ((s/1000)) + " seconds";
        Utilities.writeToTestResultFile(testResult);

        log.info(" Test run completed in " + ((s/1000)) + " in seconds " );
    }


}






