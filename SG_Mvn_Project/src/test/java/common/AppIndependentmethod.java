package common;

import driver.DriverTestScript;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class AppIndependentmethod extends DriverTestScript {


    public String datetimemethod(String format){
        Date date=null;
        SimpleDateFormat sdf=null;
        try{
            date=new Date();
            sdf=new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            System.out.println("Exception in the datetimemethod() method"+e);
            return null;
        }
    }


    public boolean clickmethod(WebDriver obrowser, By objby){
        WebElement web=null;
        boolean blnres=false;
        try{
            web=obrowser.findElement(objby);
            if(web.isDisplayed()){
                web.click();
                reports.writeReport(obrowser,"pass","click operation for '"+objby+"' is successful");
                blnres=true;
            }
            return blnres;

        }catch (Exception e) {
            reports.writeReport(obrowser,"exception","Exception in the method 'clickmethod()'"+e);
            return false;
        }
    }

    public boolean sendobjmethod(WebDriver obrowser,By objby,String data){
        WebElement web=null;
        boolean blnres=false;
        try{
            web=obrowser.findElement(objby);
            if(web.isDisplayed()){
                web.sendKeys(data);
                reports.writeReport(obrowser,"pass","the data '"+data+"' entered successfully");
                blnres=true;
            }
            return blnres;
        } catch (Exception e) {
            reports.writeReport(obrowser,"exception","Exception in the method 'sendobjmethod()'"+e);
            return false;
        }
    }

    public boolean compareValues(WebDriver oBrowser, String actual, String expected){
        try{
            if(actual.equalsIgnoreCase(expected)){
                reports.writeReport(oBrowser, "Pass", "The actual '"+actual+"' & expected '"+expected+"' values are matched");
                return true;
            }else{
                reports.writeReport(oBrowser, "Fail", "Mis-match in the actual '"+actual+"' & expected '"+expected+"' values");
                return false;
            }
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'compareValues()': " + e);
            return false;
        }
    }

    public Map<String, String> getPropDetails(String filePath) {
        FileInputStream fin = null;
        Properties prop = null;
        Map<String, String> dataMap = null;
        Set<Map.Entry<Object, Object>> data = null;
        Iterator<Map.Entry<Object, Object>> it = null;
        try {
            dataMap = new HashMap<>();
            fin = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fin);

            data = prop.entrySet();
            it = data.iterator();
            while (it.hasNext() == true) {
                Map.Entry<Object, Object> mp = it.next();
                dataMap.put(mp.getKey().toString(), mp.getValue().toString());
            }
            return dataMap;
        }catch (Exception e) {
            reports.writeReport(null, "Exception", "Exception in the method 'getPropDetails()': " + e);
            return null;
        }finally {
            try {
                fin.close();
                fin = null;
                prop = null;
                data = null;
                it = null;
            } catch (Exception e) {
            }
        }
    }


    public WebDriver launchBrowser(String browserName){
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                default:
                    reports.writeReport(null, "Fail", "Invalid '"+browserName+"' browser name was mentioned. Please specified valid broswer name");
                    return null;
            }
            if(oDriver==null){
                reports.writeReport(oDriver, "Fail", "Failed to launch '"+browserName+"' browser");
                return null;
            }else{
                reports.writeReport(oDriver, "Pass", "The '"+browserName+"' browser is launched successful");
                oDriver.manage().window().maximize();
                return oDriver;
            }
        }catch(Exception e){
            reports.writeReport(oDriver, "Exception", "Exception in the method 'launchBrowser()': " + e);
            return null;
        }
    }








}
