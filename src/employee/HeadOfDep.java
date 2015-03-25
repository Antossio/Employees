package employee;

import java.io.Serializable;

public class HeadOfDep extends Manager implements Serializable {
    int koef;
    public HeadOfDep(String name, String surname, String db, String salaryDate, 
	    int salaryAmt, int bonus, int koef, String phone, String addres, String dep) {
	super(name, surname, db, salaryDate, salaryAmt*koef, bonus, phone, addres, dep);
	this.koef = koef;
    }

    public String toString() {
	return super.toString() + "Coefficient: " + koef + "\n";
    }
    public int getKoef() {
	return koef;
    }
}
