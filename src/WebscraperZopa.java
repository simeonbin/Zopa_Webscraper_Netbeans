
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Simeon
 */
public class WebscraperZopa {

    static String urlZopa = "http://www.zopa.com/";
    static String firefoxProfile = "SimBin";
    static String strXPathLanguageDependence;
    static String strXPathGetAZopaLoan = ".//*[@id='homepage-hero']/div/div/div/div[1]/a";
    static String strXPathGetAZopaLoanText = ".//*[@id='header']/div/div[2]/ul/li[1]/a";
    static String btnXPathGetMyPersonalisedRates = ".//*[@id='submit-loan-button']";
    static String strXPathEmailAddress = ".//*[@id='member_email']";

    static String strXPathTitle = ".//*[@id='content']/div/div/div/form/fieldset[1]/div[2]/p[1]/span[2]/label";
    static String strXPathFirstName = ".//*[@id='applications_loan_apply_first_name']";
    static String strXPathLastName = ".//*[@id='applications_loan_apply_last_name']";
    static String strXPathPhonenumber = ".//*[@id='applications_loan_apply_home_phone']";

    static String ClientFile = "clients.txt";
    static ArrayList<ZopaClient> zopaC = new ArrayList<> (5);
    static int currentZopaClient;
    static String strXPathDOBDay = ".//*[@id='date_of_birth_day']";
    static String strXPathDOBMonth = ".//*[@id='date_of_birth_month']";
    static String strXPathDOBYear = ".//*[@id='date_of_birth_year']";
    static String dayDOB; static String monthDOB; static String yearDOB;
    
    public void dateSplitDayMonthYear (String dateDOB) {
        
        String[] dateParts = dateDOB.split("/");
        dayDOB = dateParts[0];
        monthDOB = dateParts[1];
        yearDOB = dateParts[2];
    }
    
    public boolean validateEmail(String dateDOB) {        
        EmailValidator eVal = new EmailValidator();        
        return (eVal.validate(dateDOB) );        
    }
    
    public void completeTheForm(WebDriver driver) throws IOException { 
        FileWriter fw = null; PrintWriter pw = null;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        ZopaClient zopaCx = zopaC.get(currentZopaClient);
        String emailZopaClient = zopaCx.email;
        
        if ( validateEmail (emailZopaClient) ) {
        
        By searchEmail = By.xpath(strXPathEmailAddress);
        driver.findElement(searchEmail).sendKeys(emailZopaClient);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchEmail));

        fw = new FileWriter("clients.txt", true /* append = true */);
        pw = new PrintWriter(fw);

        pw.append(emailZopaClient); //Writing In To File.
        pw.append("\n");
        }
        else {
        pw.append("Invalid Email"); //Writing In To File.
        pw.append("\n");
        }        
        
        String firstNameZopaClient = zopaCx.firstName;
        By searchFirstName = By.xpath(strXPathFirstName);
        driver.findElement(searchFirstName).sendKeys(firstNameZopaClient);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchFirstName));
        //   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //   driver.findElement(By.xpath(strXPathLastName)).sendKeys("Biniatidis"); 

        pw.append(firstNameZopaClient); //Writing In To File.
        pw.append("\n");
        
        String lastNameZopaClient = zopaCx.lastName;
        By searchLastName = By.xpath(strXPathLastName);
        driver.findElement(searchLastName).sendKeys(lastNameZopaClient);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchLastName));

        pw.append(lastNameZopaClient); //Writing In To File.
        pw.append("\n");
        
         String DOBZopaClient = zopaCx.DOB;         
        dateSplitDayMonthYear (DOBZopaClient);
        
        By searchDOBDay = By.xpath(strXPathDOBDay);
        driver.findElement(searchDOBDay).sendKeys(dayDOB);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchDOBDay));
        
        By searchDOBMonth = By.xpath(strXPathDOBMonth);
        driver.findElement(searchDOBDay).sendKeys(monthDOB);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchDOBMonth));
        
        By searchDOBYear = By.xpath(strXPathDOBYear);
        driver.findElement(searchDOBDay).sendKeys(yearDOB);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchDOBYear));
        

        pw.append(DOBZopaClient); //Writing In To File.
        pw.append("\n");

        pw.append("---------\n");

        pw.close();
    }

    public void navigateToZopa(WebDriver driver) {
        WebDriverWait wait;

        driver.navigate().to(urlZopa);
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlMatches(urlZopa));
    }

    public void scrapeGameInfo(WebDriver driver) throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath(strXPathGetAZopaLoan)).click();
        //    driver.findElement(By.xpath(strXPathGetAZopaLoanText)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        for (int iRate = 0; iRate < 5; iRate++) {
            currentZopaClient = iRate;
            By searchGetMyPersonalisedRates = By.xpath(btnXPathGetMyPersonalisedRates);
            driver.findElement(searchGetMyPersonalisedRates).click();
            //    driver.findElement(searchGetMyPersonalisedRates).submit();
            //   WebElement el =  wait.until(ExpectedConditions.elementToBeClickable(searchGetMyPersonalisedRates ) );
            //    el.click();

            completeTheForm(driver);
            driver.findElement(By.xpath(strXPathGetAZopaLoanText)).click();
            WebDriver.Timeouts implicitlyWait = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

    }

    public void initZopaClient () {
        ZopaClient  zopaC0 = new ZopaClient("simeonbin@gmail.com","Simeon","Biniatidis", "23/11/1961");
        zopaC.add(zopaC0);        
        ZopaClient  zopaC1 = new ZopaClient("nickbin@gmail.com","Nick","Biniatidis", "23/11/1971");
        zopaC.add(zopaC1);
        ZopaClient  zopaC2 = new ZopaClient("dimbin@gmail.com","Dimitrios","Biniatidis", "23/11/1941");
        zopaC.add(zopaC2);
        ZopaClient  zopaC3 = new ZopaClient("barbarabin@gmail.com","Barbara","Biniatidis", "23/11/1951");
        zopaC.add(zopaC3);
        ZopaClient  zopaC4 = new ZopaClient("mariabin@gmail.com","Maria","Biniatidis", "23/11/2001");        
        zopaC.add(zopaC4);
    }
    
    public void WebscraperZopaImpl() throws IOException {

        WebDriverWait wait;
        String valueOfCookie;

        FileWriter FW = new FileWriter(ClientFile);
        BufferedWriter BW = new BufferedWriter(FW);

        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile(WebscraperZopa.firefoxProfile);
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(false);
        WebDriver driver2 = new FirefoxDriver(myProfile);

        initZopaClient();
        navigateToZopa(driver2);
        scrapeGameInfo(driver2);

        driver2.close();

    }

    public static void main(String[] args) throws IOException {

        
        WebscraperZopa scrape = new WebscraperZopa();
        scrape.WebscraperZopaImpl();

    }

}
