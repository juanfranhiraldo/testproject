package com.demoqa.test.automatic.main.registrationForm;

import com.demoqa.test.automatic.main.models.Date;
import com.demoqa.test.automatic.main.models.User;
import com.demoqa.test.automatic.main.utils.RandomizeData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegistrationLogic {
    private WebDriverWait wait;
    private WebDriver driver;
    private List<User> addedUsers = new ArrayList<User>();
    private  List<User> users ;

    public RegistrationLogic(WebDriverWait wait, WebDriver driver,  List<User> users) {
        this.wait = wait;
        this.driver = driver;
        this.users=users;
    }



    public  void registerUser(ArrayList<String> resToPrint) {
        User user = registerRandomUser();
        addedUsers.add(user);
        resToPrint.remove(user.getCompleteName());
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
    /**
     * Register a random user.
     * @return The registered user.
     */
    private  User registerRandomUser() {
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
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='" + RandomizeData.randomizeMarital() + "']"))).click();

        //Random hobbies
        String randomHobbies[] = RandomizeData.randomizeHobbies();
        for (String hobbie : randomHobbies) {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='" + hobbie + "']"))).click();
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

    /**
     * Select an element by its css selector(used for dropdown elements/select).
     * @param listId id from the dropdown
     * @param cssSelector option from the dropdown.
     */
    private  void clickOptionElementByCss(String listId, String cssSelector) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(listId)));
        element.findElement(By.cssSelector(cssSelector)).click();
    }

    /**
     * Click on a web element.
     * @param elementId "Id" from that element.
     */
    private  void clickOnAnElementById(String elementId) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        element.click();
    }

    /**
     * Type data in an input element.
     * @param elementId "id" from that element.
     * @param data Data to be input.
     */
    private   void typeDataInInputField(String elementId, String data) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        element.sendKeys(data);
    }

    /**
     * Clear an input element.
     * @param elementIdentifier "id" field from that element.
     */
    private  void clearElement(String elementIdentifier) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementIdentifier)));
        element.clear();
    }

    /**
     * Deselect every hobby option in order to reset them.
     */
    private  void resetHobbies() {
        String[] hobbies = {"dance", "reading", "cricket "};
        for (String hobbie : hobbies) {
            if (driver.findElement(By.cssSelector("input[value='" + hobbie + "']")).isSelected()) {
                driver.findElement(By.cssSelector("input[value='" + hobbie + "']")).click();
            }
        }

    }
}

