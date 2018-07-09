package cscc01.summer2018.team11;


import java.io.File;
import java.util.List;

import cscc01.summer2018.team11.file.FileStorage;
import cscc01.summer2018.team11.user.Admin;
import cscc01.summer2018.team11.user.UserStorage;


public class TestTeam11_15 {
	
	public static void main(String[] args) {
		File a = new File("/cmshome/heweijia/Desktop/TestF1");
		File b = new File("/cmshome/heweijia/Desktop/TestF2");
		UserStorage.addUser("Kin","He","kin.he@mail.utoronto.ca","123456",3,null);
		UserStorage.addUser("Wayne","Why","wayne-why@mail.utoronto.ca","123456",2,null);
		UserStorage.addUser("Jack","Aay","jack-aay@mail.utoronto.ca","123456",2,null);
		UserStorage.uploadAFile("wayne-why@mail.utoronto.ca", a, 2);
		UserStorage.uploadAFile("wayne-why@mail.utoronto.ca", b, 1);
		System.out.println(Admin.getUserInfo("wayne-why@mail.utoronto.ca") + "\n");
		
		List<String> id = FileStorage.getFileID("wayne-why@mail.utoronto.ca");
		String id1 = id.get(0);
		String id2 = id.get(1);
		
		// Instructor Jack tries to delete instructor wayne's file
		// Delete Fail
		UserStorage.deleteAFile("jack-aay@mail.utoronto.ca", id1);
		System.out.println(Admin.getUserInfo("wayne-why@mail.utoronto.ca") + "\n");
		
		// Admin tries to delete instructor wayne's file
		// Delete Success
		UserStorage.deleteAFile("kin.he@mail.utoronto.ca", id1);
		System.out.println(Admin.getUserInfo("wayne-why@mail.utoronto.ca") + "\n");
		
		// Author tries to delete the file that does not exist
		// Delete Fail
		UserStorage.deleteAFile("wayne-why@mail.utoronto.ca", id1);
		System.out.println(Admin.getUserInfo("wayne-why@mail.utoronto.ca") + "\n");
		
		// Author tries to delete the file
		// Delete Success
		UserStorage.deleteAFile("wayne-why@mail.utoronto.ca", id2);
		System.out.println(Admin.getUserInfo("wayne-why@mail.utoronto.ca"));
	}
}
