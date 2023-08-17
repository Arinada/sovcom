package ui.tests;

import org.junit.Test;
import ui.steps.VacanciesSteps;

public class VacanciesTest extends BaseTest{
    private VacanciesSteps vacanciesSteps = new VacanciesSteps();

    @Test
    public void checkVacanciesAfterFilteringByCityAndCompany() {
        vacanciesSteps
                .verifyVacanciesAfterFilteringByCityAndCompany("Москва", "Совкомбанк Технологии");
    }
}
