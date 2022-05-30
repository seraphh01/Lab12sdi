package ro.ubb.catalog.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.catalog.client.ui.ClientConsole;

/**
 * Created by radu.
 */

public class ClientApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ro.ubb.catalog.client.config");
        ClientConsole console = context.getBean(ClientConsole.class);
        console.runConsole();
        System.out.println("bye ");
    }
}
