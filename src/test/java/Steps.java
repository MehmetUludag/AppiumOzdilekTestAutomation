import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class Steps extends BaseTest{

    final static Logger logger = Logger.getLogger(Steps.class.getName());

    @Step("<second> saniye kadar bekleyiniz.")
    public void waitBySecond(int second) throws InterruptedException{
        Thread.sleep(1000*second);
    }

    @Step("<key> id'li elemente tıkla")
    public void clickElementById(String key){
            appiumDriver.findElement(id(key)).click();
            logger.info(key + "elementine tıklandı.");
    }

    @Step("<key> xpath'li elemente tıkla")
    public void clickElementByXpath(String key){
        appiumDriver.findElement(xpath(key)).click();
        logger.info(key + "elementine tıklandı.");
    }

    public List<MobileElement> listElements() {
        return appiumDriver.findElements(By.xpath("//*[@class='androidx.cardview.widget.CardView']"));
    }

    @Step("Rastgele ürün seç ve <wait> saniye bekle")
    public void randomClick(int wait) throws InterruptedException {
        logger.info("Rastgele bir ürün seçiliyor");
        Random random = new Random();
        listElements().get(random.nextInt(listElements().size())).click();;
        Thread.sleep(1000*wait);
        logger.info("Rastgele bir ürün seçildi");
    }

    @Step("Elementi <xpath> bul ve <keyword> değerini kontrol et")
    public void xpathTextControl(String xpath, String keyword) {
        logger.info("Text değeri " + appiumDriver.findElement(xpath(xpath)).getText());
        Assert.assertTrue("Text değeri bulunmamadı ",
                appiumDriver.findElement(xpath(xpath)).getText().equals(keyword));
    }

    @Step("Sayfayı yukarı kaydır.")
    public void swipeUpI(){
        Dimension dims = appiumDriver.manage().window().getSize();
        logger.info("Telefon Boyutu "+dims);
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder = 25;
        final int PRESS_TIME = 200; // ms
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        logger.info("pointOptionStart " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        logger.info("pointOptionEnd " + pointOptionEnd);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }

    @Step("<Key> xpath'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByxpath(String Key,String keywordc){
        appiumDriver.findElement(xpath(Key)).sendKeys(keywordc);
        logger.info(Key+" Elementine tıklandı");
    }

    @Step("<Key> id'li elemente <keywordc> değerini yaz")
    public void SendkeyElementById(String Key,String keywordc){
        appiumDriver.findElement(id(Key)).sendKeys(keywordc);
        logger.info(Key+" Elementine tıklandı");
    }

}
