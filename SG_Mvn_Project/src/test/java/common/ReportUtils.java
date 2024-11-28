package common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import driver.DriverTestScript;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class ReportUtils extends DriverTestScript {

    /*********************************
     * method       : startExtentReport()
     * purpose      : to create and start the extent report
     *
     *********************************/
    public ExtentReports startExtentReport(String reportFilename){
        String reportFilePath=null;
        File reportFile=null;
        File screenshotfile=null;
        try{
            reportFilePath=System.getProperty("user.dir")+"/target/extent-report";
            reportFile=new File(reportFilePath);
            if(!reportFile.exists()){
                reportFile.mkdirs();
            }

            Screenshotdir=reportFilePath+"/screenshot";
            screenshotfile=new File(Screenshotdir);
            if(!screenshotfile.exists()){
                screenshotfile.mkdirs();
            }

            extent=new ExtentReports(reportFilePath+"/"+reportFilename+".html",false);
            extent.addSystemInfo("user name",System.getProperty("user.name"));
            extent.addSystemInfo("host name",System.getProperty("os.name"));
            extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
            return extent;
        } catch (Exception e) {
            System.out.println("Exception in the startExtentReport() method"+e);
            return null;
        }
    }


    public void endExtentReport(){
        try{
            extent.endTest(xtest);
            extent.close();
            extent.flush();
        }catch (Exception e) {
            System.out.println("Exception in the endExtentReport() method"+e);
        }
    }

    public String capturescreenshot(WebDriver obrowser){
        String scrpath=null;
        File objsrc=null;
        try{
            scrpath=Screenshotdir+"/screenshot_"+appInd.datetimemethod("YYYYMMddhhmmss")+".pgn";
            objsrc= ((TakesScreenshot) obrowser).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(objsrc, new File(scrpath));
            return scrpath;
        }catch (Exception e) {
            System.out.println("Exception in the capturescreenshot() method"+e);
            return null;
        }
    }

    public void writeReport(WebDriver obrowser,String status,String description){
        try{
            switch (status.toLowerCase()){
                case "pass":
                    xtest.log(LogStatus.PASS,description);
                    break;
                case "fail":
                    xtest.log(LogStatus.FAIL,description+ xtest.addScreenCapture(capturescreenshot(obrowser)));
                    break;
                case "warning":
                    xtest.log(LogStatus.WARNING,description+xtest.addScreenCapture(capturescreenshot(obrowser)));
                    break;
                case "exception":
                    xtest.log(LogStatus.FATAL,description+xtest.addScreenCapture(capturescreenshot(obrowser)));
                    break;
                case "screenshot":
                    xtest.log(LogStatus.PASS,description+xtest.addScreenCapture(capturescreenshot(obrowser)));
                    break;
                default:
                    System.out.println("invalid status '"+status+"' mentioned");
            }

        }catch (Exception e) {
            System.out.println("Exception in the writeReport() method"+e);
        }
    }
}
