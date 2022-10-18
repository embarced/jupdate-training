package collection.factories;

import java.util.List;

@SuppressWarnings("unused")
public class CollectionsToArray {
    public static void main(String[] args) {
        List<String> strings = List.of("a", "b");

        // Erzeugung eines Arrays aus einer Collection bisher
        String[] stringsAsArray = strings.toArray(new String[strings.size()]);

        // Ab Java 11
        String[] stringsAsArrayMitLambda = strings.toArray(s -> new String[s]);
        String[] stringsAsArrayMitMethodenReferenz = strings.toArray(String[]::new);
    }
}
