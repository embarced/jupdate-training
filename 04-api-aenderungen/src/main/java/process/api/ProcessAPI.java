package process.api;

import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ProcessAPI {

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
        Process notepadProcess = new ProcessBuilder("notepad.exe").start();
        System.out.println("Notepad: ");
        printProcessInfo(notepadProcess);

        // Elternprozess ermitteln
        notepadProcess.toHandle().parent().ifPresent(parent -> System.out.println("Notepad parent: " + parent.pid()));

        // Kindprozesse ermitteln
        currentProcessHandle.children().forEach(childProcessHandle -> {
            System.out.println("Child: ");
            printProcessHandleInfo(childProcessHandle);
        });

        // ProcessHandle via PID zugreifen
        Optional<ProcessHandle> notepadProcessHandle = ProcessHandle.of(notepadProcess.pid());
        System.out.println("Notepad via ProcessHandle: ");
        notepadProcessHandle.ifPresent(ProcessAPI::printProcessHandleInfo);
        
        // Auf Beenden eines Prozesses reagieren
        CompletableFuture<Process> onExit = notepadProcess.onExit();
        onExit.thenAccept(process -> {
            System.out.println("Notepad onExit: ");
            printProcessInfo(notepadProcess);
        });
        
        System.out.println("Warte auf Notepad onExit...");
        onExit.get();
    }
    
    private static void printProcessInfo(Process process) {
        printProcessHandleInfo(process.toHandle());
        System.out.println("  Alive: " + process.isAlive());
    }
    
    private static void printProcessHandleInfo(ProcessHandle processHandle) {
        System.out.println("  PID: " + processHandle.pid());
        // Info Objekt ist ein "Snapshot" der Prozess-Informationen
        Info info = processHandle.info();
        info.command().ifPresent(command -> System.out.println("  Kommando: " + command));
        info.arguments().ifPresent(arguments -> System.out.println("  Argumente: " + arguments));
        info.startInstant().ifPresent(startedAt -> System.out.println("  Start: " + startedAt));
        info.user().ifPresent(user -> System.out.println("  User: " + user));
    }
}
