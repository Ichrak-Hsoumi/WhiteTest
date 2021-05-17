package de.tekup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import de.tekup.soap.models.whitetest.Address;
import de.tekup.soap.models.whitetest.Exam;
import de.tekup.soap.models.whitetest.Student;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;

@Service
public class WhiteTestService {

	public WhiteTestResponse ValidateWhitetTest(StudentRequest request) throws Exception  {
		
	    //WhiteTestResponse response = new ObjectFactory().createWhiteTestResponse();
		WhiteTestResponse response = new WhiteTestResponse();

		List<Student> students = new ArrayList<>();//Object students
		List<Exam> exams = new ArrayList<>();//Object exams
		
		List<String> listNotFound = response.getCriteriaMismatch();//ListNotFound
		
		
		Student student1= new Student();
		student1.setId(1);
		student1.setName("Ichrak");
		//student1.setAddress("Tunis");
		Address address1 = new Address();
		address1.setStreet("Le Kram");
		address1.setCity("Tunis");
		address1.setPostCode(1001);
		student1.setAdresse(address1);
		
		Student student2= new Student();
		student2.setId(2);
		student2.setName("Rim");
		//student2.setAddress("Ariana");
		Address address2 = new Address();
		address2.setStreet("3 Avenue la Liberte");
		address2.setCity("Tunis");
		address2.setPostCode(1001);
		student1.setAdresse(address2);
		
		Student student3= new Student();
		student3.setId(3);
		student3.setName("Mohamed");
		//student3.setAddress("Ariana");
		Address address3 = new Address();
		address3.setStreet("4 Avenue la Liberte");
		address3.setCity("Tunis");
		address3.setPostCode(1001);
		student1.setAdresse(address3);
		
		//Add Students To List students
		students.add(student1);
		students.add(student2);
		students.add(student3);
  
		
		
		Exam exam1= new Exam();
		exam1.setCode("WS-001");
		exam1.setName("Web Service");
		
		Exam exam2=new Exam();
		exam2.setCode("NJ-003");
		exam2.setName("Node JS");
		
		Exam exam3=new Exam();
		exam3.setCode("SB-005");
		exam3.setName("Spring Boot");
		
		//Add Exams To List exams
	    exams.add(exam1);
	    exams.add(exam2);
	    exams.add(exam3);
	    
	   
	    if (!(foundStudent(students, request.getStudentId()))) {
	    	
	    	listNotFound.add("Student with id not found!!");	
	    }
	    
	    if (!(foundExam(exams, request.getExamCode()))) {
	    	
	    	listNotFound.add("Exam with Code not found!!");
	    	
	    }
	    
	    if (listNotFound.isEmpty()) {
	    	
	    	if (foundStudent(students,  request.getStudentId())) {
	    		Student student = studentById(students, request.getStudentId());
	    		response.setStudent(student);
	    	}
	    	
	    	if (foundExam(exams, request.getExamCode())) {
	    		Exam exam = examByCode(exams, request.getExamCode());
	    		response.setExam(exam);
	    	}
	    	XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar("2019-04-25");
	    	response.setDate(xmlGregorianCalendar);
	    	/*String dateTimeString = response.toString();
	        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
	        response.setDate(date2); 	*/
	    }
	    
	   else {
	    	listNotFound.add("Student id or Exam Code is not valid!!");
	    }
	    	
	    
	    return response;
	}

	
	private boolean foundStudent(List<Student> students, int id) {
		//return students.stream().anyMatch(std->std.getId()==id);
		//return students.contains(id);
		for (Student s:students) {
			if (s.getId() == id) {
				return true;
			}
			return false;
		}
		throw new NoSuchElementException();
		
	}
	
	private boolean foundExam(List<Exam> exams,String code) {
		//return exams.stream().anyMatch(exm->exm.getCode().equalsIgnoreCase(code));
		//return exams.contains(code);
		for (Exam e:exams) {
			if (e.getCode().contains(code)) {
				return true;
			}
			return false;
		}
		throw new NoSuchElementException();
	}
	
	private Student studentById(List<Student> students, int id) {
		for (Student s:students) {
			if (s.getId() == id) {
				return s;
			}
		}
		throw new NoSuchElementException();
	}
	
	private Exam examByCode(List<Exam> exams, String code) {
		for (Exam e:exams) {
			if (e.getCode().contains(code)) {
				return e;
			}
		}
		throw new NoSuchElementException();
	}
	
	public List<Exam> findAll() {
		return null;
		 
	}

}