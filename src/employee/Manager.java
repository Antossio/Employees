package employee;

import java.io.Serializable;

public class Manager extends Employee implements Serializable {
    int bonus;

    public Manager(String name, String surname, String db, String salaryDate, 
	    int salaryAmt, int bonus, String phone, String addres, String dep) {
	super(name, surname, db, salaryDate, salaryAmt+bonus, phone, addres, dep);
	this.bonus = bonus;
    }

    public String toString() {
	return super.toString() + "Bonus: " + bonus + "\n";
    }

    public int getBonus() {
	return bonus;
    }
}
