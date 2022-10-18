public class StringFlipper {
    public static void main(String[] args) {
        System.out.println(flip("hello"));
    }

    public static String flip(String s) {
        StringBuilder flippedString = new StringBuilder();
        for (int i = (s.length() - 1); i >= 0; i--) {
            flippedString.append(s.charAt(i));
        }
        return flippedString.toString();
    }

}
