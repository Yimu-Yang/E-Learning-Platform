package cmpe295.hungwenli.elearning.util;

import java.io.*;
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
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static boolean checkLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

}
