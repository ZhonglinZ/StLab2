package lab2;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestBaidu {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
 
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  String driverPath = System.getProperty("user.dir") + "/src/geckodriver.exe";
	  System.setProperty("webdriver.gecko.driver", driverPath);
	  driver = new FirefoxDriver();
	  baseUrl = "http://121.193.130.195:8800";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBaidu() throws Exception {

	  String path = "C:\\Users\\lenovo\\Desktop\\ACADEMY\\软件测试\\作业\\软件测试名单.xlsx";
	  File file =  new File(path);
	  InputStream is;
	  	Student st = new Student();	
	  	try {
	  	is = new FileInputStream(file);
	  	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	  	XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
	  	 for(int i = 2; i<145; i++) {
	 		   XSSFRow xssfRow = xssfSheet.getRow(i);
	 	  	    if (xssfRow != null) {
	 	  	    	DecimalFormat df = new DecimalFormat("0");           
	 	  		       String id =  df.format(xssfRow.getCell(1).getNumericCellValue()); 
	 	  		       String name = xssfRow.getCell(2).toString();
	 	  		       String address = xssfRow.getCell(3).toString();
	 	  		       String pass = id.substring(4);  
	 	  		       System.out.println(pass);
	 	  		      System.out.println(id);
	 		      //System.out.println(id);
	 	  		    driver.get("http://121.193.130.195:8800/login");
	 	  		    driver.findElement(By.name("id")).clear();
	 			    driver.findElement(By.name("id")).sendKeys(id);
	 			   driver.findElement(By.name("password")).clear();
	 			    driver.findElement(By.name("password")).sendKeys(pass);
	 			    WebElement btn = driver.findElement(By.id("btn_login"));
	 			    btn.sendKeys(Keys.ENTER);
	 			    driver.findElement(By.id("btn_login")).click();
	 			    assertEquals(id,  driver.findElement(By.id("student-id")).getText());
	 			    assertEquals(name,  driver.findElement(By.id("student-name")).getText());
	 			    assertEquals(address,  driver.findElement(By.id("student-git")).getText());
	 			    driver.findElement(By.id("btn_logout")).click();
	  		    //   students.add(st);
	      	}
	  	 }
	  	}
	  	catch (FileNotFoundException e) {
	  	// TODO Auto-generated catch block
	  	e.printStackTrace();
	  	} catch (IOException e) {
	  	// TODO Auto-generated catch block
	  	e.printStackTrace();
	  	}
	 

	  }

  @After
  public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

