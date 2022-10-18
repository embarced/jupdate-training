package jupdate.var;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;

/**
 * Neuer reservierter Typ-Bezeichner „var“ als Alternative für konkrete Typ-Deklaration
 */
public class _2_LocalVar {

    @Test
    @DisplayName("Inference von Typen mit Schlüsselwort \"var\"")
    void simpleVars() {
        // int
        var zahl = 5;
        assertThat(zahl).isEqualTo(5);

        // String
        var string = "Hello World";
        assertThat(string).isInstanceOf(String.class);


        // BigDecimal
        var objekt = BigDecimal.ONE;
        assertThat(objekt).isInstanceOf(BigDecimal.class);
    }

    @Test
    @DisplayName("Inference bei Neuzuweisung eines Wertes (var impliziert nicht \"final\")")
    void varImpliziertNichtFinal() {
        var zahl = 5;
        zahl = 7;
        assertThat(zahl).isEqualTo(7);
    }

    @Test
    @DisplayName("Inference auch mit \"final\" nutzbar, ansonsten gilt seit Java 8 ohnehin \"effectively final Semantik\"")
    void varKannAuchFinalSein() {
        final var zahl = 5;
        // zahl = 8;
    }

    @Test
    @DisplayName("Type wird bei Deklaration und Erstzuweisung vom Compiler festgelegt")
    void varInkompatibleTypen() {
        var zahl = 5;
        // incompatible types: possible lossy conversion from long to int
        // zahl = 7L;

        var objekt = BigDecimal.ONE;
        // incompatible types: BigInteger cannot be converted to BigDecimal
        // objekt = BigInteger.TEN;
    }

    @Test
    @DisplayName("Local Variable Type Inference fuer Lambda Ausdruecke nicht moeglich")
    void varLambdas() {
        // var helloFunction = name -> "Hello, " + name;
        // var intParser = Integer::parseInt;

    }

    @Test
    @DisplayName("Inference generischer Typen (List<String>)")
    void varGenericTypes() {
        var strings = Arrays.asList("World", "Java 10");

        // Inference in Schleifen
        for (var string : strings) {
            System.out.println("Hello " + string);
        }

    }

    @Test
    @DisplayName("Kombination mit Diamond Operator führt zu Inferenz von List<Object>")
    void varMitDiamondOperator() {
        //
//			var strings = new ArrayList<>();
//			strings.add("Hello World");
//			for (var string : strings) {
//				// cannot find symbol
//				System.out.println(string.replace("World", "Java 10"));
//			}

        var strings = new ArrayList<String>();
        strings.add("Hello World");
        for (var string : strings) {
            System.out.println(string.replace("World", "Java 10"));
        }
    }

    @Test
    @DisplayName("Deklaration von \"var\" nur bei direkter Initialisierung der Variable")
    void varOhneInitialisierung() {
        // cannot use 'var' on variable without initializer
//			var flag = true;
//			var number;
//			if (flag) {
//				number = 5;
//			} else {
//				number = 7;
//			}
    }

    @Test
    @DisplayName("Inference von lokalen Typen")
    void varLocalTypes() {
        var myReversibleStringList = new ArrayList<String>() {
            List<String> reverseMe() {
                var reversed = new ArrayList<String>(this);
                Collections.reverse(reversed);
                return reversed;
            }
        };
        myReversibleStringList.add("World");
        myReversibleStringList.add("Hello");

        System.out.println(myReversibleStringList.reverseMe());
    }

    @Test
    @DisplayName("Inference nutzt konkrete Typisierung")
    void varAnonymClasses() {
        var runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        // incompatible types: <anonymous Runnable> cannot be converted to <anonymous Runnable>
//			runnable = new Runnable() {
//				@Override
//				public void run() {
//				}
//			};

    }
}

