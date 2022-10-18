package jupdate.process;

import com.sun.tools.javac.Main;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ProcessTests {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        // Aktuellen Prozess ermitteln
        ProcessHandle currentProcessHandle = ProcessHandle.current();
        System.out.println("Aktueller Prozess: ");
        printProcessHandleInfo(currentProcessHandle);

        AtomicInteger processCount = new AtomicInteger();
        Stream<ProcessHandle> allProcesses = ProcessHandle.allProcesses();
        allProcesses.forEach(ph -> processCount.incrementAndGet());
        System.out.println("Anzahl Prozesse: " + processCount);

        // Starten eines Prozesses (seit Java 5)
        Process lessProcess = new ProcessBuilder("/usr/bin/less").start();
        System.out.println("less: ");
        printProcessInfo(lessProcess);

        // Elternprozess ermitteln
        lessProcess.toHandle().parent().ifPresent(parent -> System.out.println("less parent: " + parent.pid()));

        // Kindprozesse ermitteln
        currentProcessHandle.children().forEach(childProcessHandle -> {
            System.out.println("Child: ");
            printProcessHandleInfo(childProcessHandle);
        });

        // ProcessHandle via PID zugreifen
        Optional<ProcessHandle> lessProcessHandle = ProcessHandle.of(lessProcess.pid());
        System.out.println("less via ProcessHandle: ");
        lessProcessHandle.ifPresent(ProcessTests::printProcessHandleInfo);

        // Auf Beenden eines Prozesses reagieren
        CompletableFuture<Process> onExit = lessProcess.onExit();
        onExit.thenAccept(process -> {
            System.out.println("less onExit: ");
            printProcessInfo(lessProcess);
        });

        System.out.println("Warte auf less onExit...");
        onExit.get();
    }

    private static void printProcessInfo(Process process) {
        printProcessHandleInfo(process.toHandle());
        System.out.println("  Alive: " + process.isAlive());
    }

    private static void printProcessHandleInfo(ProcessHandle processHandle) {
        System.out.println("  PID: " + processHandle.pid());
        // Info Objekt ist ein "Snapshot" der Prozess-Informationen
        ProcessHandle.Info info = processHandle.info();
        info.command().ifPresent(command -> System.out.println("  Kommando: " + command));
        info.arguments().ifPresent(arguments -> System.out.println("  Argumente: " + arguments));
        info.startInstant().ifPresent(startedAt -> System.out.println("  Start: " + startedAt));
        info.user().ifPresent(user -> System.out.println("  User: " + user));
    }
}
