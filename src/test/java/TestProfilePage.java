import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.testng.asserts.SoftAssert;

public class TestProfilePage extends TestPortio {

    @Test
    @Tags({@Tag("Kijelentkezés"), @Tag("AdatVagyAdatokTörlése")})
    @DisplayName("Profil törlése automatikus kijelentkezés")
    @Description("Regisztrált profil törlése az adatbázisból, automatikus kijelentkezés ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void deleteProfileLogoutTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        ProfilePage profilePage = landingPage.clickProfileButton();
        profilePage.clickDeleteAccountButton();
        loginPage = profilePage.clickIAmSureButton();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(loginPage.isLoginWindowDisplayed());
    }

    @Test
    @Tags({@Tag("Kijelentkezés"), @Tag("AdatVagyAdatokTörlése")})
    @DisplayName("Profil törlése ellenőrzése")
    @Description("Regisztrált profil törlése az adatbázisból, törlés ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void deleteProfileTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        ProfilePage profilePage = landingPage.clickProfileButton();
        profilePage.clickDeleteAccountButton();
        loginPage = profilePage.clickIAmSureButton();
        loginPage.loginProcess("viewxy", "pass123");

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(loginPage.isLoginWindowDisplayed());
    }

    @Test
    @Tags({@Tag("IsmételtÉsSorozatosAdatbevitel"), @Tag("ÚjAdatBevitel"), @Tag("MeglévőAdatMódosítás"), @Tag("AdatVagyAdatokTörlése")})
    @DisplayName("Profil módosítása")
    @Description("Felhasználói profil módosítása")
    @Severity(SeverityLevel.NORMAL)
    public void editProfileTest(TestInfo testInfo) {
        SoftAssert softAssert = new SoftAssert();
        util = new Util();
        String expected = "Profile Edited!";
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        ProfilePage profilePage = landingPage.clickProfileButton();
        util.setFileName("src/testData/profileInfoData.csv");


        String[] profileUpdateArray = util.read().split(System.lineSeparator());
        for (String s : profileUpdateArray) {
            String name = s.split(",")[0];
            String bio = s.split(",")[1];
            String phoneNumber = s.split(",")[2];

            profilePage.fillNameField(name);
            profilePage.fillBioField(bio);
            profilePage.fillPhoneNumber(phoneNumber);
            profilePage.clickSaveProfileButton();

            String actual = profilePage.getSaveProfileMessage();

            addAttachment(testInfo.getDisplayName());
            softAssert.assertEquals(expected, actual);
            softAssert.assertAll();
        }
    }
}
