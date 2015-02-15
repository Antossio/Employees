package employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static boolean datab;
	public static String link="xxxxxxxxxxxx"; //link to website with database. Can be provide on request  

	public static void main(String[] args) throws SQLException {
		Win win=new Win();
		if(win.sourseChoosen()==0)
		{datab=true;}
		else
		{datab=false;}

		File sourse=new File("data.bin");
		if(sourse.exists())
		{
			try
			{
				FileInputStream fis=new FileInputStream("data.bin");
				ObjectInputStream ois=new ObjectInputStream(fis);
				Employee.id=ois.readInt();
				Execution.dataBase=(MyArrayList<Employee>) ois.readObject();
			}
			catch(Exception e)
			{System.out.println(e);}
		}
		else
			Execution.dataBase=new MyArrayList <Employee>();
	}
}
