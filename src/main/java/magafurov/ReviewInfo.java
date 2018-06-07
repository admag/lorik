package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;

public class ReviewInfo {
    String authorName;
    String authorURL;
    String commentCount;
    String stars;
    String date;
    String comment;
    String commentUrl;

    ReviewInfo(WebElement reviewInfoUntouched) {
        WebElement reviewInfo = reviewInfoUntouched.findElement(By.className("authorSpace"));
        String path1 = "./div[@class='half1']/div[2]/div[@class='authorName']/a";
        String path2 = "./div[@class='half2']";
        String path3 = "./div[@class='half1']/div[2]/div/div/meta";
        authorName = reviewInfo.findElement(By.xpath(path1)).getText();
        System.out.println("Author= " + authorName);
        authorURL = reviewInfo.findElement(By.xpath(path1)).getAttribute("href");
        System.out.println("authorURL= " + authorURL);
        date = reviewInfo.findElement(By.xpath(path2 + "/div")).getText();
        System.out.println("date= " + date);
        List<WebElement> commentBlock = reviewInfo.findElements(By.xpath(path2 + "/div"));
        System.out.println("commentBlock.size()= " + commentBlock.size());
        if (commentBlock.size() > 1) {
            try {
                commentCount = reviewInfo.findElement(By.xpath(path2 + "/div[contains(@class,'comments')]")).getText();
            } catch (Exception ex) {
                commentCount = "0";
            }
        } else {
            commentCount = "0";
        }
        System.out.println("commentCount= " + commentCount);
        stars = reviewInfo.findElement(By.xpath(path3)).getAttribute("content");
        System.out.println("stars= " + stars);
        try {
            comment = reviewInfoUntouched.findElement(By.className("reviewTextSnippet")).findElement(By.xpath("./div[1]/a")).getText();
        } catch (Exception e) {
            comment = "";
        }
        System.out.println("comment= " + comment);
        try {
            commentUrl = reviewInfoUntouched.findElement(By.className("reviewTextSnippet")).findElement(By.xpath("./div[1]/a")).getAttribute("href");
        } catch (Exception e) {
            commentUrl = "";
        }
        System.out.println("commentUrl= " + commentUrl);
    }

    @Override
    public String toString() {
        return (authorName + ',' + stars + ',' + date + ',' + commentCount + ',' + authorURL);
    }
}
