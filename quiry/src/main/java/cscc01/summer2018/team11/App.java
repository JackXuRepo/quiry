package cscc01.summer2018.team11;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import cscc01.summer2018.team11.database.Database;
import cscc01.summer2018.team11.lucene.Index;


/**
 * Main class for Quiry project.
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // start spring framework
        SpringApplication.run(App.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        // initialize components
        Database.connect("quiry");
        Index.initialize();
        System.out.println("Finished initialization");
    }

}
