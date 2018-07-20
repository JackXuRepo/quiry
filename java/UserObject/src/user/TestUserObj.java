package cscc01.summer2018.team11;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import cscc01.summer2018.team11.user.Student;


public class TestUserObj {

	//Admin admin = new Admin("heweijia","kin.he@mail.utoronto.ca","123321","Kin","He");
	//Student student = new Student("yingweiy","wayne.ying@mail.utoronto.ca","000111","Wayne","Ying");
	//Instructor prof = new Instructor("xuhaosen","haosen.xu@mail.utoronto.ca","123567","Jack","Xu");

	public static String title = "CSCC01 Lec01 Note";
	public static String des = "lecture 1 note of CSCC01";
	public static String path = "/cmshome/heweijia/cscc01/lec1note.pdf";
	public static String wPath = "/cmshome/heweijia/cscc01/";

	@Test
	public void testStudentAddFile() {
		Student test = mock(Student.class);
		// right parameter
		when(test.addFile(title, des, 1, 1, "CSCC01", true, path)).thenReturn(true);
		// absolute path is wrong, need to be .pdf, .txt,or .html at the end
		when(test.addFile(title, des, 1, 1, "CSCC01", true, wPath)).thenReturn(false);
		// access level can not be higher than the current level(student = 1)
		when(test.addFile(title, des, 1, 2, "CSCC01", true, path)).thenReturn(false);

		assertEquals(test.addFile(title, des, 1, 1, "CSCC01", true, path), true);
		assertEquals(test.addFile(title, des, 1, 1, "CSCC01", true, wPath), false);
		assertEquals(test.addFile(title, des, 1, 2, "CSCC01", true, path), false);
	}

	@Test
	public void testStudentDeleteFile() {
		Student test = mock(Student.class);

		// assume file id 12345 is exist
		when(test.deleteFile(12345)).thenReturn(true);
		when(test.deleteFile(17652)).thenReturn(false);

		assertEquals(test.deleteFile(12345), true);
		assertEquals(test.deleteFile(17652), false);
	}

	@Test
	public void testStudentAddFileId() {
		Student test = new Student("yingweiy","wayne.ying@mail.utoronto.ca","000111","Wayne","Ying");

		// add the file id to the data space
		assertEquals(test.addFileId(51235), true);

		// add the same file id to the data space
		assertEquals(test.addFileId(51235), false);

		// add the wrong format file id to the data space
		assertEquals(test.addFileId(-2354), false);
	}

	@Test
	public void testStudentContainFileId() {
		Student test = new Student("yingweiy","wayne.ying@mail.utoronto.ca","000111","Wayne","Ying");
		// add two file id to the data space
		test.addFileId(55231);
		test.addFileId(51235);

		// check if the data space contains two of them and check if it contains a wrong id item
		assertTrue(test.getFileIds().contains(55231));
		assertTrue(test.getFileIds().contains(51235));
		assertFalse(test.getFileIds().contains(12345));
	}

	@Test
	public void testStudentAddCourse() {
		Student test = new Student("yingweiy","wayne.ying@mail.utoronto.ca","000111","Wayne","Ying");
		// add the two right courses to the data space
		assertTrue(test.addCourse("CSCC01"));
		assertTrue(test.addCourse("CSCC02"));

		// re-add a course to the data space
		assertFalse(test.addCourse("CSCC01"));

		// add a wrong format course to the data space
		assertFalse(test.addCourse("IT3301"));
	}

	@Test
	public void testStudentDeleteCourse() {
		Student test = new Student("yingweiy","wayne.ying@mail.utoronto.ca","000111","Wayne","Ying");
		// add the two right courses to the data space
		test.addCourse("CSCC01");
		test.addCourse("CSCC02");

		// delete a course
		test.deleteCourse("CSCC02");

		// check if they still there
		assertTrue(test.getCourses().contains("CSCC01"));
		assertFalse(test.getCourses().contains("CSCC02"));
	}

}
