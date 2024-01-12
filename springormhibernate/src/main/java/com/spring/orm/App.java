package com.spring.orm;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class App 
{
    public static void main( String[] args )
    {
    	  System.out.println("Helo");
    	ApplicationContext context= new ClassPathXmlApplicationContext("config.xml");
    
   StudentDao studentDao = context.getBean("studentDao",StudentDao.class);

//	  Student student=new Student(90,"Ram","Delhi");
//	  
//	  int r=studentDao.insert(student); 
//      System.out.println("Done"+r);
//	 
   BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   boolean go=true;
      while(go) {
    	  System.out.println("PRESS 1 FOR ADD NEW STUDENT:");
    	   System.out.println("PRESS 2 FOR DISPLAY ALL STUDENTS:");
    	   System.out.println("PRESS 3 FOR GET DETAILS OF SINGLE STUDENTS:");
    	   System.out.println("PRESS 4 FOR DELETE STUDENTS:");
    	   System.out.println("PRESS 5 FOR UPDATE STUDENTS:");
    	   System.out.println("PRESS 6 FOR EXIT:");
    	   
    	   try {
    		   int input =Integer.parseInt(br.readLine());
    		  switch (input) {
			case 1:
				//Add A new Students
				
				System.out.println("Enter The User Id:");
				int uId=Integer.parseInt(br.readLine());
				
				System.out.println("Enter User Name:");
				String uName=br.readLine();
				
				System.out.println("Enter User City:");
				String uCity=br.readLine();
				
				Student s=new Student();
				s.setStudentId(uId);
				s.setStudentName(uName);
				s.setStudentCity(uCity);
				
				int r=studentDao.insert(s);
				System.out.println(r+"Student Added");
				System.out.println("******************************************************");
				System.out.println();
				
				break;
			case 2:
				//Display All Students
				System.out.println("************************************************************");
				List<Student> allStudents = studentDao.getAllStudents();
				for(Student st:allStudents) {
					System.out.println("Name:"+st.getStudentName());
					System.out.println("Id:"+st.getStudentId());
					System.out.println("City:"+st.getStudentCity());
					System.out.println("____________________________________________________________");
				}
				
				break;
				
			case 3:
				//Get Single Student Data
				
				System.out.println("Enter The User Id:");
				int userId=Integer.parseInt(br.readLine());
				Student student = studentDao.getStudent(userId);
				System.out.println("Name:"+student.getStudentName());
				System.out.println("Id:"+student.getStudentId());
				System.out.println("City:"+student.getStudentCity());
				break;
				
			case 4:
				//Delete Students
				
				System.out.println("Enter The User Id:");
				int id=Integer.parseInt(br.readLine());
				studentDao.deleteStudent(id);
				System.out.println("Student Deleted.......");
				break;
				
			case 5:
				//Update Students
				System.out.println("Enter the student id to be updated");
				int updatedId = Integer.parseInt(br.readLine());
				
				System.out.println("Press 1 for update the name");
				System.out.println("Press 2 for update the city\n");
				
				System.out.println("Enter your choise for what do you want to update?");
				int choice = Integer.parseInt(br.readLine());
				
				Student std1 = studentDao.getStudent(updatedId);
				
				String updatedName = std1.getStudentName();
				String updatedCity = std1.getStudentCity();
				
				switch (choice)
				{
				case 1:
					System.out.println("Enter the name to be updated");
					updatedName = br.readLine();
					//setting the student objects value
					std1 = new Student(updatedId, updatedName, updatedCity);
					System.out.println("Name updated successfully");
					break;

				case 2:
					System.out.println("Enter the city name to be updated");
					updatedCity = br.readLine();
					//setting the student objects value in another way
						std1.setStudentId(updatedId);
						std1.setStudentName(updatedName);
						std1.setStudentCity(updatedCity);
					System.out.println("City updated successfully");
					break;
				}
				
				studentDao.updateStudent(std1);
				System.out.println("Updated successfully\n");
				
				break;
			case 6:
				//For Exit
				go=false;
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			System.out.println("Invalid Input Try With Another One");
			System.out.println(e.getMessage());
			
		}
      }
      System.out.println("ThankYou For Using My Consle Based Application");
    }
}
