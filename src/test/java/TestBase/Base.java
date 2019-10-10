package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.annotations.*;




public class Base {

    public static WebDriver driver;
    public static Properties prop;
    public static Logger log;


    @BeforeClass
    @Parameters("browser")


    public void initialization(String br ) throws IOException {
        //configproperty file code
        try {
            prop = new Properties( );
            FileInputStream ip = new FileInputStream( System.getProperty( "user.dir" ) + "\\Config\\config.properties" );
            prop.load( ip );

        } catch ( FileNotFoundException e ) {
            e.printStackTrace( );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }

        //log4j code
        log = Logger.getLogger( "wwwnopcom01" );
        PropertyConfigurator.configure( System.getProperty( "user.dir" ) + "\\Config\\log.properties" );

        //setup

       // String br = prop.getProperty( "browser" );

        //Opens browser
        if (br.equals("firefox")) {
            // Firefox Browser
            System.setProperty("webdriver.gecko.driver",prop.getProperty("ff"));
            driver = new FirefoxDriver();
        }

        else if (br.equals("chrome")) {
            // Chrome Browser
            System.setProperty("webdriver.chrome.driver",prop.getProperty("chrome"));
            driver = new ChromeDriver();
        }

        else if (br.equals("ie")) {
            // ie Browser
            System.setProperty("webdriver.ie.driver",prop.getProperty("ie"));
            driver = new InternetExplorerDriver();
        }

        // Global implicit Wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    @AfterClass
    public void tearDown()
    {
        driver.quit();

    }


    public void screenShot(WebDriver driver,String tname){
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs( OutputType.FILE );
        File target = new File( System.getProperty( "user.dir" )+ "\\ScreenShot\\"+tname+".png");
        try {
            FileUtils.copyFile( source,target );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
        System.out.println( "Screenshot Taken");

    }

    }






