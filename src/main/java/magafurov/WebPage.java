package magafurov;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebPage {

    HtmlUnitDriver seleniumDriver;

    public WebPage() throws MalformedURLException {

//        ChromeOptions options =new ChromeOptions();
//        options.addArguments("disable-popup-blocking");
//        options.addArguments("instant-process");
//
//
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setJavascriptEnabled(true);
//        caps.setCapability("takesScreenshot", true);
//        caps.setCapability("loadImages",false);
//        caps.setCapability("localToRemoteUrlAccessEnabled", true);
//        caps.setCapability("javascriptEnabled", false);
//        caps.setCapability(
//                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                "/Users/aydar.magafurov/Documents/phantomjs-2.1.1-macosx/bin/phantomjs"
//        );
        this.seleniumDriver = new HtmlUnitDriver();
        this.seleniumDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS );
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        //seleniumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //seleniumDriver = new ChromeDriver(options);
        //WebDriverWait wait = new WebDriverWait(seleniumDriver, 10);
        //seleniumDriver.manage().window().setSize(new Dimension(1466, 868));
        //seleniumDriver.setFileDetector(new LocalFileDetector());
    }
}
