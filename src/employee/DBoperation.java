package employee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

public class DBoperation {

	public static int write(String inname, String surname, String db, String salaryDate, int salaryAmt, int bonus, int koef, String phone, String addres, String dep) throws IOException{
		int id=0;
		String page="http://"+Main.link+"/datahandling.php";
		URL url=new URL(page);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStream os=conn.getOutputStream();
		FormUtility form = new FormUtility(os, null);
		form.add("dept",dep);
		form.add("name",inname);
		form.add("surname",surname);
		form.add("birthday",conv(db));
		form.add("bonus",bonus+"");
		form.add("salamt",salaryAmt+"");
		form.add("saldate",conv(salaryDate));
		form.add("phone",phone);
		form.add("address",addres);
		form.add("koef",koef+"");
		form.complete();
		InputStream is = conn.getInputStream();
		return id;
	}

	public static int remove(int idRemove){
		int isRemoved=0;
		try
		{
			String page="http://"+Main.link+"/delId.php";
			URL url=new URL(page);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStream os=conn.getOutputStream();
			FormUtility form = new FormUtility(os, null);
			form.add("id",idRemove+"");
			form.complete();
			InputStream is = conn.getInputStream();
			try {
				String str = downloadPage(is);
				isRemoved=Integer.parseInt(str.trim());
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
		return isRemoved;
	}



	public static void save(String s){
		File f=new File("list.txt");
		try{

			String str="";
			String page="http://"+Main.link+"/saveToFile.php";
			URL url=new URL(page);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStream os=conn.getOutputStream();
			FormUtility form = new FormUtility(os, null);
			form.add("selector",s);
			form.complete();
			InputStream is = conn.getInputStream();
			try {
				str = downloadPage(is);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileWriter fw=new FileWriter(f);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(forPrint(str));
			bw.write("");
			bw.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

	public static String conv(String s){
		int year=Integer.parseInt(s.substring(s.lastIndexOf(".")+1));
		int month=Integer.parseInt(s.substring(s.indexOf(".")+1, s.lastIndexOf(".")));
		int day=Integer.parseInt(s.substring(0,s.indexOf(".")));
		return year+"-"+month+"-"+day;
	}

	public static String forPrint(String s){
		StringTokenizer  token=new StringTokenizer(s,"\n");
		String concat="";
		while(token.hasMoreTokens())
		{
			String z=token.nextToken();
			if(!z.equals("\r"))
			{
				StringTokenizer tmp=new StringTokenizer(z,";");
				concat=concat+"id: "+tmp.nextToken()+"\r\nName: "+tmp.nextToken()+"\r\nSurname: "+tmp.nextToken()+"\r\nDate of birth: "+tmp.nextToken()+"\r\nSalary: "+tmp.nextToken()+"/"+tmp.nextToken()+"\r\nBonus: "+tmp.nextToken()+"\r\nCoefficient: "+tmp.nextToken()+"\r\nPhone: "+tmp.nextToken()+"\r\nAddres: "+tmp.nextToken()+"\r\nDepartment: "+tmp.nextToken()+"\r\n----------------------\r\n";
			}
		}
		return concat;
	}

	public static String downloadPage(InputStream s) throws IOException
	{
		StringBuffer result = new StringBuffer();
		byte buffer[] = new byte[8192];
		int size = 0;
		do
		{
			size = s.read(buffer);
			if (size != -1)
				result.append(new String(buffer, 0, size));
		} while (size != -1);

		String tmp=result.toString();
		return new String(tmp.getBytes(), "windows-1251");
	}
}
