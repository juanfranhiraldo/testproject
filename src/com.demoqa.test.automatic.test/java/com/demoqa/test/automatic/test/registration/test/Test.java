package com.demoqa.test.automatic.test.registration.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.demoqa.test.automatic.main.registrationForm.RegistrationLogic;
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
    private  static  WebDriver driver;
    private  WebDriverWait wait;
    private final String[] names = {"Jan van Dam", "Chack Norris", "Klark n Kent", "John Daw", "Bat Man", "Tim Los", "Dave o Core", "Pay Pal", "Lazy Cat", "Jack & Johnes"};
    private final List<User> users = User.returnListOfUsers(names);
    private   List<User> addedUsers = new ArrayList<User>();

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

    public static WebDriver getDriver() {
        return driver;
    }

    @org.junit.Test
    public void testConnection() {
        driver.get(" http://demoqa.com/registration/");
        Assert.assertTrue(driver.getCurrentUrl().equals("http://demoqa.com/registration/"));
    }

    @org.junit.Test
    public void registerUsers() {
        wait = new WebDriverWait(driver, 5);
        RegistrationLogic registrationPage= new RegistrationLogic(wait,driver,users);
        ArrayList<String> resToPrint = new ArrayList<String>(Arrays.asList(names));
        for (int i = 0; i < 5; i++) {
            registrationPage.registerUser(resToPrint);
        }
        for (String s : resToPrint) {
            System.out.println(s);
        }







}}
