package cscc01.summer2018.team11.user;

import java.util.HashSet;

import cscc01.summer2018.team11.file.FileInfo2;
import cscc01.summer2018.team11.file.FileStorage;

public class Admin extends User {

	public Admin(String userId, String email, String password,
			String firstName, String lastName) {
		super(userId, email, password, firstName, lastName, AccessLevel.ADMIN);
	}

	public User getUser(String userId) {
		for (Instructor obj : UserStorage.instructorUsers) {
			if (obj.getUserId() == userId) {
				return obj;
			}
		}
		for (Student obj : UserStorage.studentUsers) {
			if (obj.getUserId() == userId) {
				return obj;
			}
		}
		return null;
	}

	public HashSet<Integer> getFiles(String userId) {
		for (Instructor obj : UserStorage.instructorUsers) {
			if (obj.getUserId() == userId) {
				return obj.getFileIds();
			}
		}
		for (Student obj : UserStorage.studentUsers) {
			if (obj.getUserId() == userId) {
				return obj.getFileIds();
			}
		}
		return null;
	}

	public boolean addFile(FileInfo2 f) {
		return FileStorage.addFile(f);
	}

	public boolean deleteFile(int fileId) {
		return FileStorage.deleteFile(fileId);
	}

	public boolean setAccessLv(String userId, int lv) {
		for (Instructor obj : UserStorage.instructorUsers) {
			if (obj.getUserId() == userId) {
				obj.setAccessLv(lv);
				return true;
			}
		}
		for (Student obj : UserStorage.studentUsers) {
			if (obj.getUserId() == userId) {
				obj.setAccessLv(lv);
				return true;
			}
		}
		return false;
	}

	public boolean setEmail(String userId, String email) {
		for (Instructor obj : UserStorage.instructorUsers) {
			if (obj.getUserId() == userId) {
				obj.setEmail(email);;
				return true;
			}
		}
		for (Student obj : UserStorage.studentUsers) {
			if (obj.getUserId() == userId) {
				obj.setEmail(email);;
				return true;
			}
		}
		return false;
	}

	public boolean setPassword(String userId, String password) {
		for (Instructor obj : UserStorage.instructorUsers) {
			if (obj.getUserId() == userId) {
				obj.setPassword(password);;
				return true;
			}
		}
		for (Student obj : UserStorage.studentUsers) {
			if (obj.getUserId() == userId) {
				obj.setPassword(password);;
				return true;
			}
		}
		return false;
	}

	public boolean setFirstName(String userId, String firstName) {
		for (Instructor obj : UserStorage.instructorUsers) {
			if (obj.getUserId() == userId) {
				obj.setFirstName(firstName);;
				return true;
			}
		}
		for (Student obj : UserStorage.studentUsers) {
			if (obj.getUserId() == userId) {
				obj.setFirstName(firstName);;
				return true;
			}
		}
		return false;
	}

	public boolean setLastName(String userId, String lastName) {
		for (Instructor obj : UserStorage.instructorUsers) {
			if (obj.getUserId() == userId) {
				obj.setLastName(lastName);;
				return true;
			}
		}
		for (Student obj : UserStorage.studentUsers) {
			if (obj.getUserId() == userId) {
				obj.setLastName(lastName);;
				return true;
			}
		}
		return false;
	}

}
