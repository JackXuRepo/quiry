package cscc01.summer2018.team11;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cscc01.summer2018.team11.database.Database;


/**
 * Main class for Quiry project.
 */
@SpringBootApplication
public class App
{

    public static void main( String[] args )
    {
        Database.connect("quiry");
        SpringApplication.run(App.class, args);
    }

}
