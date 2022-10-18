package collection.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsCopy {
	
	public static void main(String[] args) {
		unmodifiableCollectionsWiederholung();
		collectionsCopy();
		mapCopy();
	}

	@SuppressWarnings("unused")
	private static void unmodifiableCollectionsWiederholung() {
		System.out.println("Wiederholung zu unmodifiable Collections:");
		List<String> strings = new ArrayList<>();
		strings.add("a");
		// Erstellt eine nicht aenderbare Sicht auf die zugrundeliegende List
		List<String> unmodifiableStrings = Collections.unmodifiableList(strings);
		
		// Spaetere Aenderungen der zugundeliegenden List sind sichtbar fuer unmodifiableList Wrapper
		strings.add("b");
		System.out.println("Unmodifiable List: " + unmodifiableStrings);
		
		// unmodifiableList verhindert Aenderungen (UnsupportedOperationException)
		// unmodifiableStrings.add("c");
		
		// Alternative: Defensive Copy
		List<String> defensiveCopy = new ArrayList<>(strings);
		// Spaetere Aenderungen der original List nicht sichtbar fuer defensive copy
		strings.add("c");
		System.out.println("Defensive Copy: " + defensiveCopy);
		
		// Aenderungen an defensive copy moeglich, suggeriert aber ggf., dass sich eine Aenderung auf original List auswirkt
		defensiveCopy.add("c");
		
		// Nicht aenderbare Defensive Copy
		List<String> unmodifiableDefensiveCopy = Collections.unmodifiableList(new ArrayList<>(strings));
	}

	private static void collectionsCopy() {
		System.out.println("Beispiele zu List/Set.copyOf:");
		List<String> strings = new ArrayList<>();
		strings.add("a");
		
		// Erstellt eine nicht aenderbare Kopie der List

		// Aenderungen nicht möglich (UnsupportedOperationException)
		// stringsCopy.add("b");
		
		// Spaetere Aenderungen der original List nicht sichtbar fuer Kopie

		// Kopien muessen nicht die gleiche Art von Collection sein
		strings.add("b");
		Set<String> stringSet = Set.copyOf(strings);
		System.out.println("String List: " + strings);
		System.out.println("String Set: " + stringSet);
	}

	private static void mapCopy() {
		System.out.println("Beispiele zu Map.copyOf:");
		Map<String, Integer> values = new HashMap<>();
		values.put("a", 1);
		
		// Erstellt eine nicht aenderbare Kopie der Map

		// Kopierte Map ist nicht veraenderlich (UnsupportedOperationException)
		// valuesCopy.put("b", 2);
		
		// Spaetere Aenderungen der original Map nicht sichtbar fuer Kopie
	}
}
