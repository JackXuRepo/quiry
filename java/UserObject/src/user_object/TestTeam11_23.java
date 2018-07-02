package user_object;

import java.io.File;

public class TestTeam11_23 {

	public static void main(String[] args) {
		File a = new File("/cmshome/heweijia/Desktop/TestF1");
		File b = new File("/cmshome/heweijia/Desktop/TestF2");
		
		//add account, set 3,2,1 as admin,instructor,student access level
		//null as photo image
		UserStorage.addUser("Kin","He","kin.he@mail.utoronto.ca","123456",3,null);
		UserStorage.addUser("Wayne","Why","wayne-why@mail.utoronto.ca","123456",2,null);
		UserStorage.addUser("Jack","Aay","jack-aay@mail.utoronto.ca","123456",1,null);
		UserStorage.addUser("Test","Delete","test-delete@mail.utoronto.ca","123456",1,null);
		
		System.out.println(UserStorage.getUserInfo("kin.he@mail.utoronto.ca") + "\n");
		System.out.println(UserStorage.getUserInfo("wayne-why@mail.utoronto.ca") + "\n");
		System.out.println(UserStorage.getUserInfo("jack-aay@mail.utoronto.ca") + "\n");
		System.out.println(UserStorage.getUserInfo("test-delete@mail.utoronto.ca") + "\n");
		
		//delete an account
		System.out.println(UserStorage.deleteUser("test-delete@mail.utoronto.ca"));
		System.out.println(UserStorage.getUserInfo("test-delete@mail.utoronto.ca"));
		
		//try to delete it again
		System.out.println(UserStorage.deleteUser("test-delete@mail.utoronto.ca") + "\n");
		
		//upload the file
		UserStorage.uploadAFile("wayne-why@mail.utoronto.ca", a);
		UserStorage.uploadAFile("wayne-why@mail.utoronto.ca", b);
		//upload to a deleted account
		UserStorage.uploadAFile("test-delete@mail.utoronto.ca", b);
		System.out.println(UserStorage.getUserInfo("wayne-why@mail.utoronto.ca") + "\n");
		
		//add classes to instructor
		UserStorage.editUserClasses("wayne-why@mail.utoronto.ca", "CSCC01");
		UserStorage.editUserClasses("wayne-why@mail.utoronto.ca", "CSCC02");
		//add two same class
		UserStorage.editUserClasses("wayne-why@mail.utoronto.ca", "CSCC01");
		System.out.println(UserStorage.getUserInfo("wayne-why@mail.utoronto.ca") + "\n");
		
		//TESTING ADMIN FUNCTION
		System.out.println(Admin.getUserInfo("kin.he@mail.utoronto.ca"));
	}

}
