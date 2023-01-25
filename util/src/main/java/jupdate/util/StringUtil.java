package jupdate.util;

public final class StringUtil {

        private StringUtil() {
        }

        public static String flip(String s) {
                final StringBuffer flippedString = new StringBuffer();

                for (int i = (s.length() - 1); i >= 0; i--) {
                        flippedString.append(s.charAt(i));
                }

                return flippedString.toString();
        }
}