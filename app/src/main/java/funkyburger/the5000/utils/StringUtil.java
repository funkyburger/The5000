package funkyburger.the5000.utils;

public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        if(str == null) {
            return true;
        } else if(str.length() < 1) {
            return true;
        }

        return false;
    }
}
