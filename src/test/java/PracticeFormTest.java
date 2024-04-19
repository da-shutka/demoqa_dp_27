import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void settingsBeforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
    }
    
    @Test
    void fillFullPracticeFormTest() {
        open("automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("anna.ivanova@mail.ru");
        $("label[for=gender-radio-2]").click(); // female
        $("#userNumber").setValue("9231552110");

        $("#dateOfBirthInput").click();
        $("#dateOfBirth .react-datepicker__month-select [value='8']").click(); // September
        $("#dateOfBirth .react-datepicker__year-select [value='1991']").click();
        $("#dateOfBirth .react-datepicker__month .react-datepicker__day--016").click();

        $("#subjectsInput").setValue("accounting").pressEnter();

        $("label[for=hobbies-checkbox-1]").click(); // Sports
        $("label[for=hobbies-checkbox-3]").click(); // Music

        $("#uploadPicture").uploadFile(new File("src/test/resources/comma.jpg"));

        $("#currentAddress").setValue("Test address");
        $("#state input").setValue("Rajasthan").pressEnter();
        $("#city input").setValue("Jaiselmer").pressEnter();

        $("#submit").click();

        // Check submitting form
        $x("//td[contains(text(),'Student Name')]/../td[2]").shouldHave(text("Anna Ivanova"));
        $x("//td[contains(text(),'Student Email')]/../td[2]").shouldHave(text("anna.ivanova@mail.ru"));
        $x("//td[contains(text(),'Gender')]/../td[2]").shouldHave(text("Female"));
        $x("//td[contains(text(),'Mobile')]/../td[2]").shouldHave(text("9231552110"));
        $x("//td[contains(text(),'Date of Birth')]/../td[2]").shouldHave(text("16 September,1991"));
        $x("//td[contains(text(),'Subjects')]/../td[2]").shouldHave(text("Accounting"));
        $x("//td[contains(text(),'Hobbies')]/../td[2]").shouldHave(text("Sports, Music"));
        $x("//td[contains(text(),'Picture')]/../td[2]").shouldHave(text("comma.jpg"));
        $x("//td[contains(text(),'Address')]/../td[2]").shouldHave(text("test address"));
        $x("//td[contains(text(),'State and City')]/../td[2]").shouldHave(text("Rajasthan Jaiselmer"));
    }
}
