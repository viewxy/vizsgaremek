import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        basePage = new BasePage(driver);
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        basePage.navigate();
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    @DisplayName("Navigáció az oldalra")
    @Description("Ez a teszt a helyes url címre navigálást fedi le")
    @Severity(SeverityLevel.CRITICAL)
    public void navigateTest() {
        String expected = "https://lennertamas.github.io/portio/";
        String actual = driver.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
        saveFailureScreenShot(driver);
    }

    @Test
    @DisplayName("Terms and Conditions elfogadása")
    @Description("A Terms and conditions elfogadása")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsAcceptTest() {
        basePage.clickTermsAndConditionsAccept();

        Assertions.assertFalse(basePage.isPopUpDisplayed());
    }

    @Test
    @DisplayName("Terms and Conditions kilépés")
    @Description("A Terms and conditions kilépés gombjának megnyomása")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsExitTest() {
        basePage.clickTermsAndConditionsExit();

        Assertions.assertFalse(basePage.isPopUpDisplayed());
    }

    @Test
    @DisplayName("Terms and Conditions szöveg tartalma")
    @Description("A Terms and conditions szöveg tartalmának ellenőrzése, összehasonlítás egy .txt fájllal")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsMainTextTest() {
        util = new Util();
        String expected = util.read("src/test/resources/termsandconditions.txt");
        String actual = basePage.getTermsAndConditionsText();

        Assertions.assertEquals(expected, actual);
        saveFailureScreenShot(driver);
    }

    @Test
    @DisplayName("Terms and conditions ablakon kívül kattintás")
    @Description("Azt teszteli, hogy eltűnik-e a Terms and conditions ablak, ha azon kívülre kattintunk")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsClickToSideTest() {
        basePage.clickOverlay();

        Assertions.assertTrue(basePage.isPopUpDisplayed());
    }

    @Test
    @DisplayName("Regisztráció helyes adatok kitöltésével")
    @Description("Regisztráció az összes adat helyes kitöltésével")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "hey hey");

        String actual = registerPage.checkRegistrationValidation();

        Assertions.assertEquals("User registered!", actual);
    }

    @Test
    @DisplayName("Regisztráció üres adatokkal")
    @Description("Regisztráció üres adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationEmptyEverythingTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("", "", "", "");

        String actual = registerPage.checkRegistrationValidation();

        Assertions.assertNotEquals("User registered!", actual);
    }

    @Test
    @DisplayName("Regisztráció Description mező üresen hagyva")
    @Description("Regisztráció helyes adatokkal, a Description mező üresen hagyva")
    @Severity(SeverityLevel.NORMAL)
    public void registrationEmptyDescriptionTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        String actual = registerPage.checkRegistrationValidation();

        Assertions.assertNotEquals("User registered!", actual);
    }

    @Test
    @DisplayName("Sorozatos reisztráció")
    @Description("Sorozatos regisztráció fájlból adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void registerMultipleTest() {
        util = new Util();
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();

        String[] registerList = util.read("src/test/resources/MOCK_DATA.csv").split(System.lineSeparator());
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
    @DisplayName("Sikeres Sign in helyes adatokkal")
    @Description("Bejelentkezés a regisztrációnál megadottakkal egyező adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void loginSuccessfulTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");
        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        Assertions.assertTrue(landingPage.isNavBarDisplayed());
    }

    @Test
    @DisplayName("Sikertelen Sign in helytelen username-el")
    @Description("Bejelentkezés helytelen felhasználóvévvel")
    @Severity(SeverityLevel.CRITICAL)
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
    @DisplayName("Sikertelen Sign in helytelen passworddel")
    @Description("Bejelentkezés helytelen jelszóval")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWrongPasswordTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();

        loginPage.loginProcess("viewxy", "pass1");

        Assertions.assertTrue(loginPage.isAlertMessageDisplayed());
    }

    @Test
    @DisplayName("Sikertelen Sign in üres adatokkal")
    @Description("Bejelentkezés üres adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void loginEmptyTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("", "");

        Assertions.assertFalse(landingPage.isNavBarDisplayed());
    }

    @Test
    @DisplayName("Logout")
    @Description("Kijelentkezés")
    @Severity(SeverityLevel.CRITICAL)
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
    @DisplayName("Blog oldal lista lapozás ellenőrzése")
    @Description("Blog összes bejegyzés lapozás")
    @Severity(SeverityLevel.NORMAL)
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
    @DisplayName("Blog oldal tartalom ellenőrzése")
    @Description("Blog összes bejegyzés cím ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void blogTitlesToFileTest() throws InterruptedException {
        util = new Util();
        String testFileName = "src/test/resources/blogPostTitles.txt";
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
                util.write(actual, System.lineSeparator());
            }
            if (!allBlogPostPage.isNextButtonDisplayed()) {
                break;
            }
            allBlogPostPage.clickNextButton();
        }

        String expected = "Markdown Formatting Demo" + System.lineSeparator() +
                "Designer Conference at Florida 2020" + System.lineSeparator() +
                "Benjamin Franklins thoughts about new designers" + System.lineSeparator() +
                "Designers thoughts about mobile UI" + System.lineSeparator() +
                "How to become acreative designer" + System.lineSeparator() +
                "New designers limitations" + System.lineSeparator() +
                "Things you must know as a designer" + System.lineSeparator() +
                "World's Most Famous App Developers and Designers" + System.lineSeparator() +
                "You must know this before becoming a designer";

        Assertions.assertEquals(expected, util.read(testFileName));

        File deleteFile = new File(testFileName);
        deleteFile.delete();
    }

    @Test
    @DisplayName("Profil törlése automatikus kijelentkezés")
    @Description("Regisztrált profil törlése az adatbázisból, automatikus kijelentkezés ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void deleteProfileLogoutTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        ProfilePage profilePage = landingPage.clickProfileButton();
        profilePage.clickDeleteAccountButton();
        loginPage = profilePage.clickIAmSureButton();

        Assertions.assertTrue(loginPage.isLoginWindowDisplayed());
    }

    @Test
    @DisplayName("Profil törlése ellenőrzése")
    @Description("Regisztrált profil törlése az adatbázisból, törlés ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void deleteProfileTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        ProfilePage profilePage = landingPage.clickProfileButton();
        profilePage.clickDeleteAccountButton();
        loginPage = profilePage.clickIAmSureButton();
        loginPage.loginProcess("viewxy", "pass123");

        Assertions.assertTrue(loginPage.isLoginWindowDisplayed());
    }

    @Test
    @DisplayName("h2 Heading szövegstílus ellenőrzése")
    @Description("Az oldalon konzisztens a  h2 heading-ek szövegstílusa, betűtípusa")
    @Severity(SeverityLevel.MINOR)
    public void h2HeadingFontTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");
        String expected = "500 50px / 60px \"yeseva one\", cursive";
        String[] actualArray = landingPage.getFontStyle(landingPage.getH2Headings());
        for (String actual : actualArray) {
            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    @DisplayName("h3 Heading szövegstílus ellenőrzése")
    @Description("Az oldalon konzisztens a  h3 heading-ek szövegstílusa, betűtípusa")
    @Severity(SeverityLevel.MINOR)
    public void h3HeadingFontTest() {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");
        String expected = "500 30px / 36px \"yeseva one\", cursive";
        String[] actualArray = landingPage.getFontStyle(landingPage.getH3Headings());
        for (String actual : actualArray) {
            Assertions.assertEquals(expected, actual);
        }
    }


    @AfterEach
    public void dispose() {
        driver.quit();
    }
}
