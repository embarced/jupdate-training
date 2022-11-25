package milling.project.coin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TryWithResources {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(TryWithResources.class.getResourceAsStream("/hello.txt")))) {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
