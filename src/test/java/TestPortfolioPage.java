import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.File;

public class TestPortfolioPage extends TestPortio {

    @Test
    @DisplayName("Képek ellenőrzése")
    @Description("Képek ellenőrzése a Work oldalon")
    @Severity(SeverityLevel.NORMAL)
    public void portfolioImagesTest(TestInfo testInfo) {
        util = new Util();
        String testFileName = "src/test/resources/";
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");
        PortfolioPage portfolioPage = landingPage.clickWorkButton();
        String[] portfolioImageSources = portfolioPage.getImages();
        String[] portfolioImageNames = portfolioPage.getImagesName();

        for (int i = 0; i < portfolioImageSources.length; i++) {
            String imageName = testFileName + portfolioImageNames[i].replace(" ", "") + ".png";
            util.setFileName(imageName);
            String image = portfolioImageSources[i];
            addAttachment(testInfo.getDisplayName());
            util.imageSave(image);
            Assertions.assertTrue(new File(imageName).exists());
            util.fileDeleter();
        }
    }
}
