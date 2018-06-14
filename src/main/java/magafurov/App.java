package magafurov;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Throwable
    {
        //java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        //java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

        String[] brandUrls = new String[100];
        String csvFile = System.getProperty("user.dir") + "/input.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        System.out.println(csvFile);
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                brandUrls = line.split(cvsSplitBy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for(String s : brandUrls) {
            System.out.println(s);
        }
        Csv csv = new Csv(System.getProperty("user.dir") + "/test.csv");
        for(String url : brandUrls) {
            try {
                Product product = new Product();
                product.checkPageProduct(url);
            } catch (Exception exc) {
                System.out.println("Could not get product page, failed on= " + url);
                //exc.printStackTrace();
            }
        }
        csv.closePw();
    }
}

