package api;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@RunWith(DataProviderRunner.class)
public class CleanuriTest {
    private final String SERVICE_URL = "https://cleanuri.com/api/v1/shorten";

    @DataProvider
    public static Object[] positiveTestData() {
        return getUrlArrayFromFile("positive-cases.txt");
    }

    @DataProvider
    public static Object[] negativeTestData() {
        return getUrlArrayFromFile("negative-cases.txt");
    }

    private static String[] getUrlArrayFromFile(String fileName) {
        ArrayList<String> urls = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for(String url; (url = br.readLine()) != null; ) {
                urls.add(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] urlsArray = new String[urls.size()];
        for (int i=0;i<urls.size();i++) {
            urlsArray[i] = urls.get(i);
        }

        return urlsArray;
    }

    @Test
    @UseDataProvider("positiveTestData")
    public void checkPositiveCases(String longUrl) {
        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .body("url=" + longUrl)
                .when()
                .post(SERVICE_URL);
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    @UseDataProvider("negativeTestData")
    public void checkNegativeCases(String longUrl) {
        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .body("url=" + longUrl)
                .when()
                .post(SERVICE_URL);
        Assert.assertEquals(400, response.statusCode());
    }
}
