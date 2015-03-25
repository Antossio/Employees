package employee;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Employee implements Serializable {
    private String name;
    private String surname;
    private GregorianCalendar db;
    private String[] sal = new String [10];
    private int masSalIndex = 0;
    private String phone;
    private String addres;
    private String dep;
    static public int id = 0;
    int idEmployee;
    private int year;
    private int month;
    private int day;

    public Employee(String name, String surname, String db, 
	    String salaryDate, int salaryAmt, String phone, String addres, String dep) {
	this.name = name;
	this.surname = surname;
	year = Integer.parseInt(db.substring(db.lastIndexOf(".") + 1));
	month = Integer.parseInt(db.substring(db.indexOf(".") + 1, db.lastIndexOf("."))) - 1;
	day = Integer.parseInt(db.substring(0,db.indexOf(".")));
	this.db = new GregorianCalendar(year, month, day);
	sizeSal();
	sal[masSalIndex] = salaryDate + "/" + salaryAmt;
	masSalIndex++;
	this.phone = phone;
	this.addres = addres;
	this.dep = dep;
	id++;
	idEmployee = id;
    }

    private void sizeSal() {
	if (masSalIndex == sal.length) {
	    sal = Arrays.copyOf(sal, sal.length * 2);
	}
    }

    public String toString() {
	String toReturn = "";
	toReturn += "id: " + idEmployee + "\n" + "Name: " + name + "\n"
		+ "Surname: " + surname + "\n" + "Date of birth: " + day + "."
		+ (month + 1) + "." + year + "\n" + "Phone: " + phone
		+ "\n" + "Addres: " + addres + "\n" + "Department: " + dep + "\n" + "Salary: ";
	for (String s : sal) {
	    if (s != null) {
		toReturn += s + ", ";
	    }
	}
	toReturn += "\n";
	return toReturn;
    }

    public String getSurname() {
	return surname;
    }

    public GregorianCalendar getBD() {
	return db;
    }

    public int getId() {
	return idEmployee;
    }

    public String getName() {
	return name;
    }

    public String[] getSal() {
	return sal;
    }
    public String getPhone() {
	return phone;
    }
    public String getAddres() {
	return addres;
    }

    public String getDep() {
	return dep;
    }	
}

class byName implements Comparator {
    public int compare(Object ob1, Object ob2) {
	Employee emp1 = (Employee)ob1;
	Employee emp2 = (Employee)ob2;
	if (emp1 != null & emp2 != null) {
	    return emp1.getSurname().toLowerCase().compareTo(emp2.getSurname().toLowerCase());
	}
	else {
	    return 0;
	}
    }
}

class byId implements Comparator {
    public int compare(Object ob1, Object ob2) {
	Employee emp1 = (Employee)ob1;
	Employee emp2 = (Employee)ob2;
	if (emp1 != null & emp2 != null) {
	    return emp1.getId() - emp2.getId();
	}
	else {
	    return 0;
	}
    }
}

class byBD implements Comparator {
    public int compare(Object ob1, Object ob2) {
	Employee emp1 = (Employee)ob1;
	Employee emp2 = (Employee)ob2;
	if (emp1 != null & emp2 != null) {
	    if (emp1.getBD().after(emp2.getBD())) {
		return 1;
	    }
	    else if (emp1.getBD().before(emp2.getBD())) {
		return -1;
	    }
	    else {
		return 0;
	    }
	}
	else {
	    return 0;
	}
    }	
}

class byDep implements Comparator {
    public int compare(Object ob1, Object ob2) {
	Employee emp1 = (Employee)ob1;
	Employee emp2 = (Employee)ob2;
	if(emp1 != null & emp2 != null) {
	    return emp1.getDep().toLowerCase().compareTo(emp2.getDep().toLowerCase());
	}
	else {
	    return 0;
	}
    }
}