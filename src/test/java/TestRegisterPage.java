import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class TestRegisterPage extends TestPortio {

    @Test
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
    @DisplayName("Sorozatos regisztráció")
    @Description("Sorozatos regisztráció fájlból adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void registerMultipleTest(TestInfo testInfo) {
        util = new Util();
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        util.setFileName("src/test/resources/MOCK_DATA.csv");
        String[] registerList = util.read().split(System.lineSeparator());
        for (String s : registerList) {
            String username = s.split(",")[0];
            String password = s.split(",")[1];
            String email = s.split(",")[2];
            registerPage.registerProcess(username, password, email, "");
            String actual = registerPage.checkRegistrationValidation();

            addAttachment(testInfo.getDisplayName());
            Assertions.assertEquals("User registered!", actual);
        }
    }
}
