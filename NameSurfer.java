
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */

	private NameSurferGraph graph;

	public void init() {

		graph = new NameSurferGraph();
		add(graph);
		infEntr();
	}

	private JLabel nameShower;
	private JTextField nameEntrer;
	private JButton graphBttn;
	private JButton clearBttn;
	private NameSurferDataBase allInfo = new NameSurferDataBase("names-data.txt");

	// Adds buttons, text and everything.
	private void infEntr() {
		name();

		txtFiller();

		graph();

		clear();

		addActionListeners();

	}
	
	private void name(){
		nameShower = new JLabel("Name: ");
		add(nameShower, SOUTH);
	}
	
	private void txtFiller(){
		nameEntrer = new JTextField(10);
		nameEntrer.addActionListener(this);
		add(nameEntrer, SOUTH);
	}
	
	private void graph(){
		graphBttn = new JButton("Graph");
		add(graphBttn, SOUTH);
	}
	
	private void clear(){
		clearBttn = new JButton("Clear");
		add(clearBttn, SOUTH);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == graphBttn || e.getSource() == nameEntrer) {

			if (nameEntrer.getText().length() > 0) {
				NameSurferEntry entry = allInfo.findEntry(nameEntrer.getText());

				println(entry.toString());
				graph.addEntry(entry);
				graph.update();

			}
		} else if (e.getSource() == clearBttn) {
			println("Clear");
			graph.clear();
			graph.update();
		}
	}
}
