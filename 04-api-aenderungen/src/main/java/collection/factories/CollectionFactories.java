package collection.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class CollectionFactories {

	public static void main(String[] args) {
		collectionErstellungBisJava8();
		collectionFactories();
		mapErstellungBisJava8();
		mapFactories();
	}

	private static void collectionErstellungBisJava8() {
		{
			// Erstellung von List mit Werten bisher durch manuelles einfuegen:
			List<String> strings = new ArrayList<>();
			strings.add("a");
			strings.add("b");
			strings.add("c");
		}

		{
			// Alternative Erstellung einer List ueber Factory in Arrays:
			List<String> strings = Arrays.asList("a", "b", "c");
			// Setzen eines Wertes moeglich:
			strings.set(0, "d");
			// Hinzufuegen eines Wertes ist nicht moeglich (UnsupportedOperationException):
			// strings.add("e");
			System.out.println(strings);
		}

		{
			// Erstellung von Set mit Werten bisher durch manuelles einfuegen:
			Set<String> strings = new HashSet<>();
			strings.add("a");
			strings.add("b");
			strings.add("c");
		}

		{
			// Erstellung von Set mit Werten mit Umweg ueber List:
			Set<String> strings = new HashSet<>(Arrays.asList("a", "b", "c"));
		}

	}
	
	private static void collectionFactories() {
		{
			// Erstellung einer List ueber Factory Methode:
		}
		
		{
			// Erstellung eines Set ueber Factory Methode:
		}
	}
	
	private static void mapErstellungBisJava8() {
		{
			Map<String, Integer> values = new HashMap<>();
			values.put("a", 1);
			values.put("b", 2);
			values.put("c", 3);
		}
	}
	
	private static void mapFactories() {
		{
			// Erstellung einer Map ueber Factory Methode:
		}
		
		{
			// Map.Entry Instanzen koennen jetzt auch direkt erzeugt werden:
			Map.Entry<String, Integer> entry = Map.entry("a", 1);
			
			// Erstellung einer Map ueber Factory Methode und Map.Entry Instanzen:
		}
	}
}
