
package factory;

import service.StudentService;
import service.StudentServiceImpl;

public class StudentServiceFactory {
	
    private static StudentService studentService;
    static {
    	studentService = new StudentServiceImpl();
    }
    public static StudentService getStudentService(){
    	return studentService;
    }
}
 