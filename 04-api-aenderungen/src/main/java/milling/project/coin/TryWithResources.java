package milling.project.coin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TryWithResources {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(TryWithResources.class.getResourceAsStream("hello.txt")));
        try (reader) {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String __ = "";
        String meinName = "Falk";

    }

    public void x(String ich_ignoriere_diese_variable, int zaehler) {
        int i = 1_000_000_000; // 1.000.000.000


    }
}
