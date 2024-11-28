package baseClass;

import driver.DriverTestScript;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginBaseclass extends DriverTestScript {


    public boolean verifynavigateurl(WebDriver obrowser){
        try{
          appDep.navigateurl(obrowser,propData.get("appURL"));
            return true;
        } catch (Exception e) {
            reports.writeReport(obrowser,"Exception","exception in method verifynavigateurl()"+e);
            return false;
        }
    }

    public boolean verifylogin(WebDriver obrowser){
        try{
            Assert.assertTrue(appInd.sendobjmethod(obrowser, LoginPage.obj_username_edit,propData.get("userName")));
            Assert.assertTrue(appInd.sendobjmethod(obrowser,LoginPage.obj_password_edit,propData.get("password")));
            Assert.assertTrue(appInd.clickmethod(obrowser,LoginPage.obj_login_btn));
            appDep.waitforelement(obrowser, HomePage.obj_savechangeshome_btn,"clickable","",10);
            return true;

        }catch (Exception e) {
            reports.writeReport(obrowser,"Exception","exception in method  verifylogin()"+e);
            return false;
        }
    }
}
