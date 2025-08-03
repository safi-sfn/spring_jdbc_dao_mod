package service;

import dao.StudentDao;
import dao.StudentDaoImpl;
import dto.Student;
import factory.StudentDaoFactory;
import service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	String status = "";
	@Override
	public String addStudent(Student std) {
		studentDao = StudentDaoFactory.getStudentDao();
		status = studentDao.add(std);
		return status;
	}

	@Override
	public Student searchStudent(String sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		Student std = studentDao.search(sid);
		return std;
	}

	@Override
	public String deleteStudent(String sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		status = studentDao.delete(sid);
		return status;
	}

	


	

}
