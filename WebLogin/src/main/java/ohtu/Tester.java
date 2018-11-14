package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        sleep(2);
        
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();

        sleep(1);
        //kirjautuminen
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element = driver.findElement(By.name("login"));
        //väärä salasana
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("aiaauiauaiauiaia");
//        element = driver.findElement(By.name("login"));
        //väärä tunnus
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("jhauuauauoo");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("aiaauiauaiauiaia");
//        element = driver.findElement(By.name("login"));
        //uusi käyttäjätunnus
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        element = driver.findElement(By.name("username"));
        Random r = new Random();
        element.sendKeys("partoo"+ r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        
        //uloskirjautuminen
        element=driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element=driver.findElement(By.linkText("logout"));
        element.click();
        sleep(3);
        System.out.println(driver.getPageSource());
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
