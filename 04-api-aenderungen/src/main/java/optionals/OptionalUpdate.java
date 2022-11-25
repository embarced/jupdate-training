package optionals;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalUpdate {

	public static void main(String[] args) {
		optionalWiederholung();
		optionalErweiterungen();
	}

	@SuppressWarnings("unused")
	private static void optionalWiederholung() {
		// Optional mit Wert
		Optional<String> aOptional = Optional.of("a");
		aOptional.ifPresent(System.out::println);

		// Optional ohne Wert
		Optional<String> emptyOptional = Optional.empty();
		Optional<String> otherEmptyOptional = Optional.ofNullable(null);
		System.out.println(emptyOptional.orElse("Default Wert"));
		
		// Optional in Java
		Optional<String> optionalResult = Stream.<String>of().findFirst();
		System.out.println("Test: " + optionalResult);
	}
	
	@SuppressWarnings("unused")
	private static void optionalErweiterungen() {
		Optional<String> emptyOptional = Optional.empty();
		
		// Behandlung von Existenz und Abwesenheit eins Wertes in einem Aufruf
		emptyOptional.ifPresentOrElse(System.out::println, () -> System.out.println("Kein Wert"));

		// Gibt das bestehende Optional zurueck, falls dies ein Wert hat oder erzeugt alternativ ein neues ueber Supplier
		Optional<String> alternative = Optional.of("b").or(() -> Optional.of("a"));
		System.out.println(alternative);
		
		// Erzeugung eines 0- oder 1-Elementigen Stream aus Optional
		emptyOptional.stream().count();

		// Gibt existierenden Wert zurück und wirft Exception, falls Optional leer ist
		// Empfohlene Alternative zu Optional.get()
		emptyOptional.orElseThrow();
	}

}
