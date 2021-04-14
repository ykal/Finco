package framework;

import framework.controllers.CommandManager;
import framework.controllers.Controller;
import framework.models.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FinCo extends JFrame{
	private Controller controller;

	private Data model;
	private JTable JTable1;
	private JScrollPane JScrollPane1;

	FinCo parentFrame;
	JPanel JPanel1 = new JPanel();
	JButton JButton_Exit = new JButton();

	public FinCo (String title, DefaultTableModel model){
		this.parentFrame = this;

		/**
		 * setup the main panel
		 * - title
		 * - main panel
		 * - panel size
		 * - scroll pane
		 */
		setTitle(title);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(575, 310);
		setVisible(false);
		JTable1 = new JTable(model);
		JPanel1.setLayout(null);
		getContentPane().add(BorderLayout.CENTER, JPanel1);
		JPanel1.setBounds(0, 0, 580, 320);
		JScrollPane1 = new JScrollPane();
		JPanel1.add(JScrollPane1);
		JScrollPane1.setBounds(12, 92, 444, 160);
		JScrollPane1.getViewport().add(JTable1);
		JTable1.setBounds(0, 0, 420, 0);

		// Exit button
		JButton_Exit.setText("Exit");
		JButton_Exit.setBounds(468, 248, 96, 33);
		JPanel1.add(JButton_Exit);
		JButton_Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitApplication();
			}
		});

		// Window action listener
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
	}

	class SymWindow extends java.awt.event.WindowAdapter {
		public void windowClosing(WindowEvent event) {
			Object object = event.getSource();
			if (object == this)
				BankFrm_windowClosing(event);
		}
	}

	void BankFrm_windowClosing(WindowEvent event) {
		// to do: code goes here.

		BankFrm_windowClosing_Interaction1(event);
	}

	void BankFrm_windowClosing_Interaction1(WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	void exitApplication() {
		try {
			this.setVisible(false); // hide the Frame
			this.dispose(); // free the system resources
			System.exit(0); // close the application
		} catch (Exception e) {
		}
	}

	public void start() {
		try {
			// Add the following code if you want the Look and Feel
			// to be set to the Look and Feel of the native system.

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}

			// Create a new instance of our application's frame, and make it visible.
			this.setVisible(true);
		} catch (Throwable t) {
			t.printStackTrace();
			// Ensure the application exits with an error condition.
			System.out.println(t.getMessage() + "Error");
			System.exit(1);
		}
	}

	public void addComponent(Component component) {
		this.JPanel1.add(component);
	}

	public int getTableSelection() {
		return this.JTable1.getSelectionModel().getMinSelectionIndex();
	}

	public void setTableModel(Data data){this.JTable1.setModel(data);}

	public static void main(String[] args) {
		FinCo app = new FinCo("Finco", new DefaultTableModel());
		app.start();
	}
}
