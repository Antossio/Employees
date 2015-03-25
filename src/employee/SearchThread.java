package employee;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class SearchThread extends Thread {
    String name;
    String sname;
    GregorianCalendar bdStart;
    GregorianCalendar bdEnd;
    String bdS;
    String bdE;
    String phone;
    String addres;
    Win win;

    SearchThread (Win win, String name, String sname, GregorianCalendar bdStart,
	    GregorianCalendar bdEnd, String phone, String addres) {
	this.name = name;
	this.sname = sname;
	this.bdStart = bdStart;
	this.bdEnd = bdEnd;
	this.phone = phone;
	this.addres = addres;
	this.win = win;
    }

    SearchThread (Win win, String name, String sname, String bdStart, String bdEnd, 
	    String phone, String addres) {
	this.name = name;
	this.sname = sname;
	bdS = bdStart;
	bdE = bdEnd;
	this.phone = phone;
	this.addres = addres;
	this.win = win;
    }

    public void run() {
	if (Main.datab) {
	    try {
		if (bdS.equals("") && bdE.equals("")) {
		    String str = "";
		    String page = "http://" + Main.link + "/searchData.php";
		    URL url = new URL(page);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStream os = conn.getOutputStream();
		    FormUtility form = new FormUtility(os, null);
		    form.add("selector", 1+"");
		    form.add("name", name);
		    form.add("surname", sname);
		    form.add("phone", phone);
		    form.add("address", addres);
		    form.complete();
		    InputStream is = conn.getInputStream();
		    try {
			str = downloadPage(is);
		    } 
		    catch (MalformedURLException e1) {
			e1.printStackTrace();
		    } 
		    catch (IOException e) {
			e.printStackTrace();
		    }
		    win.ta.setText(DBoperation.forPrint(str));
		}
		if (!bdS.equals("") && bdE.equals("")) {
		    String str = "";
		    String page = "http://" + Main.link + "/searchData.php";
		    URL url = new URL(page);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStream os = conn.getOutputStream();
		    FormUtility form = new FormUtility(os, null);
		    form.add("selector", 2+"");
		    form.add("name", name);
		    form.add("surname", sname);
		    form.add("bdS", DBoperation.conv(bdS));
		    form.add("phone", phone);
		    form.add("address", addres);
		    form.complete();
		    InputStream is = conn.getInputStream();
		    try {
			str = downloadPage(is);
		    } 
		    catch (MalformedURLException e1) {
			e1.printStackTrace();
		    } 
		    catch (IOException e) {
			e.printStackTrace();
		    }
		    win.ta.setText(DBoperation.forPrint(str));
		}
		if (bdS.equals("") && !bdE.equals("")) {
		    String str = "";
		    String page = "http://" + Main.link + "/searchData.php";
		    URL url = new URL(page);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStream os = conn.getOutputStream();
		    FormUtility form = new FormUtility(os, null);
		    form.add("selector", 3+"");
		    form.add("name", name);
		    form.add("surname", sname);
		    form.add("bdE", DBoperation.conv(bdE));
		    form.add("phone", phone);
		    form.add("address", addres);
		    form.complete();
		    InputStream is = conn.getInputStream();
		    try {
			str = downloadPage(is);
		    } 
		    catch (MalformedURLException e1) {
			e1.printStackTrace();
		    } 
		    catch (IOException e) {
			e.printStackTrace();
		    }
		    win.ta.setText(DBoperation.forPrint(str));
		}

		if (!bdS.equals("") && !bdE.equals("")) {
		    String str = "";
		    String page = "http://" + Main.link + "/searchData.php";
		    URL url = new URL(page);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStream os = conn.getOutputStream();
		    FormUtility form = new FormUtility(os, null);
		    form.add("selector", 4+"");
		    form.add("name", name);
		    form.add("surname", sname);
		    form.add("bdS", DBoperation.conv(bdS));
		    form.add("bdE", DBoperation.conv(bdE));
		    form.add("phone", phone);
		    form.add("address", addres);
		    form.complete();
		    InputStream is = conn.getInputStream();
		    try {
			str = downloadPage(is);
		    } 
		    catch (MalformedURLException e1) {
			e1.printStackTrace();
		    } 
		    catch (IOException e) {
			e.printStackTrace();
		    }
		    win.ta.setText(DBoperation.forPrint(str));
		}
	    }
	    catch(Exception e) {
		System.out.println(e);
	    }
	}
	else {
	    Iterator it = Execution.dataBase.iterator();
	    Employee emp = null;
	    while (it.hasNext()) {
		emp = (Employee)it.next();
		if (emp != null) {
		    if (bdStart == null && bdEnd == null) {
			if(emp.getName().toLowerCase().startsWith(name)
				&& emp.getSurname().toLowerCase().startsWith(sname) 
				&& emp.getPhone().toLowerCase().contains(phone) 
				&& emp.getAddres().toLowerCase().contains(addres)) {
			    win.ta.append(emp.toString());
			    win.ta.append("-----------------------------\n");
			}
		    }
		    if (bdStart != null && bdEnd == null) {
			if(emp.getName().toLowerCase().startsWith(name)
				&& emp.getSurname().toLowerCase().startsWith(sname)
				&& emp.getPhone().toLowerCase().contains(phone)
				&& emp.getAddres().toLowerCase().contains(addres)
				&& (emp.getBD().after(bdStart) || emp.getBD().equals(bdStart))) {
			    win.ta.append(emp.toString());
			    win.ta.append("-----------------------------\n");
			}
		    }
		    if (bdStart == null && bdEnd != null) {
			if(emp.getName().toLowerCase().startsWith(name)
				&& emp.getSurname().toLowerCase().startsWith(sname)
				&& emp.getPhone().toLowerCase().contains(phone)
				&& emp.getAddres().toLowerCase().contains(addres)
				&& (emp.getBD().before(bdEnd) || emp.getBD().equals(bdEnd))) {
			    win.ta.append(emp.toString());
			    win.ta.append("-----------------------------\n");
			}
		    }
		    if (bdStart != null && bdEnd != null) {
			if (emp.getName().toLowerCase().startsWith(name)
				&& emp.getSurname().toLowerCase().startsWith(sname)
				&& emp.getPhone().toLowerCase().contains(phone)
				&& emp.getAddres().toLowerCase().contains(addres)
				&& ((emp.getBD().before(bdEnd) && emp.getBD().after(bdStart))
					|| emp.getBD().equals(bdStart) || emp.getBD().equals(bdEnd))) {
			    win.ta.append(emp.toString());
			    win.ta.append("-----------------------------\n");
			}
		    }
		}
	    }
	}
    }

    public String downloadPage(InputStream s) throws IOException {
	StringBuffer result = new StringBuffer();
	byte buffer[] = new byte[8192];
	int size = 0;
	do {
	    size = s.read(buffer);
	    if (size != -1) {
		result.append(new String(buffer, 0, size));
	    }
	} while (size != -1);
	String tmp = result.toString();
	return new String(tmp.getBytes(), "utf-8");
    }
}
