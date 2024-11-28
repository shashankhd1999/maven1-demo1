package stepDefinition;

import driver.DriverTestScript;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends DriverTestScript {

@Before
    public void setup(Scenario scenario){
        String scenarioname=null;
        try{
            scenarioname=scenario.getName();
           xtest= extent.startTest(scenarioname);
           obrowser=appInd.launchBrowser("chrome");
            if(obrowser!=null) reports.writeReport(obrowser, "Pass", "Browser launched successful");
            else reports.writeReport(null, "Fail", "Failed to launch the browser");
        } catch (Exception e) {
            reports.writeReport(obrowser,"exception","Exception in the method 'setup()'"+e);

        }
    }

@After
    public void teardown(Scenario scenario){
        String scenarioname=null;
        TakesScreenshot ts=null;
        try{
            scenarioname=scenario.getName().replace("","_");
            if(scenario.isFailed()==true) {
                ts = (TakesScreenshot) obrowser;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(scenarioname, "image/png", scenarioname);
            }
            reports.endExtentReport();
//            if(obrowser!=null){
//                obrowser.close();
//            }
        }catch (Exception e) {
            reports.writeReport(obrowser,"exception","Exception in the method 'teardown()'"+e);

        }

    }
}
