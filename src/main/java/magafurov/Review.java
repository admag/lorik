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
        Thread.sleep(10000);
        List<WebElement> list = getReviewsList();
        String category = "";
        String title = "";
        try {
            WebElement temp  = this.reviewPage.seleniumDriver.findElement(By.xpath("//div[contains(@class,'voc-group vid-1')]"));
            category = temp.findElement(By.xpath("./a")).getText();
            title = this.reviewPage.seleniumDriver.findElement(By.xpath("//h1/span")).getText();
        } catch (Exception e) {
            System.out.println(reviewPage.seleniumDriver.getPageSource());
        }
        for (int i = 0; i < list.size(); i++) {
            WebElement row = list.get(i);
            ReviewInfo info = new ReviewInfo(row);
            Csv.writeToFile(info.authorName, info.stars, info.date, info.commentCount, category, title, info.authorURL, urlToCheck);
            //System.out.println(info.toString());
        }
        String newUrl;
        if (!(pagerCurrent == pagerLast)) {
            if (pagerCurrent > 1) {
                newUrl = urlToCheck.substring(0,urlToCheck.lastIndexOf("=")) + "=" + pagerCurrent;
                pagerCurrent++;
                checkPage(newUrl);
            } else {
                newUrl = urlToCheck + "?page=" + pagerCurrent;
                pagerCurrent++;
                checkPage(newUrl);
            }
        }
    }

    private List<WebElement> getReviewsList() {
        //By xpath = By.xpath(("//ul[@class='list-comments']"));
        WebElement table = reviewPage.seleniumDriver.findElement(By.className("list-comments"));
        List<WebElement> allRows = new ArrayList<>();
        for (WebElement row : table.findElements(By.tagName("li"))) {
            try {
                allRows.add(row.findElement(By.className("authorSpace")));
            } catch (Exception e) {
                System.out.println("No author space has been found");
            }
        }
        return allRows;
    }
}
