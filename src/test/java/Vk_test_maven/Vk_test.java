package Vk_test_maven;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Vk_test {

    @BeforeAll
    public static void setUp(){
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open("https://vk.com/");
    }

    @Test
    public void checkIfIOnAStartPage(){
        element("title").shouldHave(attribute(
                "text",
                "Добро пожаловать | ВКонтакте"));
    }

    // Проверка негативного ввода у полей e-mail и password
    @Test
    public void negative_SingInForm() {
        element("#index_email").setValue("mail");
        element("#index_pass").setValue("pass").pressEnter();

        // Должна появиться страница входа
        element("title").shouldHave(attribute(
                "text",
                "Вход | ВКонтакте"));

        // Страница должна содержать текст о неправильности ввода логина и пароля
        element(By.className("msg_text")).shouldHave(text("Не удаётся войти. " +
                "Пожалуйста, проверьте правильность написания логина и пароля."));
    }

}
