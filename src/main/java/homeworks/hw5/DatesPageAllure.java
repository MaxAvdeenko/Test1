package homeworks.hw5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import homeworks.hw4.ex2.DatesPageData;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;


public class DatesPageAllure {

    @FindBy(css = ".uui-navigation.nav.navbar-nav.m-l8 li a[data-toggle='dropdown']")
    private SelenideElement serviceButton;

    @FindBy(css = "[class='dropdown-menu']")
    private SelenideElement serviceMenu;

    @FindBy(css = ".ui-slider-handle")
    private ElementsCollection sliders;

    @FindBy(css = ".panel-body-list.logs li")
    private ElementsCollection log;

    @FindBy(css = "[class='dropdown-menu'] li")
    private ElementsCollection serviceMenuList;

    @Step("Assert browser title")
    public void openDatesPage(DatesPageData title) {
        serviceButton.click();
        serviceMenuList.get(1).click();
        assertEquals(getWebDriver().getTitle(), title.toString());
    }

    @Step("Check slider's logs")
    public void checkLogs(Integer leftSlider, Integer rightSlider) {
        log.get(1).shouldHave(Condition.text(leftSlider.toString()));
        log.get(0).shouldHave(Condition.text(rightSlider.toString()));
    }

    @Step("Set slider position")
    public void setSliderPosition(int left, int right) {
        if (left == 0) {
            replaceSliderToLeft(sliders.get(0));
        } else if (left == 100) {
            replaceSliderToRight(sliders.get(0));
        }
        if (right == 0) {
            replaceSliderToLeft(sliders.get(1));
        } else if (right == 100) {
            replaceSliderToRight(sliders.get(1));
        }
        if ((left > 0 && left < 100) | (right > 0 && right < 100)) replaceSlidersCustom(left, right);
    }

    @Step("Replace slider to left")
    private void replaceSliderToLeft(SelenideElement slider) {
        actions().dragAndDropBy(slider, -1000, 0).build().perform();
    }

    @Step("Replace slider to right")
    private void replaceSliderToRight(SelenideElement slider) {
        actions().dragAndDropBy(slider, 1000, 0).build().perform();
    }

    @Step("Replace slider to any position we want")
    private void replaceSlidersCustom(int left, int right) {
        actions().dragAndDropBy(sliders.get(0), -1000, 0).build().perform();
        int length = sliders.get(1).getLocation().getX() - sliders.get(0).getLocation().getX();
        double oneStep = length / 100.0;
        int leftPosition = (int) Math.round(oneStep * left) - 1;
        int rightPosition = length - (int) Math.round(((oneStep * right))-oneStep);
        actions().dragAndDropBy(sliders.get(0), leftPosition, 0).build().perform();
        actions().dragAndDropBy(sliders.get(1), -rightPosition, 0).build().perform();
    }
}
