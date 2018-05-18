package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Date;

public class ReviewInfo {
    String authorName;
    String authorURL;
    String commentCount;
    String stars;
    String date;

    ReviewInfo(WebElement reviewInfo) {
        String path1 = "./div[@class='half1']/div[2]/div[@class='authorName']/a";
        String path2 = "./div[@class='half2']";
        String path3 = "./div[@class='half1']/div[2]/div/div/meta";
        authorName = reviewInfo.findElement(By.xpath(path1)).getText();
        authorURL = reviewInfo.findElement(By.xpath(path1)).getAttribute("href");
        date = reviewInfo.findElement(By.xpath(path2 + "/div")).getText();
        try {
            commentCount = reviewInfo.findElement(By.xpath(path2 + "/div[contains(@class,'comments')]")).getText();
        } catch (Exception ex) {
            commentCount = "0";
        }
        stars = reviewInfo.findElement(By.xpath(path3)).getAttribute("content");
    }

    @Override
    public String toString() {
        return (authorName + ',' + stars + ',' + date + ',' + commentCount + ',' + authorURL);
    }
}
