package homeworks.hw4.ex1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import homeworks.hw4.ex1.enumsWithDiffElementsData.NatureElements;
import homeworks.hw4.ex1.enumsWithDiffElementsData.Colors;
import homeworks.hw4.ex1.enumsWithDiffElementsData.DiffElPageData;
import homeworks.hw4.ex1.enumsWithDiffElementsData.Metals;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public class DiffElPageWithSelenide {
    @FindBy(css = "li.dropdown.open li")
    private List<SelenideElement> serviceMenuList;

    @FindBy(css = ".uui-navigation.nav.navbar-nav.m-l8 li a[data-toggle='dropdown']")
    private SelenideElement serviceButton;

    @FindBy(css = "ul > li:nth-child(3) > a")
    private SelenideElement serviceButtonLeft;

    @FindBy(css = "ul > li.menu-title > a > span")
    private SelenideElement serviceMenuLeft;

    @FindBy(css = "div.main-content div:nth-child(2)")
    private SelenideElement checkboxes;

    @FindBy(css = "div.main-content div:nth-child(3) label")
    private List<SelenideElement> radiosList;

    @FindBy(css = ".colors option")
    private List<SelenideElement> colorsList;

    // TODO Pay attention on naming, please.
    @FindBy(css = "[name='Default Button']")
    private SelenideElement defaultButton;

    @FindBy(css = "[type='button']")
    private SelenideElement button;

    @FindBy(css = "[name='log-sidebar']")
    private SelenideElement rightSection;

    @FindBy(css = "#mCSB_1_container")
    private SelenideElement leftSection;

    @FindBy(css = ".panel-body-list.logs li")
    private List<SelenideElement> listLog;

    @FindBy(css = "div.main-content div:nth-child(2) label")
    private List<SelenideElement> boxList;

    public void checkDiffElPage(DiffElPageData title) {
        serviceButton.click();
        serviceMenuList.get(6).click();
        assertEquals(getWebDriver().getTitle(), title.toString());

    }

    public void checkDiffElPageInterface() {
        assertEquals(boxList.size(), 4);
        // TODO I assume that Selenid allows us to mare this verification without cycle.
        for (SelenideElement checkbox : boxList) {
            checkbox.shouldHave(Condition.visible);
        }

        for (SelenideElement radio : radiosList) {
            radio.shouldHave(Condition.visible);
        }
        assertEquals(radiosList.size(), 4);
        // TODO It is not really great idea to wind element it PO methods...
        $(".colors").should(Condition.visible);
        defaultButton.should(Condition.visible);
        button.should(Condition.visible);
    }

    public void checkForRightSection() {
        rightSection.should(Condition.visible);
    }

    public void checkForLeftSection() {
        leftSection.should(Condition.visible);
    }

    public void selectCheckboxes() {
        boxList.get(0).click();
        boxList.get(2).click();
    }

    public void checkboxCorrectLog(NatureElements wind, NatureElements water) {
        listLog.get(0).shouldHave(text(wind.toString()));
        listLog.get(1).shouldHave(text(water.toString()));
    }

    public void selectRadio() {
        radiosList.get(3).click();
    }

    public void radiosCorectLog(Metals selen) {
        listLog.get(0).shouldHave(text(selen.toString()));
    }

    public void selectColor() {
        $(".colors").click();
        colorsList.get(3).click();
    }

    public void colorsCorrectLog(Colors yellow) {
        listLog.get(0).shouldHave(text(yellow.toString()));
    }

    public void unselectCheckboxes() {
        boxList.get(0).click();
        boxList.get(2).click();
    }

    public void checkLog(NatureElements wind, NatureElements water) {
        listLog.get(0).shouldHave(text(wind.toString()));
        listLog.get(1).shouldHave(text(water.toString()));
        listLog.get(0).shouldHave(text("false"));
        listLog.get(1).shouldHave(text("false"));
    }
}
