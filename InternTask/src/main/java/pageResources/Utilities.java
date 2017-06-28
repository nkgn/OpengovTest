package pageResources;

/**Utilities class is helper class for thr classes in the package.*/


import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
/**
 * Created by nitu on 6/28/2017.
 */
public class Utilities {
        public static WebDriver driver;
        public static File f1 ;

        public static String getHeader(){
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy");

            //  create a new String using the date format we want
            String reportHeader = formatter.format(new Date());
            //System.out.println(reportHeader);
            return reportHeader ;
        }

        /*Helper method to provide date and time */
        public  static String getDateTime() {
            GregorianCalendar gcalendar = new GregorianCalendar();
            Calendar today = new GregorianCalendar();

            // Get the date
            int week = today.get(Calendar.DAY_OF_WEEK);
            int day = today.get(Calendar.DAY_OF_MONTH);
            int month = today.get(Calendar.MONTH);
            int year = today.get(Calendar.YEAR);
            int hour = today.get(Calendar.HOUR_OF_DAY);
            int minute = today.get(Calendar.MINUTE);
            int sec = today.get(Calendar.SECOND);

            String date_time = "" + month + '_' + day + '_' + year  +"-"+
                     + hour + '_' + minute + '_' + sec  ;
           // System.out.println(month + " " + day + " " + year);

             return date_time;
        }

        public static void createTestResultFile(){
            String dt = getDateTime();

            try {
                f1 = new File("testLog_" + dt);
                f1.createNewFile();
                FileWriter fileWritter = new FileWriter(f1.getName(), true);
                BufferedWriter bw = new BufferedWriter(fileWritter);
                bw.write("Date is " + getHeader() + "\n\n");

                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void writeToTestResultFile(String st){
            try {

                if (f1.exists()) {

                    FileWriter fileWritter = new FileWriter(f1.getName(), true);
                    BufferedWriter bw = new BufferedWriter(fileWritter);
                    bw.write(st + "\n\n");
                    bw.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }








}


