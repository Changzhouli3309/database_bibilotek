package controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import data.LibrarianDao;

public class LibrarianUi extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private LibrarianDao lDao = new LibrarianDao();
	private JTextField input1 = new JTextField();
	private JTextField input2 = new JTextField();
	private JButton b1 = new JButton("search customer by name");
	private JButton b2 = new JButton("search customer by book");
	private JButton b3 = new JButton("log out");
	private JButton b4 = new JButton("edit Personal infor");
	private JTextArea msg = new JTextArea();

	public LibrarianUi() {
		setTitle("Librarian");
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new GridLayout(6, 2, 10, 10));
		c.add(new JLabel("Name:"));
		c.add(input1);
		c.add(new JLabel("Book:"));
		c.add(input2);
		c.add(b1);
		c.add(b2);
		c.add(msg);
		c.add(b3);
		c.add(b4);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		ActionListener al1 = e -> {
			String re = lDao.findCustomerByName(input1.getText());
			if(re!=null) {
				msg.setText(re);
			}else {
				msg.setText("not found");
			}
		};
		b1.addActionListener(al1);

		ActionListener al2 = e -> {
			String re = lDao.findCustomerByBook(input2.getText());
			if(re!=null) {
				msg.setText(re);
			}else {
				msg.setText("not found");
			}
		};
		b2.addActionListener(al2);
		
		ActionListener al3 = e -> {
			JFrame newUi = new LoginUi();
			newUi.setVisible(true);
			
			dispose();
		};
		b3.addActionListener(al3);
		
		ActionListener al4 = e -> {
			JFrame newUi = new EditingUi(1);
			newUi.setVisible(true);
			
			dispose();
		};
		b4.addActionListener(al4);

		pack();
	}
}
