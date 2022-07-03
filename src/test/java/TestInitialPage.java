import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

public class TestInitialPage extends TestPortio {

    @Test
    @Tag("Navigáció")
    @DisplayName("Navigáció az oldalra")
    @Description("Ez a teszt a helyes url címre navigálást fedi le")
    @Severity(SeverityLevel.CRITICAL)
    public void navigateTest(TestInfo testInfo) {
        String expected = "https://lennertamas.github.io/portio/";
        String actual = driver.getCurrentUrl();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Tag("AdatkezelésiNyilatkozatHasználata")
    @DisplayName("Terms and Conditions elfogadása")
    @Description("A Terms and conditions elfogadása")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsAcceptTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertFalse(basePage.isPopUpDisplayed());
    }

    @Test
    @Tag("AdatkezelésiNyilatkozatHasználata")
    @DisplayName("Terms and Conditions kilépés")
    @Description("A Terms and conditions kilépés gombjának megnyomása")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsExitTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsExit();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertFalse(basePage.isPopUpDisplayed());
    }

    @Test
    @Tag("AdatkezelésiNyilatkozatHasználata")
    @DisplayName("Terms and Conditions szöveg tartalma")
    @Description("A Terms and conditions szöveg tartalmának ellenőrzése, összehasonlítás egy .txt fájllal")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsMainTextTest(TestInfo testInfo) {
        util = new Util();
        util.setFileName("testData/termsandconditions.txt");
        String expected = util.read();
        String actual = basePage.getTermsAndConditionsText();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Tag("AdatkezelésiNyilatkozatHasználata")
    @DisplayName("Terms and conditions ablakon kívül kattintás")
    @Description("Azt teszteli, hogy eltűnik-e a Terms and conditions ablak, ha azon kívülre kattintunk")
    @Severity(SeverityLevel.CRITICAL)
    public void termsAndConditionsClickToSideTest(TestInfo testInfo) {
        basePage.clickOverlay();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(basePage.isPopUpDisplayed());
    }
}
