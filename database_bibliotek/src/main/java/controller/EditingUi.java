package controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import data.EditingDao;
import model.Employee;

public class EditingUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private EditingDao eDao = new EditingDao();
	private JTextField input1 = new JTextField();
	private JTextField input2 = new JTextField();
	private JTextField input3 = new JTextField();
	private JTextField input4 = new JTextField();
	private JTextField input5 = new JTextField();
	private JTextField input6 = new JTextField();
	private JTextField input7 = new JTextField();
	private JButton b1 = new JButton("edit");
	private JButton b2 = new JButton("cancel");
	private JLabel msg = new JLabel();

	public EditingUi(int id) {
		Employee emp = eDao.getEmployeeById(id);
		setTitle("Editing");
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new GridLayout(6, 2, 10, 10));

		c.add(new JLabel("id:"));
		c.add(new JLabel(String.valueOf(emp.getId())));

		c.add(new JLabel("Name:"));
		input1.setText(emp.getName());
		c.add(input1);

		c.add(new JLabel("Adrees:"));
		input2.setText(emp.getAdress());
		c.add(input2);

		c.add(new JLabel("Tel1:"));
		input3.setText(String.valueOf(emp.getTel1()));
		c.add(input3);

		c.add(new JLabel("Tel2:"));
		input4.setText(String.valueOf(emp.getTel2()));
		c.add(input4);

		c.add(new JLabel("Tel3:"));
		input5.setText(String.valueOf(emp.getTel3()));
		c.add(input5);

		c.add(new JLabel("Salry:"));
		input6.setText(String.valueOf(emp.getSalary()));
		c.add(input6);

		c.add(new JLabel("Holiday left:"));
		input7.setText(String.valueOf(emp.getHolidayLeft()));
		c.add(input7);

		c.add(b1);
		c.add(b2);
		c.add(msg);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		ActionListener al1 = e -> {
			if (checkInputs()) {
				emp.setName(input1.getText());
				emp.setAdress(input2.getText());
				emp.setTel1(Integer.parseInt(input3.getText()));
				emp.setTel2(Integer.parseInt(input4.getText()));
				emp.setTel3(Integer.parseInt(input5.getText()));
				emp.setSalary(Integer.parseInt(input6.getText()));
				emp.setHolidayLeft(Integer.parseInt(input7.getText()));
				eDao.uppdateEmploee(emp);
				msg.setText("uppdate");
			} else {
				msg.setText("Fail to uppdate");
			}

		};
		b1.addActionListener(al1);

		ActionListener al2 = e -> {
			JFrame newUi = new LibrarianUi();
			newUi.setVisible(true);

			dispose();
		};
		b2.addActionListener(al2);

		pack();
	}

	private boolean checkInputs() {
		String[] toCheckList = { input1.getText(), input2.getText(), 
				input3.getText(), input4.getText(),	input5.getText(), 
				input6.getText(), input7.getText() };

		for (int i = 0; i < toCheckList.length; i++) {
			String pattern = "^[0-9]+$";
			if (i == 0) {
				pattern = "^[a-zA-Z ]+$";
			}
			if (i == 1) {
				pattern = "^[a-zA-Z0-9 ,]+$";
			}
			if (toCheckList[i].equals("")) {
				return false;
			} else if (!rexgexCheck(toCheckList[i], pattern)) {
				return false;
			}
		}

		return true;
	}

	private boolean rexgexCheck(String toCheck, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(toCheck);
		return matcher.find();
	}

}
