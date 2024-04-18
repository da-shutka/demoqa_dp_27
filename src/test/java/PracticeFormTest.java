import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("anna.ivanova@mail.ru");
        $(By.cssSelector("label[for=gender-radio-2]")).click(); // female
        $("#userNumber").setValue("9231552110");

        $("#dateOfBirthInput").click();
        $("#dateOfBirth .react-datepicker__month-select [value='8']").click(); // September
        $("#dateOfBirth .react-datepicker__year-select [value='1991']").click();
        $("#dateOfBirth .react-datepicker__month .react-datepicker__day--016").click();

        $("#subjectsInput").setValue("accounting").pressEnter();

        $(By.cssSelector("label[for=hobbies-checkbox-1]")).click(); // Sports
        $(By.cssSelector("label[for=hobbies-checkbox-3]")).click(); // Music

        $("#uploadPicture").uploadFile(new File("src/test/sources/comma.jpg"));

        $("#currentAddress").setValue("Test address");
        $("#state input").setValue("Rajasthan").pressEnter();
        $("#city input").setValue("Jaiselmer").pressEnter();

        $("#submit").click();

        // Check submitting form
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Student Name')]/parent::tr/td[2]").shouldHave(text("Anna Ivanova"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Student Email')]/parent::tr/td[2]").shouldHave(text("anna.ivanova@mail.ru"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Gender')]/parent::tr/td[2]").shouldHave(text("Female"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Mobile')]/parent::tr/td[2]").shouldHave(text("9231552110"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Date of Birth')]/parent::tr/td[2]").shouldHave(text("16 September,1991"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Subjects')]/parent::tr/td[2]").shouldHave(text("Accounting"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Hobbies')]/parent::tr/td[2]").shouldHave(text("Sports, Music"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Picture')]/parent::tr/td[2]").shouldHave(text("comma.jpg"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'Address')]/parent::tr/td[2]").shouldHave(text("test address"));
        $x("//div[@class='table-responsive']//tr/td[1][contains(text(),'State and City')]/parent::tr/td[2]").shouldHave(text("Rajasthan Jaiselmer"));
    }
}
