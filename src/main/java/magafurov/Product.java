package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;

public class Product {
    WebPage productPage;

    {
        try {
            productPage = new WebPage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void checkPageProduct(String urlToCheck) throws Throwable{
        this.productPage.seleniumDriver.get(urlToCheck);
        System.out.println(urlToCheck);
        Thread.sleep(15000);
        int lastPageIndex = getNumberOfPages();
        for (int i = 1; i < lastPageIndex; i++) {
            List<WebElement> offers = getOffers().findElements(By.xpath("//a"));
            int offerCounter = offers.size();
            for (int j = 0; j < offerCounter; j++) {
                ReviewInfo offer = new ReviewInfo(offers.get(j));
                Csv.writeToFile(offer.date, offer.description, offer.percentage, offer.priceNew,
                        offer.priceOld, offer.quantity, offer.shop, offer.url);
            }
            if (i == 1) {
                String newUrl = urlToCheck + "?page=" + i;
                urlToCheck = newUrl;
            } else {
                String newUrl = urlToCheck.substring(0,urlToCheck.indexOf("?")) + "?page=" + i;
                urlToCheck = newUrl;
            }
        }
    }

    private int getNumberOfPages() {
        WebElement lastPager = null;
        try {
            lastPager = productPage.seleniumDriver.findElement(By.xpath(
                    "//*[@id=\"view\"]/div[1]/div/div[1]/div[3]/div/div[1]/div[9]/a/div"
            ));
        } catch (Exception ex) {
            System.out.println("Failed to find last pager");
        }
        return Integer.parseInt(lastPager.getText());
    }

    private WebElement getOffers() {
        WebElement offers = null;
        try {
            offers = productPage.seleniumDriver.findElement(By.xpath(
                    "//*[@id=\"view\"]/div[1]/div/div[1]/div[2]/div[2]"
            ));
        } catch (Exception ex) {
            System.out.println("Failed to find offers");
        }
        return offers;
    }
}
