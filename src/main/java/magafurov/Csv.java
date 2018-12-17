package magafurov;

import java.io.File;
import java.io.PrintWriter;

public class Csv {

    private static PrintWriter pw;
    private static StringBuilder sb;
    private static int count = 1;
    public Csv(String path) {
        try {
            pw = new PrintWriter(new File(path),"UTF8");
            sb = new StringBuilder();
            sb.append("#");
            sb.append('}');
            sb.append("date");
            sb.append('}');
            sb.append("description");
            sb.append('}');
            sb.append("percentage");
            sb.append('}');
            sb.append("priceNew");
            sb.append('}');
            sb.append("priceOld");
            sb.append('}');
            sb.append("quantity");
            sb.append('}');
            sb.append("shop");
            sb.append('}');
            sb.append("url");
            sb.append('\n');
            pw.write(sb.toString());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    public static void writeToFile(String date, String description, String percentage, String priceNew,
                                   String priceOld, String quantity, String shop, String url) {
        sb = new StringBuilder();
        sb.append(++count + "");
        sb.append('}');
        sb.append(date);
        sb.append('}');
        sb.append(description);
        sb.append('}');
        sb.append(percentage);
        sb.append('}');
        sb.append(priceNew);
        sb.append('}');
        sb.append(priceOld);
        sb.append('}');
        sb.append(quantity);
        sb.append('}');
        sb.append(shop);
        sb.append('}');
        sb.append(url);
        sb.append('\n');
        pw.write(sb.toString());
        System.out.println(count + " " + url);
    }
    public void closePw() {
        pw.close();
    }
}