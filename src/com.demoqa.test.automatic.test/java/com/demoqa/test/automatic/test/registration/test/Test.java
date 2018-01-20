package com.demoqa.test.automatic.test.registration.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.demoqa.test.automatic.main.models.Date;
import com.demoqa.test.automatic.main.models.User;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.demoqa.test.automatic.main.utils.RandomizeData;

public class Test {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private final String[] names = {"Jan van Dam", "Chack Norris", "Klark n Kent", "John Daw", "Bat Man", "Tim Los", "Dave o Core", "Pay Pal", "Lazy Cat", "Jack & Johnes"};
    private final List<User> users = User.returnListOfUsers(names);
    private static final List<User> addedUsers = new ArrayList<User>();

    @BeforeClass
    public static void setUpBeforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

    }

    @AfterClass
    public static void tearDownAfterClass() {
        driver.close();
    }

    @org.junit.Test
    public void testConnection() {
        driver.get(" http://demoqa.com/registration/");
        Assert.assertTrue(driver.getCurrentUrl().equals("http://demoqa.com/registration/"));
    }

    @org.junit.Test
    public void registerUsers() {
        ArrayList<String> resToPrint = new ArrayList<String>(Arrays.asList(names));
        for (int i = 0; i < 5; i++) {
            User user = registerRandomUser();
            addedUsers.add(user);
            resToPrint.remove(user.getCompleteName());
            wait = new WebDriverWait(driver, 5);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='pie_submit']")));
            element.click();
            wait = new WebDriverWait(driver, 20000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Thank you for your registration')]")));
            wait = new WebDriverWait(driver, 5);
            clearElement("name_3_firstname");
            clearElement("name_3_lastname");
            clearElement("phone_9");
            clearElement("username");
            clearElement("email_1");
            clearElement("description");
            clearElement("password_2");
            clearElement("confirm_password_password_2");
            resetHobbies();
        }
        for (String s : resToPrint) {
            System.out.println(s);
        }
    }

    private void clearElement(String elementIdentifier) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementIdentifier)));
        element.clear();
    }


    private void resetHobbies() {
        String[] hobbies = {"dance", "reading", "cricket "};
        for (String hobbie : hobbies) {
            if (driver.findElement(By.cssSelector("input[value='" + hobbie + "']")).isSelected()) {
                driver.findElement(By.cssSelector("input[value='" + hobbie + "']")).click();
            }
        }

    }

    private User registerRandomUser() {
        Random r = new Random(System.currentTimeMillis());
        User userToAdd = users.get(r.nextInt(users.size()));
        while (addedUsers.contains(userToAdd)) {
            userToAdd = users.get(r.nextInt(users.size()));
        }
        //User data
        wait = new WebDriverWait(driver, 5);
        typeDataInInputField("name_3_firstname", userToAdd.getFirstName());
        typeDataInInputField("name_3_lastname", userToAdd.getLastName());
        //Random marital status
        driver.findElement(By.cssSelector("input[value='" + RandomizeData.randomizeMarital() + "']")).click();
        //Random hobbies
        String randomHobbies[] = RandomizeData.randomizeHobbies();
        for (String hobbie : randomHobbies) {
            driver.findElement(By.cssSelector("input[value='" + hobbie + "']")).click();
        }
        //Random country(First three letters which are common) and select
        clickOnAnElementById("dropdown_7");
        typeDataInInputField("dropdown_7", RandomizeData.randomizeCountry());
        clickOnAnElementById("dropdown_7");
        //Random date(even if wrong)
        Date date = RandomizeData.randomizeDate();
        clickOptionElementByCss("mm_date_8", "option[value='" + date.getMonth() + "'");
        clickOptionElementByCss("dd_date_8", "option[value='" + date.getDay() + "'");
        clickOptionElementByCss("yy_date_8", "option[value='" + date.getYear() + "'");
        //Random phone number between 10-20 digits
        typeDataInInputField("phone_9", RandomizeData.randomizePhoneNumber());
        //Random username between 5-15 characters
        String username = RandomizeData.randomizeUserName();
        typeDataInInputField("username", username);
        userToAdd.setUsername(username);
        typeDataInInputField("email_1", RandomizeData.randomizeEmail());
        typeDataInInputField("description", RandomizeData.randomAbout());
        String password = RandomizeData.randomPassword();
        typeDataInInputField("password_2", password);
        typeDataInInputField("confirm_password_password_2", password);
        return userToAdd;
    }

    private void clickOptionElementByCss(String listId, String cssSelector) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(listId)));
        element.findElement(By.cssSelector(cssSelector)).click();
    }

    private void clickOnAnElementById(String elementId) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        element.click();
    }

    private void typeDataInInputField(String elementId, String data) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        element.sendKeys(data);
    }

}
