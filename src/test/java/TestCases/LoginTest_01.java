package TestCases;

import TestBase.Base;
import org.testng.Assert;
import org.testng.annotations.*;
import PageObjects.LoginPage;




    public class LoginTest_01 extends Base


    {

        @Test
        public void loginTest()
        {
            log.info( "************  LoginTest***********" );
            driver.get(prop.getProperty("url"  ));


            LoginPage lp=new LoginPage(driver);

            log.info( "***********Username***********" );

            lp.setUserName(prop.getProperty( "useremail" ));


            lp.setPassword("admin");
            lp.clickLogin();

            log.info( "*********Title test**********");

            String exp_title="Dashboard / nopCommerce administration";

            String act_title=driver.getTitle();

            if(exp_title.equals(act_title))
            {
                log.info( "Title test Passed" );
                Assert.assertTrue(true );
            }
            else
            {
                log.error( "Title mismatch");
                log.warn( act_title );
                screenShot( driver,"loginTest");

                Assert.assertTrue(false);


            }
        }


    }



