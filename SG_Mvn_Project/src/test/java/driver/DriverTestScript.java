package driver;

import baseClass.LoginBaseclass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import common.AppDependentmethod;
import common.AppIndependentmethod;
import common.ReportUtils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.util.Map;

@CucumberOptions(
        tags = "@regression",
        glue = {"stepDefinition"},
        features = {"src/test/resources/featureFiles"},
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumberReport.html",
                "json:target/cucumber-report/cucumberReport.json"
        },
        monochrome = true, dryRun = false
)


public class DriverTestScript extends AbstractTestNGCucumberTests {

    public static AppDependentmethod appDep=null;
    public static AppIndependentmethod appInd=null;
    public static ReportUtils reports=null;
    public static WebDriver obrowser=null;
    public static ExtentTest xtest=null;
    public static ExtentReports extent=null;
    public static String Screenshotdir=null;
    public static Map<String, String> propData = null;
    public static Map<String,String> mapdata=null;
    public static LoginBaseclass loginBaseclass;

@BeforeSuite
    public void loadclass() {
        try {
            appDep = new AppDependentmethod();
            appInd = new AppIndependentmethod();
            reports = new ReportUtils();
            extent = reports.startExtentReport("Testresults");
            propData = appInd.getPropDetails(System.getProperty("user.dir")+"/src/main/resources/config.properties");
            loginBaseclass = new LoginBaseclass();


        } catch (Exception e) {
            System.out.println("Exception in 'loadclass()' method" + e);
        }
    }

        @DataProvider(parallel = false)
                public Object[][] Scenarios() {
            return super.scenarios();
        }

    }

