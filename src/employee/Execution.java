package employee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import java.util.Iterator;

public class Execution {

	private static String name;
	private static String surname;
	private static String db;
	private static String salaryDate;
	private static int salaryAmt;
	private static String phone;
	private static String addres;
	private static String dep;
	private static int bonus;
	private static int koef;
	public static MyArrayList <Employee> dataBase;

	static boolean removeEmp(int id){
		return dataBase.remove(id);
	}

	static GregorianCalendar convert(String s){
		int year=Integer.parseInt(s.substring(s.lastIndexOf(".")+1));
		int month=Integer.parseInt(s.substring(s.indexOf(".")+1, s.lastIndexOf(".")))-1;
		int day=Integer.parseInt(s.substring(0,s.indexOf(".")));
		return new GregorianCalendar(year,month,day);
	}

	public static void addManager(String name, String surname, String db, String salaryDate, int salaryAmt, int bonus, String phone, String addres, String dep) throws IOException{
		if(Main.datab)
		{
			DBoperation.write(name, surname, db, salaryDate, salaryAmt, bonus, koef, phone, addres, dep);
		}
		else{
			Manager mn=new Manager(name, surname, db, salaryDate, salaryAmt, bonus, phone, addres, dep); 
			dataBase.add(mn);
		}
	}

	public static void addHeadOfDep(String name, String surname, String db, String salaryDate, int salaryAmt, int bonus, int koef, String phone, String addres, String dep) throws IOException{
		if(Main.datab)
		{
			DBoperation.write(name, surname, db, salaryDate, salaryAmt, bonus, koef, phone, addres, dep);
		}
		else{
			HeadOfDep hod=new HeadOfDep(name, surname, db, salaryDate, salaryAmt, bonus, koef, phone, addres, dep);
			dataBase.add(hod);
		}
	}

	public static void addStaff(String name, String surname, String db, String salaryDate, int salaryAmt, String phone, String addres, String dep) throws IOException{
		if(Main.datab)
		{
			DBoperation.write(name, surname, db, salaryDate, salaryAmt, bonus, koef, phone, addres, dep);
		}
		else{
			Employee em=new Employee(name, surname, db, salaryDate, salaryAmt, phone, addres, dep);
			dataBase.add(em);
		}
	}

	public static void save(){
		File f=new File("list.txt");
		try{
			FileWriter fw=new FileWriter(f);
			BufferedWriter bw=new BufferedWriter(fw);
			Iterator it=dataBase.iterator();
			while(it.hasNext())
			{
				Employee emp=(Employee) it.next();
				if(emp!=null)
				{
					bw.write("id: "+emp.getId());
					bw.newLine();
					bw.write("Name: "+emp.getName());
					bw.newLine();
					bw.write("Surname: "+emp.getSurname());
					bw.newLine();
					bw.write("Date of birth: "+emp.getBD().get(Calendar.DAY_OF_MONTH)+"."+(emp.getBD().get(Calendar.MONTH)+1)+"."+emp.getBD().get(Calendar.YEAR));
					bw.newLine();
					bw.write("Salary:");
					for(String s:emp.getSal())
						if(s!=null)
							bw.write(s+", ");
					bw.newLine();
					bw.write("Phone: "+emp.getPhone());
					bw.newLine();
					bw.write("Addres: "+emp.getAddres());
					bw.newLine();
					bw.write("Department: "+emp.getDep());
					bw.newLine();
					bw.write("----------------------");
					bw.newLine();
				}
			}
			bw.write("");
			bw.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
}
