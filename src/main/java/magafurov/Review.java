package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Review {

    int pagerCurrent;
    int pagerLast;
    WebPage reviewPage;


    public Review(int pagerCurrent, int pagerLast) {
        {
            try {
                reviewPage = new WebPage();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        this.pagerCurrent = pagerCurrent;
        this.pagerLast = pagerLast;
    }

    public void checkPage(String urlToCheck) throws Throwable{
        this.reviewPage.seleniumDriver.get(urlToCheck);
        System.out.println(urlToCheck);
        Thread.sleep(15000);
        List<WebElement> list = getReviewsList();
        String category = "";
        String title = "";
        String brand = "";
        try {
            WebElement temp  = this.reviewPage.seleniumDriver.findElement(By.xpath("//div[contains(@class,'voc-group vid-1')]"));
            category = temp.findElement(By.xpath("./a")).getText();
            title = this.reviewPage.seleniumDriver.findElement(By.xpath("//h1/span")).getText();
            WebElement temp1  = this.reviewPage.seleniumDriver.findElement(By.xpath("//div[contains(@class,'voc-group vid-17')]"));
            brand = temp1.findElement(By.xpath("./span[2]/a")).getText();
        } catch (Exception e) {
            System.out.println("First parse failed");
            System.out.println(reviewPage.seleniumDriver.getPageSource());
        }
        System.out.println("category= " + category);
        System.out.println("title= " + title);
        System.out.println("brand= " + brand);
        for (int i = 0; i < list.size(); i++) {
            WebElement row = list.get(i);
            System.out.println("Parsing review");
            ReviewInfo info = new ReviewInfo(row);
            System.out.println("Review parsed");
            Csv.writeToFile(info.authorName, info.stars, info.date, info.commentCount, category, brand, title, info.authorURL, urlToCheck);
        }
        String newUrl;
        if (!(pagerCurrent == pagerLast)) {
            if (pagerCurrent > 1) {
                newUrl = urlToCheck.substring(0,urlToCheck.lastIndexOf("=")) + "=" + pagerCurrent;
                pagerCurrent++;
                System.out.println("Pager current= " + pagerCurrent + " pager last= " + pagerLast);
                checkPage(newUrl);
            } else {
                newUrl = urlToCheck + "?page=" + pagerCurrent;
                pagerCurrent++;
                System.out.println("Pager current= " + pagerCurrent + " pager last= " + pagerLast);
                checkPage(newUrl);
            }
        }
        System.out.println("Pager current= " + pagerCurrent + " pager last= " + pagerLast);
    }

    private List<WebElement> getReviewsList() {
        System.out.println("Getting comments info");
        WebElement table = reviewPage.seleniumDriver.findElement(By.className("list-comments"));
        List<WebElement> allRows = new ArrayList<>();
        for (WebElement row : table.findElements(By.tagName("li"))) {
            try {
                allRows.add(row.findElement(By.className("authorSpace")));
            } catch (Exception e) {
                System.out.println("No author space has been found");
            }
        }
        System.out.println("Comments on page= " + allRows.size());
        return allRows;
    }
}
