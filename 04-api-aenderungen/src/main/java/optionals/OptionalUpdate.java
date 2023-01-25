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
	}
	
	@SuppressWarnings("unused")
	private static void optionalErweiterungen() {
		Optional<String> emptyOptional = Optional.empty();
		emptyOptional.ifPresentOrElse(System.out::println, () -> System.out.println("leer"));
		
		// Behandlung von Existenz und Abwesenheit eins Wertes in einem Aufruf
		emptyOptional.or(() -> Optional.of("foobar"));

		// Gibt das bestehende Optional zurueck, falls dies ein Wert hat oder erzeugt alternativ ein neues ueber Supplier

		// Erzeugung eines 0- oder 1-Elementigen Stream aus Optional

		// Gibt existierenden Wert zurück und wirft Exception, falls Optional leer ist
		// Empfohlene Alternative zu Optional.get()
	}

}
