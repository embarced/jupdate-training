package resources.utf8;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class ResourceBundles {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        ResourceBundle resourceBundleUTF = ResourceBundle.getBundle("test-UTF");
        System.out.println(resourceBundleUTF.getString("test"));
        
        ResourceBundle resourceBundleISO = ResourceBundle.getBundle("test-ISO");
        System.out.println(resourceBundleISO.getString("test"));
    }
}
