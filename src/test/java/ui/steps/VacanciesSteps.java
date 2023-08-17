package ui.steps;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import ui.pages.VacanciesPage;

import java.util.List;

public class VacanciesSteps {
    private VacanciesPage vacanciesPage = new VacanciesPage();

    public void verifyVacanciesAfterFilteringByCityAndCompany(String city, String company){
        List<String> vacanciesDescriptionByCityAndCompany = vacanciesPage
                .pressOnVacanciesButton()
                .selectCity(city)
                .selectCompany(company)
                .getVacanciesDescription();

        vacanciesDescriptionByCityAndCompany.forEach(x -> {
            Assert.assertThat(x, CoreMatchers.containsString(city));
        });

        vacanciesDescriptionByCityAndCompany.forEach(x -> {
            Assert.assertThat(x, CoreMatchers.containsString(company));
        });
    }
}
