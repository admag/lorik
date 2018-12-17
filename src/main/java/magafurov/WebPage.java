package magafurov;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebPage {

    HtmlUnitDriver seleniumDriver;

    public WebPage() throws MalformedURLException {
        this.seleniumDriver = new HtmlUnitDriver();
        this.seleniumDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS );
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
    }
}
