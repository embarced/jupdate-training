package jupdate.var;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Diese Art der Type Inference gab es schon vor Java 9, sollten wir also bereits kennen.
 */
public class _1_TypeInference {

    @Test
    @DisplayName("Type Inference bei generisch typisierten Methoden (seit Java 5)")
    void typeInferenceMethods() {
        List<String> names = Arrays.asList("Hello", "Java 9");
    }

    @Test
    @DisplayName("Type Inference bei Parametern von Lambda Ausdrücken (Java 8)")
    void typeInferenceLambdaParameter() {
        Function<String, String> greetingFunction = name -> "Hello, " + name;
    }

    @Test
    @DisplayName("Inference von Generics (Diamond Operator; Java 7)")
    void typeInferenceDiamondOperator() {
        List<String> helloStrings = new ArrayList<>();

        List<String> names = Arrays.asList("World", "Java 9", "embarc");
        Function<String, String> helloFunction = s -> "Hello " + s;
        names.forEach(s -> helloStrings.add(helloFunction.apply(s)));
    }

    @Test
    @DisplayName("Inference von Generics (Diamond Operator) bei anonymen inneren Klassen (Java 9 -> JEP 213)")
    void typeInferenceCombination() {
        // Type Inference bei generisch typisierten Methoden (Java 5)
        List<String> strings = Arrays.asList("World", "Java 9");

        // Type inference bei Parametern von Lambda Ausdrücken (Java 8)
        Function<String, String> helloFunction = s -> "Hello " + s;

        // Inference von Generics (Diamond Operator; Java 7)
        List<String> helloStrings = new ArrayList<>();
        strings.forEach(s -> helloStrings.add(helloFunction.apply(s)));

        // Inference von Generics (Diamond Operator) bei anonymen inneren Klassen (Java 9 -> JEP 213)
        Consumer<String> printer = new Consumer<>() {
            @Override
            public void accept(String string) {
                System.out.println(string);
            }
        };

        helloStrings.forEach(printer::accept);
    }

}
