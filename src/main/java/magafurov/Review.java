package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Review {

    int current;
    int last;
    WebPage reviewPage;

    {
        try {
            reviewPage = new WebPage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void checkPage(String urlToCheck) throws Throwable{
        Thread.sleep(1000);
        this.reviewPage.seleniumDriver.get(urlToCheck);
        List<WebElement> list = getReviewsList();
        String category = "";
        String title = "";
        try {
            WebElement temp  = reviewPage.seleniumDriver.findElement(By.xpath("//div[contains(@class,'voc-group vid-1')]"));
            category = temp.findElement(By.xpath("./a")).getText();
            title = reviewPage.seleniumDriver.findElement(By.xpath("//h1/span")).getText();
        } catch (Exception e) {
            System.out.println(reviewPage.seleniumDriver.getPageSource());
        }
        for (int i = 0; i < list.size(); i++) {
            WebElement row = list.get(i);
            ReviewInfo info = new ReviewInfo(row);
            Csv.writeToFile(info.authorName, info.stars, info.date, info.commentCount, category, title, info.authorURL, urlToCheck);
            //System.out.println(info.toString());
        }
        try {
            current = Integer.parseInt(reviewPage.seleniumDriver.findElement(By.xpath("//li[contains(@class,'pager-current')]")).getText());
            try {
                last = Integer.parseInt(reviewPage.seleniumDriver.findElement(By.xpath("//li[@class='pager-current last']")).getText());
            } catch (Exception ex) {
                last = Integer.parseInt(reviewPage.seleniumDriver.findElement(By.xpath("//li[@class='pager-last last']/a")).getText());
            }
        } catch (Exception exc) {
            current = 0;
            last = 0;
        }
        String newUrl;
        if (!(current == 0 || current == last)) {
            if (current > 1) {
                newUrl = urlToCheck.substring(0,urlToCheck.lastIndexOf("=")) + "=" + current;
                checkPage(newUrl);
            } else {
                newUrl = urlToCheck + "?page=" + current;
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
                System.out.println("Test4");
            }
        }
        return allRows;
    }
}
