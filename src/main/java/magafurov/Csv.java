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
            sb.append(']');
            sb.append("Author");
            sb.append(']');
            sb.append("Rating");
            sb.append(']');
            sb.append("Post date");
            sb.append(']');
            sb.append("# of comments");
            sb.append(']');
            sb.append("comment text");
            sb.append(']');
            sb.append("category");
            sb.append(']');
            sb.append("brand");
            sb.append(']');
            sb.append("title");
            sb.append(']');
            sb.append("Profile");
            sb.append(']');
            sb.append("Url");
            sb.append('\n');
            pw.write(sb.toString());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    public static void writeToFile(String author, String rating, String date, String commentCount, String comment, String category, String brand, String title, String profile, String url) {
        sb = new StringBuilder();
        sb.append(++count + "");
        sb.append(']');
        sb.append(author);
        sb.append(']');
        sb.append(rating);
        sb.append(']');
        sb.append(date);
        sb.append(']');
        sb.append(commentCount);
        sb.append(']');
        sb.append(comment);
        sb.append(']');
        sb.append(category);
        sb.append(']');
        sb.append(brand);
        sb.append(']');
        sb.append(title);
        sb.append(']');
        sb.append(profile);
        sb.append(']');
        sb.append(url);
        sb.append('\n');
        pw.write(sb.toString());
        System.out.println(count + " " + url);
    }
    public void closePw() {
        pw.close();
    }
}