package stepDefinition;

import driver.DriverTestScript;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginStepDefinition extends DriverTestScript {




    @Given("verify user navigate to url")
    public void verifyUserNavigateToUrl() {
        Assert.assertTrue(loginBaseclass.verifynavigateurl(obrowser));
    }

    @And("verify user login to actitime functionality")
    public void verifyUserLoginToActitimeFunctionality() {
    Assert.assertTrue(loginBaseclass.verifylogin(obrowser));

    }

//    @Then("verify user to create new task")
//    public void verifyUserToCreateNewTask() {
//    }
//
//    @And("verify user logout from application")
//    public void verifyUserLogoutFromApplication() {
//    }
}
