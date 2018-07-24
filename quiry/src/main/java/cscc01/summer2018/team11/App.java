package cscc01.summer2018.team11;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cscc01.summer2018.team11.database.Database;
import cscc01.summer2018.team11.file.FileStorage;
import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.user.UserStorage;


/**
 * Main class for Quiry project.
 */
@SpringBootApplication
public class App
{

    public static void main( String[] args )
    {
        // initialize components
        Database.connect("quiry");
        FileStorage.initialize();
        UserStorage.initialize();
        Index.initialize();

        // start spring framework
        SpringApplication.run(App.class, args);
    }

}
