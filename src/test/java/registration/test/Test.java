package registration.test;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.Date;
import models.User;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.RandomizeData;

public class Test {
	static WebDriver driver;
	final String[] names={"Jan van Dam", "Chack Norris", "Klark n Kent", "John Daw", "Bat Man", "Tim Los", "Dave o Core", "Pay Pal", "Lazy Cat", "Jack & Johnes"};
	final List<User> users=User.returnListOfUsers(names);
	static List<User> addedUsers=new ArrayList<User>();
	@Before
	public void setUpBeforeClass() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver(options);
	    
	}

	@After
	public void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@org.junit.Test
	public void testConnection() throws InterruptedException{
		driver.get(" http://demoqa.com/registration/");
		Assert.assertTrue(driver.getCurrentUrl().equals("http://demoqa.com/registration/"));
        registerUsers();
	}
    @org.junit.Test
    public void registerUsers() {
        ArrayList<String> resToPrint=new ArrayList<String>(Arrays.asList(names));
        for(int i=0;i<5;i++){
            User user=registerRandomUser();
            addedUsers.add(user);
            resToPrint.remove(user.getCompleteName());
            driver.findElement(By.cssSelector("input[name='pie_submit']")).click();
            WebDriverWait wait = new WebDriverWait(driver, 20000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Thank you for your registration')]")));
            driver.findElement(By.id("name_3_firstname")).clear();
            driver.findElement(By.id("name_3_lastname")).clear();
            driver.findElement(By.id("phone_9")).clear();
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("email_1")).clear();
            driver.findElement(By.id("description")).clear();
            driver.findElement(By.id("password_2")).clear();
            driver.findElement(By.id("confirm_password_password_2")).clear();
            resetHobbies();
        }
        for(String s:resToPrint){
            System.out.println(s);
        }
    }


    private void resetHobbies() {
		String[] hobbies={"dance","reading","cricket "};
		for(int i=0;i<hobbies.length;i++){
			if(driver.findElement(By.cssSelector("input[value='"+hobbies[i]+"']")).isSelected()){
				driver.findElement(By.cssSelector("input[value='"+hobbies[i]+"']")).click();
			}
		}
		
	}

	private User registerRandomUser() {
		Random r=new Random(System.currentTimeMillis());
		User userToAdd=users.get(r.nextInt(users.size()));
		while(addedUsers.contains(userToAdd)){
			userToAdd=users.get(r.nextInt(users.size()));
		}
		//User data
		driver.findElement(By.id("name_3_firstname")).sendKeys(userToAdd.getFirstName());
		driver.findElement(By.id("name_3_lastname")).sendKeys(userToAdd.getLastName());
		//Random marital status
		driver.findElement(By.cssSelector("input[value='"+RandomizeData.randomizeMarital()+"']")).click();
		//Random hobbies
		String randomHobbies[]=RandomizeData.randomizeHobbies();
		for(int i=0;i<randomHobbies.length;i++){
			driver.findElement(By.cssSelector("input[value='"+randomHobbies[i]+"']")).click();
		}
		//Random country(First three letters which are common) and select
		driver.findElement(By.id("dropdown_7")).click();
		driver.findElement(By.id("dropdown_7")).sendKeys(RandomizeData.randomizeCountry());
		driver.findElement(By.id("dropdown_7")).sendKeys(Keys.RETURN);
		//Random date(even if wrong)
		Date date=RandomizeData.randomizeDate();
		driver.findElement(By.id("mm_date_8")).findElement(By.cssSelector("option[value='"+date.getMonth()+"'")).click();
		driver.findElement(By.id("dd_date_8")).findElement(By.cssSelector("option[value='"+date.getDay()+"'")).click();
		driver.findElement(By.id("yy_date_8")).findElement(By.cssSelector("option[value='"+date.getYear()+"'")).click();
		//Random phone number between 10-20 digits
		driver.findElement(By.id("phone_9")).sendKeys(RandomizeData.randomizePhoneNumber());
		//Random username between 5-15 characters
		String username=RandomizeData.randomizeUserName();
		driver.findElement(By.id("username")).sendKeys(username);
		userToAdd.setUsername(username);
		driver.findElement(By.id("email_1")).sendKeys(RandomizeData.randomizeEmail());
		driver.findElement(By.id("description")).sendKeys(RandomizeData.randomAbout());
		String password=RandomizeData.randomPassword();
		driver.findElement(By.id("password_2")).sendKeys(password);
		driver.findElement(By.id("confirm_password_password_2")).sendKeys(password);
		return userToAdd;
	}

}
