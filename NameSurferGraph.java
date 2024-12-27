
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	private ArrayList<NameSurferEntry> namesGraph;

	public NameSurferGraph() {
		addComponentListener(this);

		namesGraph = new ArrayList<NameSurferEntry>();

	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		namesGraph.clear();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		namesGraph.add(entry);
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		backgroundDrawer();
		wholeLengthGraphsDrawer();

	}

	
	//Draws the background.
	private void backgroundDrawer() {
		upLine();
		downLine();
		straightLines();
		yearsWriter();
	}

	//Draws whole graphs for all the players
	private void wholeLengthGraphsDrawer() {
		for (int i = 0; i < namesGraph.size(); i++) {
			distance = getWidth() / NDECADES;
			graphsDrawer(namesGraph.get(i), i);
		}
	}

	private int distance;

	//Draws the upper line for the background.
	private void upLine() {
		distance = getWidth() / NDECADES;
		GLine gline = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(gline);
	}

	//Draws the down line for the background.
	private void downLine() {
		distance = getWidth() / NDECADES;
		GLine gline = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(gline);
	}

	//Draws the lines for the background.
	private void straightLines() {
		distance = getWidth() / NDECADES;

		for (int i = 0; i < NDECADES; i++) {
			GLine gline = new GLine(distance * i, 0, distance * i, getHeight());
			add(gline);
		}
	}

	//Writes the years below.
	private void yearsWriter() {
		distance = getWidth() / NDECADES;
		for (int i = 0; i < NDECADES; i++) {
			int whichYearN = 1900 + i * 10;
			String whichYear = "" + whichYearN;
			GLabel year = new GLabel(whichYear);
			add(year, distance * i, getHeight() - year.getHeight() * (1 / 2));
		}
	}

	//Gives Y of the graph by name's rank.
	private double yGiver(double rank) {
		double Y = 0;
		if (rank == 0) {

			Y = getHeight() - GRAPH_MARGIN_SIZE;

		} else {

			Y = (rank / MAX_RANK) * (getHeight() - 2 * GRAPH_MARGIN_SIZE) + GRAPH_MARGIN_SIZE;

		}
		return Y;
	}

	//Draws NDECADES small graphs and gives color, depends on which it is.
	private void graphsDrawer(NameSurferEntry entry, int i) {
		for (int j = 0; j < NDECADES; j++) {

			GLine gline = new GLine(distance * j, yGiver(entry.getRank(j)), distance * (j + 1),
					yGiver(entry.getRank(j + 1)));
			String nameYearS = "";
			if (entry.getRank(j) > 0) {

				nameYearS = nameYearS + entry.getName() + entry.getRank(j);

			} else {
				nameYearS = nameYearS + entry.getName() + "*";
			}
			GLabel nameYear = new GLabel(nameYearS);

			if (i % 4 == 0) {
				gline.setColor(Color.BLACK);
				nameYear.setColor(Color.BLACK);
			} else if (i % 4 == 1) {
				gline.setColor(Color.RED);
				nameYear.setColor(Color.RED);
			} else if (i % 4 == 2) {
				gline.setColor(Color.BLUE);
				nameYear.setColor(Color.BLUE);
			} else if (i % 4 == 3) {
				gline.setColor(Color.YELLOW);
				nameYear.setColor(Color.YELLOW);
			}
			add(gline);
			add(nameYear, distance * j, yGiver(entry.getRank(j)) - nameYear.getHeight() * (1 / 2));
		}
	}
	
	
	
	

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
