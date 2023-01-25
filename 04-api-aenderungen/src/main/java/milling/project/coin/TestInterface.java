package milling.project.coin;

public interface TestInterface {
    void x();

    default void y() {
        b();
    }

    private void z() {}

    static void a() {}

    private static void b() {}
}
