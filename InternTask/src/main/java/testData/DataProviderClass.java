package testData;

/**
 * Created by nitu on 6/28/2017.
 */

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DataProviderClass {

    /*********Using single data provider method with method parameter ****/

    @DataProvider(name="searchFilter")
    public Object[][] getDataFromDataprovider(Method m) {

        if (m.getName().equalsIgnoreCase("test_filterOnDepartmentAndLocation")) {
            // if filter by department and location ,sends department name , location name and file name to record the result
            return new Object[][]{
                    {"Engineering","Redwood City"}
                    //{"Finance",Portland,OR"},


            };

        }else if (m.getName().equalsIgnoreCase("test_filterOnDepartmentOnly")) {
            // if filter by department, sends departmentname and file name to record the result
            return new Object[][]{
                    {"Engineering"}

            };

        }else {
            //log.info("DataProvider could not find any data .");
            return null;
        }
    }

}

