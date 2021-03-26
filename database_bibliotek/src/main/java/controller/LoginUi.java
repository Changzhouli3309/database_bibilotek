package controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtname = new JTextField();
	private JPasswordField txtpass = new JPasswordField();
	private JButton bl = new JButton("login");
	private JButton bg = new JButton("close");
	private JLabel msg = new JLabel();

	public LoginUi() {
		setTitle("Login");
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(4, 2, 10, 10));

		ActionListener close = e -> {
			System.exit(0);
		};
		bg.addActionListener(close);

		ActionListener login = e -> {
			String name = txtname.getText();
			String pass = "";
			for (char cha : txtpass.getPassword()) {
				pass += cha;
			}
			if (name.equals("asd") && pass.equals("123")) {
				JFrame newUi = new CustomerUi();
				newUi.setVisible(true);

				dispose();

			}else if(name.equals("qwe") && pass.equals("456")){
				JFrame newUi = new LibrarianUi();
				newUi.setVisible(true);
				
				dispose();
			}else {
				msg.setText("Fail to login");
			}
		};
		bl.addActionListener(login);

		c.add(new JLabel("name"));
		c.add(txtname);
		c.add(new JLabel("pass"));
		c.add(txtpass);
		c.add(bl);
		c.add(bg);
		c.add(msg);
		c.add(new JLabel("test customer: asd / 123 --- test librarian: qwe / 456"));
		pack();
	}
}
