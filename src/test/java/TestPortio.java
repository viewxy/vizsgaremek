import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class TestPortio {
    WebDriver driver;
    BasePage basePage;
    Util util;


    @BeforeEach
    public void Setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        // options.addArguments("--headless");
        // options.addArguments("--window-size=1920,1080");
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        basePage = new BasePage(driver);
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        basePage.navigate();
    }


    @Test
    @Epic("Put epic name here")
    @Story("Story or stories")
    @Description("additional info, description")
    @Severity(SeverityLevel.NORMAL)
    public void navigateTest() {
        String expected = "https://lennertamas.github.io/portio/";
        String actual = driver.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void termsAndConditionsAcceptTest() {
        basePage.clickTermsAndConditionsAccept();

        Assertions.assertFalse(basePage.isPopUpDisplayed());
    }

    @Test
    public void termsAndConditionsExitTest() {
        basePage.clickTermsAndConditionsExit();

        Assertions.assertFalse(basePage.isPopUpDisplayed());
    }

    @Test
    public void termsAndConditionsClickToSideTest() {
        basePage.clickOverlay();

        Assertions.assertTrue(basePage.isPopUpDisplayed());
    }

    @Test
    public void termsAndConditionsMainTextTest() {
        util = new Util();
        String expected = util.read("./termsandconditions.txt");
        String actual = basePage.getTermsAndConditionsText();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void registrationTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "hey hey");

        String actual = registerPage.checkRegistrationValidation();

        Assertions.assertEquals("User registered!", actual);
    }

    @Test
    public void registrationEmptyEverythingTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("", "", "", "");

        String actual = registerPage.checkRegistrationValidation();

        Assertions.assertNotEquals("User registered!", actual);
    }

    @Test
    public void registrationEmptyDescriptionTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        String actual = registerPage.checkRegistrationValidation();

        Assertions.assertNotEquals("User registered!", actual);
    }

    @Test
    public void loginSuccessfulTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");
        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        Assertions.assertTrue(landingPage.isNavBarDisplayed());
    }

    @Test
    public void loginWrongUserTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");
        registerPage.clickRegisterSubmitButton();

        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("view-xy", "pass123");

        Assertions.assertFalse(landingPage.isNavBarDisplayed());
    }

    @Test
    public void loginWrongPasswordTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();

        loginPage.loginProcess("viewxy", "pass1");

        Assertions.assertTrue(loginPage.isAlertMessageDisplayed());
    }

    @Test
    public void loginEmptyTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("", "");

        Assertions.assertFalse(landingPage.isNavBarDisplayed());
    }

    @Test
    public void logOutTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        landingPage.clickLogoutButton();

        boolean actual = loginPage.isLoginWindowDisplayed();

        Assertions.assertTrue(actual);
    }

    @Test
    public void resumesTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        //continue
    }

    @Test
    public void blogTest() throws InterruptedException {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        BlogPage blogPage = landingPage.clickBlogButton();

        Thread.sleep(1000);
        AllBlogPostPage allBlogPostPage = blogPage.clickSeeAllButton();
        while (allBlogPostPage.isNextButtonDisplayed()) {
            allBlogPostPage.clickNextButton();
        }

        Assertions.assertTrue(allBlogPostPage.isLastPageButtonActive().contains("active"));
    }

    @Test
    public void registerMultipleTest() {
        util = new Util();
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();

        String[] registerList = util.read("./MOCK_DATA.csv").split("\n");
        for (String s : registerList) {
            String username = s.split(",")[0];
            String password = s.split(",")[1];
            String email = s.split(",")[2];
            registerPage.registerProcess(username, password, email, "");
            String actual = registerPage.checkRegistrationValidation();

            Assertions.assertEquals("User registered!", actual);
        }
    }

    @Test
    public void blogTitlesToFileTest() throws InterruptedException {
        util = new Util();
        String testFileName = "blogPostTitles.txt";
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");
        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        BlogPage blogPage = landingPage.clickBlogButton();

        Thread.sleep(1000);
        AllBlogPostPage allBlogPostPage = blogPage.clickSeeAllButton();
        util.setFileName(testFileName);
        while (true) {
            for (String actual : allBlogPostPage.getArrayOfBlogTitles()) {
                util.write(actual, "\n");
            }
            if (!allBlogPostPage.isNextButtonDisplayed()) {
                break;
            }
            allBlogPostPage.clickNextButton();
        }
        File testFile = new File(testFileName);


        Assertions.assertTrue(testFile.length() > 0);
    }


    @AfterEach
    public void dispose() {
        driver.quit();

    }
}
