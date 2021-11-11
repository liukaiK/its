package cn.com.goodlan.its.api.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

    public static String getExceptionInfo(Exception exception) {

        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);

            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e) {
            e.printStackTrace();

            return "bad getErrorInfoFromException";
        }
    }
}
