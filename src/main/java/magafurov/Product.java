package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Product {
    int current;
    int last;
    WebPage productPage;

    {
        try {
            productPage = new WebPage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void checkPageProduct(String urlToCheck) throws Throwable{
        Thread.sleep(1000);
        this.productPage.seleniumDriver.get(urlToCheck);
        List<String> list = getProductList();
        for (int i = 0; i < list.size(); i++) {
            String row = list.get(i);
            Review review = new Review();
            review.checkPage(row);
        }
        try {
            current = Integer.parseInt(productPage.seleniumDriver.findElement(By.xpath("//li[contains(@class,'pager-current')]")).getText());
            try {
                last = Integer.parseInt(productPage.seleniumDriver.findElement(By.xpath("//li[@class='pager-current last']")).getText());
            } catch (Exception ex) {
                last = Integer.parseInt(productPage.seleniumDriver.findElement(By.xpath("//li[@class='pager-last last']/a")).getText());
            }
        } catch (Exception exc) {
            current = 0;
            last = 0;
        }
        String newUrl;
        if (!(current == 0 || current == last)) {
            if (current > 1) {
                newUrl = urlToCheck.substring(0,urlToCheck.lastIndexOf("=")) + "=" + current;
                System.out.println(newUrl);
                checkPageProduct(newUrl);
            } else {
                newUrl = urlToCheck + "?page=" + current;
                checkPageProduct(newUrl);
            }
        }
    }

    private List<String> getProductList() {
        WebElement table = productPage.seleniumDriver.findElementByClassName("srch-result-nodes");
        List<String> allRows = new ArrayList<>();
        for (WebElement row : table.findElements(By.tagName("li"))) {
            try {
                allRows.add(row.findElement(By.xpath("./div/a")).getAttribute("href"));
            } catch (Exception e) {
                System.out.println("Test4");
            }
        }
        return allRows;
    }
}
