package jupdate.collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactories {

	@Test
	void collectionErstellungBisJava8() {
			// Erstellung von List mit Werten bisher durch manuelles einfuegen:
			List<String> strings = new ArrayList<>();
			strings.add("a");
			strings.add("b");
			strings.add("c");

		Assertions.assertThat(strings).hasSize(3);
		}

		@Test
		void arraysAsList() {

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
			List<String> strings = List.of("a", "b", "c");
			
			// Erzeugte List ist unveraenderlich (UnsupportedOperationException):
			// strings.set(0, "d");
			// strings.add("e");
			
			// Entspricht damit:
			// List<String> strings = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
		}
		
		{
			// Erstellung eines Set ueber Factory Methode:
			Set<String> strings = Set.of("a", "b", "c");
			
			// Erzeugtes Set ist unveraenderlich (UnsupportedOperationException):
			// strings.set(0, "d");
			// strings.add("e");
			
			// Bisher kompliziert als Einzeiler:
			// Set<String> strings = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("a", "b", "c")));
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
			Map<String, Integer> values = Map.of("a", 1, "b", 2, "c", 3);
			
			// Erzeugte Map ist unveraenderlich (UnsupportedOperationException):
			// values.put("d", 4);
			
			// Begrenzung auf 10 Schluessel-Wert-Paare
			// Map<String, Integer> values = Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9, "j", 10, "k", 11);
		}
		
		{
			Map<String, Integer> values = Map.of("a", 1, "b", 2, "c", 3);
			
			// Map.Entry kennen wir schon:
			for (Map.Entry<String, Integer> entry : values.entrySet()) {
			}
		}
		
		{
			// Map.Entry Instanzen koennen jetzt auch direkt erzeugt werden:
			Map.Entry<String, Integer> entry = Map.entry("a", 1);
			
			// Erstellung einer Map ueber Factory Methode und Map.Entry Instanzen:
			Map<String, Integer> values = Map.ofEntries(Map.entry("a", 1), Map.entry("b", 2), Map.entry("c", 3));
		}
	}
}
