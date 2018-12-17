package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReviewInfo {
    String percentage;
    String date;
    String description;
    String quantity;
    String priceOld;
    String priceNew;
    String shop;
    String url;

    ReviewInfo(WebElement offerInfoUntouched) {
        String path1 = "./div/div/div[1]";
        String path2 = "./div/div/div[2]";
        String path3 = "./div/div/div[3]";

        url = offerInfoUntouched.getAttribute("href");

        List<WebElement> headerBlock = offerInfoUntouched.findElements(By.xpath(path1 + "/div[2]"));
        System.out.println("headerBlock.size()= " + headerBlock.size());
        if (headerBlock.size() > 1) {
            try {
                percentage = headerBlock.get(0).getText();
                date = headerBlock.get(1).findElement(By.xpath("/div")).getText();
            } catch (Exception ex) {
                percentage = "0";
            }
        } else {
            percentage = "undefined";
            date = headerBlock.get(0).findElement(By.xpath("/div")).getText();
        }

        List<WebElement> bodyBlock = offerInfoUntouched.findElements(By.xpath(path2));
        System.out.println("bodyBlock.size()= " + bodyBlock.size());
        if (headerBlock.size() > 1) {
            try {
                description = bodyBlock.get(0).getText();
                quantity = bodyBlock.get(1).getText();
            } catch (Exception ex) {
                quantity = "undefined";
            }
        } else {
            percentage = "undefined";
            quantity = headerBlock.get(0).getText();
        }

        List<WebElement> footerBlock = offerInfoUntouched.findElements(By.xpath(path3+ "/div"));
        System.out.println("footerBlock.size()= " + footerBlock.size());
        if (footerBlock.size() > 2) {
            try {
                priceNew = bodyBlock.get(0).getText();
                priceOld = bodyBlock.get(1).getText();
                shop = bodyBlock.get(2).getAttribute("title");
            } catch (Exception ex) {
                priceOld = "undefined";
            }
        } else {
            priceOld = "undefined";
            priceNew = bodyBlock.get(0).getText();
            shop = bodyBlock.get(1).getAttribute("title");
        }
    }

    @Override
    public String toString() {
        return (percentage + ',' + date + ',' + description + ',' + quantity + ','
                + priceOld + ',' + priceNew + ',' + shop + ',' + url);
    }
}
