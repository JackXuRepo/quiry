package cscc01.summer2018.team11;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cscc01.summer2018.team11.database.Database;
import cscc01.summer2018.team11.user.Guest;
import cscc01.summer2018.team11.user.User;


/**
 * Main class for Quiry project.
 */
@SpringBootApplication
public class App
{
    // keep track of current logged in user
    public static User currentUser = new Guest("guest");

    public static void main( String[] args )
    {
    	Database.connect("quiry");
        SpringApplication.run(App.class, args);
    }

    
}
