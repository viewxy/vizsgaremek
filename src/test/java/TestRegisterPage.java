import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.testng.asserts.SoftAssert;

public class TestRegisterPage extends TestPortio {

    @Test
    @Tags({@Tag("Regisztráció"), @Tag("ÚjAdatBevitel")})
    @DisplayName("Regisztráció helyes adatok kitöltésével")
    @Description("Regisztráció az összes adat helyes kitöltésével")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "hey hey");

        String actual = registerPage.checkRegistrationValidation();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertEquals("User registered!", actual);
    }

    @Test
    @Tags({@Tag("Regisztráció"), @Tag("ÚjAdatBevitel")})
    @DisplayName("Regisztráció üres adatokkal")
    @Description("Regisztráció üres adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationEmptyEverythingTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("", "", "", "");

        String actual = registerPage.checkRegistrationValidation();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertNotEquals("User registered!", actual);
    }

    @Test
    @Tags({@Tag("Regisztráció"), @Tag("ÚjAdatBevitel")})
    @DisplayName("Regisztráció Description mező üresen hagyva")
    @Description("Regisztráció helyes adatokkal, a Description mező üresen hagyva")
    @Severity(SeverityLevel.NORMAL)
    public void registrationEmptyDescriptionTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        String actual = registerPage.checkRegistrationValidation();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertEquals("User registered!", actual);
    }

    @Test
    @Tags({@Tag("IsmételtÉsSorozatosAdatbevitel"), @Tag("ÚjAdatBevitel"), @Tag("MeglévőAdatMódosítás"), @Tag("AdatVagyAdatokTörlése")})
    @DisplayName("Sorozatos regisztráció")
    @Description("Sorozatos regisztráció fájlból adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void registerMultipleTest(TestInfo testInfo) {
        SoftAssert softAssert = new SoftAssert();
        util = new Util();
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        util.setFileName("testData/registrationData.csv");
        String[] registerList = util.read().split(System.lineSeparator());
        for (String s : registerList) {
            String username = s.split(",")[0];
            String password = s.split(",")[1];
            String email = s.split(",")[2];
            registerPage.registerProcess(username, password, email, "");
            String actual = registerPage.checkRegistrationValidation();

            addAttachment(testInfo.getDisplayName());
            softAssert.assertEquals("User registered!", actual);
        }
        softAssert.assertAll();
    }

    @Test
    @Tags({@Tag("IsmételtÉsSorozatosAdatbevitel"), @Tag("ÚjAdatBevitel"), @Tag("MeglévőAdatMódosítás"), @Tag("AdatVagyAdatokTörlése")})
    @DisplayName("Sorozatos regisztráció invalid email cím")
    @Description("Sorozatos regisztráció különböző, helytelen email címekkel")
    @Severity(SeverityLevel.CRITICAL)
    public void registerEmailMultipleTest(TestInfo testInfo) {
        SoftAssert softAssert = new SoftAssert();
        util = new Util();
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        util.setFileName("testData/invalidEmailRegistrationData.csv");
        String[] registerList = util.read().split(System.lineSeparator());
        for (String s : registerList) {
            String username = s.split(",")[0];
            String password = s.split(",")[1];
            String email = s.split(",")[2];
            registerPage.registerProcess(username, password, email, "");
            String actual = registerPage.checkRegistrationValidation();

            addAttachment(testInfo.getDisplayName());
            softAssert.assertNotEquals("User registered!", actual);
        }
        softAssert.assertAll();
    }
}
