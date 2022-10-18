package jupdate.var;

import jupdate.annotations.NonNull;
import jupdate.annotations.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Local variable von kombinierten Typen
 */
public class _4_LocalVarLambdaParameter {

    @Test
    @DisplayName("Inference von Lambda Parametern")
    void varLambdaParameter() {
        Consumer<String> printer1 = (var s) -> System.out.println(s);
        // statt
		Consumer<String> printer2 = s -> System.out.println(s);

        // Keine Mischung von "var" und deklarierten Typen möglich
		// BiConsumer<String, String> printer = (var s1, String s2) -> System.out.println(s1 + " " + s2);
    }

    @Test
    @DisplayName("Dies ist nützlich für die Nutzung von type annotations")
    void varLambdaParameterTypeAnnotations() {
//        BiConsumer<String, String> printerWithNullable = (@NonNull var s1, @Nullable var s2) -> System.out
//                .println(s1 + (s2 == null ? "" : " " + s2));
    }

}
