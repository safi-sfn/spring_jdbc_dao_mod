package factory;

import dao.StudentDao;
import dao.StudentDaoImpl;

public class StudentDaoFactory {

	private static StudentDao studentDao;
	
	static {
		studentDao = new StudentDaoImpl();
	}
	public static StudentDao getStudentDao() {
		return studentDao;
	}
}
