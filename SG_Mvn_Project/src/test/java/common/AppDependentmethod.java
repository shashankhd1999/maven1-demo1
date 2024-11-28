package common;

import driver.DriverTestScript;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class AppDependentmethod extends DriverTestScript {

    public boolean navigateurl(WebDriver obrowser,String strurl){
        try{
            obrowser.navigate().to(strurl);
            waitforelement(obrowser, LoginPage.objby_loginpage_title,"visibility"," ",10);
            reports.writeReport(obrowser,"screenshot","navigate to url was successful");
            return appInd.compareValues(obrowser,obrowser.getTitle(),"actiTIME - Login");
        } catch (Exception e) {
            reports.writeReport(obrowser,"exception","Exception in the method 'navigateurl()'"+e);
            return false;
        }
    }

    public boolean waitforelement(WebDriver obrowser, By objby, String condition,String text,long duration){
        WebDriverWait owait=null;
        try{
            owait=new WebDriverWait(obrowser, Duration.ofSeconds(duration));
            switch (condition.toLowerCase()){
                case "clickable":
                    owait.until(ExpectedConditions.elementToBeClickable(objby));
                    break;
                case "text":
                    owait.until(ExpectedConditions.textToBePresentInElementLocated(objby,text));
                    break;
                case "visibility":
                    owait.until(ExpectedConditions.visibilityOfElementLocated(objby));
                    break;
                case "invisibility":
                    owait.until(ExpectedConditions.invisibilityOfElementLocated(objby));
                    break;
                default:
                    reports.writeReport(obrowser,"fail","invalid condition '"+condition+"' in the waitelementmethod ");
                    return false;
            }
            return true;
        }catch (Exception e) {
            reports.writeReport(obrowser,"exception","Exception in the method 'waitforelement()'"+e);
            return false;
        }
    }


}
