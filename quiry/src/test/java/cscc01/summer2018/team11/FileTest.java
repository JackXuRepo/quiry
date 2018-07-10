package cscc01.summer2018.team11;

import java.io.File;

import cscc01.summer2018.team11.file.FileStorage;

public class FileTest {

	public static void main(String[] args) {
		File a = new File("/cmshome/heweijia/Desktop/testF1");
		File b = new File("/cmshome/heweijia/Desktop/testF2");
		String id1 = FileStorage.addFile("kin-he@hotmail.com",a, 0);
		String id2 = FileStorage.addFile("kin.he@gmail.com",b, 0);
		System.out.println("ID1 = " + id1);
		System.out.println("ID2 = " + id2);
		FileStorage.deleteFile(id1);
		System.out.println(FileStorage.getFileInfo(id1));
	}

}
