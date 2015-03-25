package employee;

import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Win extends JFrame implements ActionListener {
    String emp = "staff";
    Container cnt;
    JLabel idi = new JLabel();
    JLabel nm = new JLabel("Name:");
    JLabel sn = new JLabel("Surname:");
    JLabel bd = new JLabel("Date of birth:");
    JLabel sal = new JLabel("Salary amt:");
    JLabel sald = new JLabel("Salary date:");
    JLabel ph = new JLabel("Phone:");
    JLabel ad = new JLabel("Address:");
    JLabel dp = new JLabel("Department:");
    JLabel type = new JLabel("Category:");
    JLabel source = new JLabel("Source:");
    JLabel error = new JLabel("");
    JLabel formatBD = new JLabel("(DD.MM.YYYY)");

    JComboBox <String> sourceData = new JComboBox<String>();

    JLabel nameForsearch = new JLabel("Name (start with):");
    JLabel snameForSearch = new JLabel("Surname (start with):");
    JLabel bdForSearch = new JLabel("Date of birth(one/two val.):");
    JLabel phForSearch = new JLabel("Phone (contains):");
    JLabel adForSearch = new JLabel("Address (contains):");

    JTextField nameInpSearch = new JTextField();
    JTextField snameInpSearch = new JTextField();
    JTextField birdayInpsearchStart = new JTextField();
    JTextField birdayInpsearchEnd = new JTextField();
    JTextField foneInpSearch = new JTextField();
    JTextField adrInpSearch = new JTextField();

    JLabel bon = new JLabel("Bonus:");
    JLabel kof = new JLabel("Coefficient:");

    JTextField name = new JTextField();
    JTextField sname = new JTextField();
    JTextField birday = new JTextField();
    JTextField salar = new JTextField();
    JTextField saldate = new JTextField();
    JTextField fone = new JTextField();
    JTextField adr = new JTextField();
    JTextField depar = new JTextField();

    JTextField bons = new JTextField();
    JTextField coefic = new JTextField();

    JButton add = new JButton("Add");
    JComboBox <String> staff = new JComboBox<String>();
    JLabel saveToFile = new JLabel("Save to file:");
    JComboBox <String> sorted = new JComboBox <String>();

    JLabel rem = new JLabel("Input \"id\" to remove:");
    JLabel message = new JLabel();
    JTextField inputId = new JTextField();
    JButton remove = new JButton("Remove");

    JButton save = new JButton("Save");
    JButton search = new JButton("Search");

    TextArea ta = new TextArea();

    JTabbedPane tpane = new JTabbedPane();
    JPanel pan1 = new JPanel();
    JPanel pan2 = new JPanel();
    JPanel pan3 = new JPanel();
    int sourceVal;

    public Win() throws SQLException {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("Employees management system");
	setBounds(100, 100, 400, 450);
	setVisible(true);
	tpane.setBounds(0, 0, 400, 350);
	cnt = getContentPane();//создание контейнера окна для получения доступа к свойствам
	cnt.setLayout(null);
	cnt.add(tpane);
	tpane.add("Add employee", pan1);
	tpane.add("Remove employee", pan2);
	tpane.add("Search employee", pan3);
	pan1.setLayout(null);
	pan2.setLayout(null);
	pan3.setLayout(null);
	idi.setBounds(0, 0, 150, 20);
	nm.setBounds(0, 40, 80, 20);
	sn.setBounds(0, 60, 80, 20);
	bd.setBounds(0, 80, 80, 20);
	sal.setBounds(0, 100, 80, 20);
	sald.setBounds(0, 120, 80, 20);
	ph.setBounds(0, 140, 80, 20);
	ad.setBounds(0, 160, 80, 20);
	dp.setBounds(0, 180, 80, 20);
	bon.setBounds(0, 200, 80, 20);
	kof.setBounds(0, 220, 80, 20);
	type.setBounds(0, 20, 80, 20);
	source.setBounds(1, 355, 50, 20);
	sourceData.setBounds(86, 355, 150, 20);
	idi.setText("id:            " + (getMax() + 1));
	bon.setVisible(false);
	kof.setVisible(false);
	pan1.add(idi);
	pan1.add(nm);
	pan1.add(sn);
	pan1.add(bd);
	pan1.add(sal);
	pan1.add(sald);
	pan1.add(ph);
	pan1.add(ad);
	pan1.add(dp);
	pan1.add(bon);
	pan1.add(kof);
	pan1.add(type);
	cnt.add(source);
	cnt.add(sourceData);
	pan1.add(formatBD);
	name.setBounds(85, 40, 80, 20);
	sname.setBounds(85, 60, 80, 20);
	birday.setBounds(85, 80, 80, 20);
	formatBD.setBounds(166, 80, 100, 20);
	salar.setBounds(85, 100, 80, 20);
	saldate.setBounds(85, 120, 80, 20);
	fone.setBounds(85, 140, 80, 20);
	adr.setBounds(85, 160, 80, 20);
	depar.setBounds(85, 180, 80, 20);
	bons.setBounds(85, 200, 80, 20);
	coefic.setBounds(85, 220, 80, 20);
	bons.setVisible(false);
	coefic.setVisible(false);
	pan1.add(name);
	pan1.add(sname);
	pan1.add(birday);
	pan1.add(salar);
	pan1.add(saldate);
	pan1.add(fone);
	pan1.add(adr);
	pan1.add(depar);
	pan1.add(bons);
	pan1.add(coefic);
	rem.setBounds(0, 0, 120, 20);
	inputId.setBounds(125, 0, 50, 20);
	message.setBounds(0, 20, 190, 20);
	remove.setBounds(0, 40, 80, 20);
	pan2.add(message);
	pan2.add(rem);
	pan2.add(inputId);
	pan2.add(remove);
	remove.addActionListener(this);
	nameForsearch.setBounds(0, 0, 120, 20);
	snameForSearch.setBounds(0, 20, 120, 20);
	bdForSearch.setBounds(0, 40, 160, 20);
	phForSearch.setBounds(0, 60, 120, 20);
	adForSearch.setBounds(0, 80, 120, 20);
	pan3.add(nameForsearch);
	pan3.add(snameForSearch);
	pan3.add(bdForSearch);
	pan3.add(phForSearch);
	pan3.add(adForSearch);
	pan3.add(search);
	search.setBounds(0, 100, 100, 20);
	search.addActionListener(this);
	pan3.add(nameInpSearch);
	pan3.add(snameInpSearch);
	pan3.add(birdayInpsearchStart);
	pan3.add(birdayInpsearchEnd);
	pan3.add(foneInpSearch);
	pan3.add(adrInpSearch);
	pan3.add(ta);
	ta.setBounds(0, 125, 320, 200);
	nameInpSearch.setBounds(160, 0, 140, 20);
	snameInpSearch.setBounds(160, 20, 140, 20);
	birdayInpsearchStart.setBounds(160, 40, 70, 20);
	birdayInpsearchEnd.setBounds(230, 40, 70, 20);
	foneInpSearch.setBounds(160, 60, 140, 20);
	adrInpSearch.setBounds(160, 80, 140, 20);
	sourceData.addItem("database");
	sourceData.addItem("file");
	add.setBounds(0, 240, 85, 20);
	error.setBounds(0, 260, 300, 20);
	pan1.add(error);
	pan1.add(add);
	staff.addItem("staff");
	staff.addItem("manager");
	staff.addItem("head of department");
	staff.setBounds(85, 20, 80, 20);
	pan1.add(staff);
	saveToFile.setBounds(0, 300, 100, 20);
	pan1.add(saveToFile);
	sorted.addItem("as it is");
	sorted.addItem("sorted by Surname");
	sorted.addItem("sorted by id");
	sorted.addItem("sorted by birthday");
	sorted.addItem("sorted by department");
	sorted.setBounds(85, 300, 150, 20);
	pan1.add(sorted);
	pan1.add(save);
	save.setBounds(0, 280, 85, 20);
	sourceVal = sourceData.getSelectedIndex();
	save.addActionListener(this);
	add.addActionListener(this);
	tpane.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent arg0) {
		if(tpane.getSelectedIndex() == 1) {
		    initRem();
		}
	    }
	});
	sourceData.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if(sourceData.getSelectedIndex() == 0) {
		    Main.datab = true;
		    idi.setText("id:            "+(getMax() + 1));
		}
		else {
		    Main.datab = false;
		    idi.setText("id:            "+(Employee.id + 1));
		}
	    }
	});
	repaint();
	staff.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if(staff.getSelectedItem().equals("staff")) {
		    emp = "staff";
		    bon.setVisible(false);
		    kof.setVisible(false);
		    bons.setVisible(false);
		    coefic.setVisible(false);
		}
		else if(staff.getSelectedItem().equals("manager")) {
		    emp = "manager";
		    bon.setVisible(true);
		    kof.setVisible(false);
		    bons.setVisible(true);
		    coefic.setVisible(false);
		}
		else {
		    emp = "head of department";
		    bon.setVisible(true);
		    kof.setVisible(true);
		    bons.setVisible(true);
		    coefic.setVisible(true);
		}

	    }});
	WindowListener listener = new FrameListener();
	this.addWindowListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
	int inputSalaryAmt = 0;
	int inputKoef = 0;
	int inputBonus = 0;
	int id = 0;
	int maxId = 0;
	Object src = e.getSource();
	if (src == add) {
	    if (checkInpData(name.getText(), sname.getText(), birday.getText())) {			
		String inputName = name.getText();
		String inputSurname = sname.getText();
		String inputDb = birday.getText();
		if (!salar.getText().equals("")) {
		    inputSalaryAmt = Integer.parseInt(salar.getText());
		}
		String inputSalaryDate = saldate.getText();
		if (!bons.getText().equals("")) {
		    inputBonus = Integer.parseInt(bons.getText());
		}
		if (!coefic.getText().equals("")) {
		    inputKoef = Integer.parseInt(coefic.getText());
		}
		String inputPhone = fone.getText();
		String inputAddres = adr.getText();
		String inputDep = depar.getText();

		if (Main.datab) {
		    try {
			DBoperation.write(inputName, inputSurname, inputDb, inputSalaryDate, 
				inputSalaryAmt, inputBonus, inputKoef, inputPhone, inputAddres, inputDep);
		    } 
		    catch (IOException e1) {
			e1.printStackTrace();
		    }
		    initPan1(getMax());
		}
		else {
		    try {
			switch(emp) {
			case "staff":
			    Execution.addStaff(inputName, inputSurname, inputDb, inputSalaryDate,
				    inputSalaryAmt, inputPhone, inputAddres, inputDep);
			    break;
			case "manager":
			    Execution.addManager(inputName, inputSurname, inputDb, inputSalaryDate, 
				    inputSalaryAmt, inputBonus, inputPhone, inputAddres, inputDep);
			    break;
			case "head of department":
			    Execution.addHeadOfDep(inputName, inputSurname, inputDb, inputSalaryDate,
				    inputSalaryAmt, inputBonus, inputKoef, inputPhone, inputAddres, inputDep);
			}
		    }
		    catch(Exception er) {

		    }
		    initPan1(Employee.id);
		}
	    }
	}

	if (src == remove) {
	    if (!inputId.getText().equals("")) {
		id = Integer.parseInt(inputId.getText());
	    }
	    if (Main.datab) {
		if (DBoperation.remove(id) == 1) {
		    message.setText("Employee with id = "+id+" removed.");
		}
		else {
		    message.setText("No such id.");
		}
	    }
	    else {
		if (Execution.removeEmp(id)) {
		    message.setText("Employee with id = "+id+" removed.");
		}
		else { 
		    message.setText("No such id.");
		}
	    }
	}

	if (src == save){
	    if (Main.datab) {
		DBoperation.save((sorted.getSelectedIndex() + 1) + "");
	    }
	    else {
		switch(sorted.getSelectedIndex()) {
		case 0:
		    Execution.save();
		    break;
		case 1:
		    Execution.dataBase.sort(new byName());
		    Execution.save();
		    break;
		case 2:
		    Execution.dataBase.sort(new byId());
		    Execution.save();
		    break;
		case 3:
		    Execution.dataBase.sort(new byBD());
		    Execution.save();
		    break;
		case 4:
		    Execution.dataBase.sort(new byDep());
		    Execution.save();
		}
	    }
	}

	if (src == search){
	    ta.setText("");
	    GregorianCalendar bdStart = null;
	    GregorianCalendar bdEnd = null;
	    String n = nameInpSearch.getText();
	    String sname = snameInpSearch.getText();
	    if(!birdayInpsearchStart.getText().equals("")) {
		bdStart = Execution.convert(birdayInpsearchStart.getText());
	    }
	    if(!birdayInpsearchEnd.getText().equals("")) {
		bdEnd = Execution.convert(birdayInpsearchEnd.getText());
	    }
	    String ph = foneInpSearch.getText();
	    String ad = adrInpSearch.getText();
	    String stringDateStart = birdayInpsearchStart.getText();
	    String stringDateEnd = birdayInpsearchEnd.getText();
	    if(Main.datab) {
		SearchThread strd = new SearchThread(this, n, sname, stringDateStart, 
			stringDateEnd, ph, ad);
		strd.start();
	    }
	    else {
		SearchThread strd = new SearchThread(this, n, sname, bdStart, bdEnd, ph, ad);
		strd.start();
	    }
	}

    }
    public void initPan1(int maxId) {
	name.setText("");
	sname.setText("");
	birday.setText("");
	salar.setText("");
	saldate.setText("");
	fone.setText("");
	adr.setText("");
	depar.setText("");
	bons.setText("");
	coefic.setText("");
	idi.setText("id:            " + (maxId + 1));
	error.setText("");
    }

    public void initRem() {
	message.setText("");
	inputId.setText("");
    }

    public int sourseChoosen() {
	return sourceData.getSelectedIndex();
    }

    public int getMax() {
	int id = 0;
	String page = "http://" + Main.link + "/selectMax.php";
	try {
	    URL u = new URL(page);
	    String str = downloadPage(u);
	    if (str.trim().equals("")) {
		return 0;
	    }
	    id = Integer.parseInt(str.trim());
	} 
	catch (MalformedURLException e1) {
	    e1.printStackTrace();
	} 
	catch (IOException e) {
	    e.printStackTrace();
	}
	return id;
    }

    public String downloadPage(URL url) throws IOException {
	StringBuffer result = new StringBuffer();
	byte buffer[] = new byte[8192];
	InputStream s = url.openStream();
	int size = 0;
	do {
	    size = s.read(buffer);
	    if (size != -1) {
		result.append(new String(buffer, 0, size));
	    }
	} while (size != -1);
	return result.toString();
    }

    boolean checkInpData(String name, String sname, String bd) {
	if (name.equals("")) {
	    error.setText("Field 'Name' is empty");
	    return false;
	}
	if (name.length() < 2) {
	    error.setText("Name must be more than 2 symbols ");
	    return false;
	}
	if (sname.equals("")) {
	    error.setText("Field 'Surname' is empty");
	    return false;
	}
	if (sname.length() < 2) {
	    error.setText("Surname must be more than 2 symbols ");
	    return false;
	}
	if (bd.equals("")) {
	    error.setText("Field 'Birthday' is empty");
	    return false;
	}
	if (!bd.matches("^\\d{2}\\.\\d{2}\\.\\d{4}")) {
	    error.setText("Date of birth wrong format");
	    return false;
	}
	return true;
    }
}

class FrameListener extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
	if (!Main.datab) {
	    try {
		File f = new File("data.bin");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeInt(Employee.id);
		oos.writeObject(Execution.dataBase);
		oos.close();
	    }
	    catch(Exception er) {
		System.out.println(e);
	    }
	}
	System.exit(0);
    }
}