package guruqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestGithub {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkingPage() {

        //Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");

        //Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".wiki-more-pages-link").$(".mx-auto").scrollTo().click();

//        Вариант поиска по номеру тега, который содержит текст.
//        Но он может быть нестабилен, так как может появиться ещё элемент <a> и нумерация собьётся.
//        $("#wiki-pages-box").$("a", 19).shouldHave(Condition.text("SoftAssertions"));

        $x("//div[@id='wiki-pages-box']//li//a[text()='SoftAssertions']").shouldBe(Condition.visible);

        //Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave
                (Condition.text("JUnit5 extension - com.codeborne.selenide.junit5.SoftAssertsExtension"));
    }
}
