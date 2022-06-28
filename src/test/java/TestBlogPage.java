import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class TestBlogPage extends TestPortio {

    @Test
    @DisplayName("Blog oldal lista lapozás ellenőrzése")
    @Description("Blog összes bejegyzés lapozás")
    @Severity(SeverityLevel.NORMAL)
    public void blogTest(TestInfo testInfo) throws InterruptedException {
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

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(allBlogPostPage.isLastPageButtonActive().contains("active"));
    }

    @Test
    @DisplayName("Blog oldal tartalom ellenőrzése")
    @Description("Blog összes bejegyzés cím ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void blogTitlesToFileTest(TestInfo testInfo) throws InterruptedException {
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

        addAttachment(testInfo.getDisplayName());

        Assertions.assertEquals(expected, util.read());

        util.fileDeleter();
    }
}
