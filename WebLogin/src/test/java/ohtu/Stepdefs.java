package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    } 
//
//    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
//    public void username_and_password_are_given(String username, String password) throws Throwable {
//        WebElement element = driver.findElement(By.name("username"));
//        element.sendKeys(username);
//        element = driver.findElement(By.name("password"));
//        element.sendKeys(password);
//        element = driver.findElement(By.name("login"));
//        element.submit();  
//    }
    @Given("^command new user is selected$")
    public void signup_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();          
    } 
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created")
    public void new_user_createdAndLogOut(String username, String password){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        signUpWith(username, password, password);
        element = driver.findElement(By.linkText("continue to application mainpage"));  
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();

    }
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created")
    public void new_user_not_created_and_login(String username, String password){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        signUpWith(username, password, password);
        element = driver.findElement(By.linkText("back to home"));  
        element.click();
    }
    @When("^login with username \"([^\"]*)\" and password \"([^\"]*)\" are given")
    public void new_user_login(String username, String password){
        logInWith(username, password);
    }
    
    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    @When("^incorrect username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void wrong_username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    // new user
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered")
    public void new_user_correct(String username, String password) throws Throwable{
        signUpWith(username, password, password);
    }
    @When("^a invalid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered")
    public void username_too_short(String username, String password) throws Throwable{
        signUpWith(username, password, password);
    }
    @When("^a valid username \"([^\"]*)\" and bad password \"([^\"]*)\" and matching password confirmation are entered")
    public void password_too_short(String username, String password) throws Throwable{
        signUpWith(username, password,password);
    }
    @When("^password \"([^\"]*)\" and confirmation \"([^\"]*)\" are entered")
    public void password_dont_match(String password, String confirmation) throws Throwable{
        signUpWith("jouko", password, confirmation);
    }
    
    @Then("^user is not created and error \"([^\"]*)\" is reported")
    public void new_user_not_created(String error){
        pageHasContent("Create username and give password");
        pageHasContent(error);
    }
    
    @Then("^a new user is created")
    public void new_user_OK(){
        pageHasContent("Welcome to Ohtu Application!");
    }
    @Then("^user is not created and passworderror \"([^\"]*)\" is reported")
    public void passwordError(String passworderror){
        pageHasContent("Create username and give password");
        pageHasContent(passworderror);
    }
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }     
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    public void signUpWith(String username, String password, String confirmation){
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    }    
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
