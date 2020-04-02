package cmpe295.hungwenli.elearning.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

public class Utility {

    public static String readFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        InputStream in = Utility.class.getResourceAsStream("/static/" + fileName);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str = reader.readLine()) != null) {
                contentBuilder.append(str);
                contentBuilder.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean checkLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

}
