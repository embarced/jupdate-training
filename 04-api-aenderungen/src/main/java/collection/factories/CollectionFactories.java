package collection.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
			List<String> leer = List.of();
			System.out.println(leer);
			List<Integer> integers = List.of(1, 2, 2, 6, 4, 44, 4, 4, 4, 4, 4, 4, 4, 4, 44, 4);
			//integers.add(44444);
			//integers.set(0, 6);

			new HashSet(Arrays.asList(1,1,2));

			Set<Integer> set = Stream.of(1, 1, 1, 2).distinct().collect(Collectors.toSet());
			Stream.of(1,2,3).collect(Collectors.toSet());
			Set.of(1,2,3);
			System.out.println(set);
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
