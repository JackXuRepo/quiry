package user_object;

import java.io.File;
import java.util.List;

import file_storage.FileStorage;

public class TestTeam11_5 {

	public static void main(String[] args) throws Exception {
		File a = new File("/cmshome/heweijia/Desktop/testF1");
		File b = new File("/cmshome/heweijia/Desktop/testF2");
		UserStorage.addUser("Kin","He","kin.he@mail.utoronto.ca","123456",2,null);
		UserStorage.addUser("Wayne","Why","wayne-why@mail.utoronto.ca","123456",1,null);
		
		UserStorage.uploadAFile("kin.he@mail.utoronto.ca",a,2);
		UserStorage.uploadAFile("kin.he@mail.utoronto.ca",b,1);
		List<String> id = FileStorage.getFileID("kin.he@mail.utoronto.ca");
		String id1 = id.get(0);
		String id2 = id.get(1);
		
		UserStorage.readAFile("kin.he@mail.utoronto.ca",id1);
		UserStorage.readAFile("wayne-why@mail.utoronto.ca",id1);
	}

}