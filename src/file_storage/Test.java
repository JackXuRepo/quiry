package file_storage;

import java.io.File;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		File a = new File("/cmshome/heweijia/Desktop/testF1");
		File b = new File("/cmshome/heweijia/Desktop/testF2");
		String id1 = FileStorage.addFile("kin-he@hotmail.com",a);
		String id2 = FileStorage.addFile("kin.he@gmail.com",b);
		System.out.println("ID1 = " + id1);
		System.out.println("ID2 = " + id2);
		System.out.println(FileStorage.storage);
		FileStorage.deleteFile(id1);
		System.out.println(FileStorage.storage);
		System.out.println(FileStorage.getFileInfo(id1));
		
	}

}
