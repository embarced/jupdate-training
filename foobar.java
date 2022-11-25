2 + 2
$1
System.out.println($1)
System.out.println("Hello")
int i = 3;
String flip(String s) {
        final StringBuffer flippedString = new StringBuffer();

        for (int i = (s.length() - 1); i >= 0; i--) {
            flippedString.append(s.charAt(i));
        }

        return flippedString.toString();
    }
flip("hello")
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
class A{}
new A()
StringUtil.flip("hello")