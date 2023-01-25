package jupdate.var;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Local variable von kombinierten Typen
 */
public class _3_LocalVarKombinierteTypen {

    public interface Fahrbar extends Runnable {
        default void fahren() {
            System.out.println("Fahren");
        }
    }

    public interface Schwimmbar extends Runnable {
        default void schwimmen() {
            System.out.println("Hupen");
        }
    }

    // Deklaration kombinierter generischer Typen
    // Der konkrete Typ ist uns egal, solange er die Anforderungen des deklarierten Generics erfüllt
    public static class AmphibienFahrzeug<T extends Fahrbar & Schwimmbar> {
        // Methodenaufruf erfordert ein Objekt, welches beide Interfaces implementiert
        void fahrenUndSchwimmen(T t) {
            t.fahren();
            t.schwimmen();
        }
    }

    public static class Schwimmauto implements Fahrbar, Schwimmbar {

        @Override
        public void run() {
            System.out.println("Schwimmauto");
        }
    }


    @Test
    @DisplayName("Inference von Typen mit Schlüsselwort \"var\"")
    void kombinierteTypen() {
        // Durch Kombination des Diamond Operators mit Typ Inferenz wird der Basistyp des Generics erzwungen
        // Da der Basistyp eine Kombination zweier Type ist, erhält auch die Variable den kombinierten generischen Typ
        // Dies ist ohne Schlüsselwort "var" nicht abbildbar
        var amphibienFahrzeug = new AmphibienFahrzeug<>();
        // Die Variable erhält per Type Inferenz einen Lambda-Typ, welcher beide Interfaces implementiert
        var faehrtUndSchwimmt = (Fahrbar & Schwimmbar) () -> {
        };
        // Typen passen, da der vom Compiler generierte Lambda-Typ die Anforderungen des kombinierten gcenerischen Typs erfüllt
        amphibienFahrzeug.fahrenUndSchwimmen(faehrtUndSchwimmt);
    }

}
