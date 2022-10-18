package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsUpdate {

	public static void main(String[] args) {
		streamsWiederholung();
		iterateWiederholung();
		iterateBeispiel();
		ofWiederholung();
		ofNullableBeispiel();
		filterWiederholung();
		takeWhileBeispiel();
		dropWhileBeispiel();
		toCollectionWiederholung();
		toUnmodifiableBeispiel();
	}
	
	private static void streamsWiederholung() {
		int max = Arrays.asList(1, 2, 3, 4, 5) 	// erstellt eine ArrayList
				.stream() 						// erzeugt einen Stream aus der ArrayList
				.map(i -> i * 10) 				// multipliziert jeden Wert mit 10
				.max(Integer::compareTo).get(); // sucht das Maximum

		System.out.println("Resultat fuer Integer Stream aus ArrayList\nMax: " + max);
	}
	
	private static void iterateWiederholung() {
		System.out.println("\nResultat iterate mit limit:");
		Stream.iterate(1, x -> x * 2) 				// Erstellung des Streams: 2er Potenzen
		.limit(10)
		.forEach(x -> System.out.print(x + " ")); 	// Ausgabe der Zweiterpotenzen
	}

	private static void iterateBeispiel() {
		System.out.println("\n\nResultat iterate-Beispiel:");
		Stream.iterate(1, x -> x < 1000, x -> x * 2) 		// Erstellung des Streams: 2er Potenzen zwischen 1 und 1000
				.forEach(x -> System.out.print(x + " ")); 	// Ausgabe der Zweiterpotenzen
		
		System.out.println("\n\nAequivalente for-Schleife:");
		for (int x = 1; x < 1000; x *= 2) {
			System.out.print(x + " ");
		}
	}

	private static void ofWiederholung() {
		System.out.println("\n\nWiederholung zu of:");
		System.out.println(Stream.of("a", "b").count()); 	// Erzeugung eines Streams ohne Zwischenschritt über Collection
		
		System.out.println("\nNull-Werte und of:");
		String nullValue = null;
		System.out.println(Stream.of(nullValue).count()); 	// null ist gueltiges Stream-Element
		System.out.println(Stream.of(nullValue)
				.filter(x -> x != null)						// Ausfiltern des null-Wertes
				.count());
	}
	
	private static void ofNullableBeispiel() {
		System.out.println("\nNullcheck Beispiel:");
		String nullValue = null;
		System.out.println(Stream.ofNullable(nullValue).count()); 	// Aufruf der neuen Stream.ofNullable(...)-Methode
	}
	
	private static void filterWiederholung() {
		System.out.println("\nWiederholung zu filter:");
		Stream.of(1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1) 	// erzeugt einen Stream mit den uebergebenen Zahlen
		.filter(x -> x < 6) 								// Filterbedingung x < 6
		.forEach(x -> System.out.print(x + " "));			// Ausgabe der Zahlen
	}

	private static void takeWhileBeispiel() {
		System.out.println("\n\nResultat fuer takeWhile:");
		Stream.of(1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1) 	// erzeugt einen Stream mit den uebergebenen Zahlen
				.takeWhile(x -> x < 6) 						// Abbruchbedingung x < 6
				.forEach(x -> System.out.print(x + " "));	// Ausgabe der Zahlen
	}

	private static void dropWhileBeispiel() {
		System.out.println("\n\nResultat fuer dropWhile:");
		IntStream.of(1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1)	// erzeugt einen Stream mit den uebergebenen Zahlen
				.dropWhile(x -> x < 5)						// Bedingnung x < 5
				.forEach(x -> System.out.print(x + " "));	// Ausgabe der Zahlen
	}
	
	private static void toCollectionWiederholung() {
		System.out.println("\n\nWiederholung zu toList und toCollection:");
		List<Integer> values = Stream.of(1, 2, 3)
				.map(i -> i * 2)
				.collect(Collectors.toList()); // Sammeln von Werten in List
		// Collectors.toList garantiert bzgl. der erzeugten List keine Aenderbarkeit
		values.add(27); // Die List kann aber aenderbar sein
		System.out.println(values);
		
		List<Integer> values2 = Stream.of(1, 2, 3)
				.map(i -> i * 2)
				.collect(Collectors.toCollection(ArrayList::new)); // Alternative Collectors.toCollection mit Factory zur Erzeugung der Collection
		values2.add(27);
		System.out.println(values2);
		
		// Collectors.toCollection erfordert explizit eine aenderbare Collection (UnsupportedOperationException)
		// IntStream.of(1, 2, 3)
		//		.mapToObj(i -> i * 2)
		//		.collect(Collectors.toCollection(() -> Collections.unmodifiableList(new ArrayList<>())));
	}
	
	private static void toUnmodifiableBeispiel() {
		System.out.println("\n\nResultat fuer toUnmodifiable*:");
		System.out.println(Stream.of(1, 2, 3)
				.map(i -> i * 2)
				.collect(Collectors.toUnmodifiableList())); // Sammeln in unveraenderliche List
		
		System.out.println(Stream.of(1, 2, 3, 3)
				.map(i -> i * 2)
				.collect(Collectors.toUnmodifiableSet())); // Sammeln in unveraenderliches Set
		
		System.out.println(Stream.of(1, 2, 3)
				.collect(Collectors.toUnmodifiableMap(i -> i + 1, i -> i * 2))); // Sammeln in unveraenderliche Map
	}
}
