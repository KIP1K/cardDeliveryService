package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.WithText;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class TestCardDeliveryService {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldTestSomething() {
        open("http://localhost:9999");

        $(".input__control").setValue("Владивосток");
        String planningDate = generateDate(4, "dd.MM.yyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);

        $("[name=name]").setValue("Пучков Иван");
        $("[name=phone]").setValue("+79000000000");
        $(".checkbox__text").click();
        $(".button__text").click();
        $("[data-test-id=notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + planningDate));
    }
}
