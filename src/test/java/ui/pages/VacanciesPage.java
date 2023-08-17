package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class VacanciesPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'header-contacts')]//a[@href='/vacancies']")
    private WebElement vacanciesBtn;

    @FindBy(css = "#input-283")
    private WebElement cityDropDownList;

    @FindBy(css = "#input-290")
    private WebElement companyDropDownList;

    @FindBy(css = ".section-vacancies.full-width")
    private WebElement blockWithVacancies;

    public VacanciesPage pressOnVacanciesButton(){
        wait.until(ExpectedConditions.visibilityOf(vacanciesBtn));
        vacanciesBtn.click();
        return this;
    }

    public VacanciesPage selectCity(String cityName){
        wait.until(ExpectedConditions.visibilityOf(cityDropDownList));
        cityDropDownList.click();
        cityDropDownList.sendKeys(cityName);
        WebElement firstCityFromList = driver.findElement(By.xpath("//div[contains(@class, 'menuable__content__active')]//div[contains(@class, 'v-list-item')]"));
        firstCityFromList.click();
        return this;
    }

    public VacanciesPage selectCompany(String companyName){
        wait.until(ExpectedConditions.visibilityOf(companyDropDownList));
        cityDropDownList.click();
        WebElement requiredCityElement = driver.findElement(By.xpath("//div[contains(@class, 'list-item-flexbox')]//div[text()='Совкомбанк Технологии']"));
        requiredCityElement.click();
        return this;
    }

    public List<String> getVacanciesDescription() {
        wait.until(ExpectedConditions.visibilityOf(blockWithVacancies));
        List<WebElement> vacanciesElements = blockWithVacancies.findElements(By.xpath("//div[contains(@class, 'section-vacancies')]//a[contains(@href,'/vacancies/')]"));
        return vacanciesElements
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
