package cscc01.summer2018.team11;


import cscc01.summer2018.team11.user.Guest;
import cscc01.summer2018.team11.user.User;


/**
 * Main class for Quiry project.
 */
public class App
{
    // keep track of current logged in user
    public static User currentUser = new Guest("guest");

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
