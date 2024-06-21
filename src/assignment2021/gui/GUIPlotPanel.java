package assignment2021.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.gui.AbstractGUIPanel;
import assignment2021.codeprovided.gui.BasicGUIPlotPanel;

/**
 * A class to represent the GUI panel where the selected curves will be plot
 * using Java 2D. You are expected to write this class.
 *
 *@author Ethan Watts
 */

public class GUIPlotPanel extends BasicGUIPlotPanel {

	/** Generated Serial version UID. */
	private static final long serialVersionUID = -1482643158587603732L;
	private GUIPanel panel = (GUIPanel) this.parentGUIPanel;
	private Map<Participant, Set<Tracker>> selected;
	private final int PADDING_AXIS = 80;

	/**
	 * Instantiates a new GUI plot panel.
	 *
	 * @param parentGUIPanel the parent GUI panel
	 */
	public GUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
		super(parentGUIPanel);
		this.setBackground(Color.white);
		
		this.selected = new HashMap<>();
	}
	
	/**
	 * Resets display area
	 */
	public void resetDisplay() {
		Graphics g = this.getGraphics();
		selected.clear();
		g.clearRect(0, 0, getWidth(), getHeight());
		
		panel.setCurveDetailsBox(selected);
	}
	
	/**
	 * Gets the participant from provided name.
	 *
	 * @param pName the name of a participant
	 * @return the participant object
	 */
	private Participant getParticipantFromName(String pName) {
		for (Participant person : panel.getParticipants()) {
			if (person.getName().equals(pName))
				return person;
		}
		return null;
	}
	
	/**
	 * Adds the selected participant and tracker combo to the screen
	 *
	 * @param participant the participant
	 * @param tracker the tracker
	 */
	public void addToPanel(String participant, String tracker) {
		Participant p = getParticipantFromName(participant);
		Collection<Tracker> trackers = Tracker.filterTrackersByName(
			p.getAllTrackers(), tracker);
		
		Set<Tracker> setTr = new HashSet<>();
		
		setTr.addAll(trackers);
		
		if (selected.containsKey(p))
			selected.get(p).addAll(setTr);
		else
			selected.put(p, setTr);
		
		panel.setCurveDetailsBox(selected);
		
		repaint();
	}
	
	/**
	 * Draws axis for the graphs.
	 *
	 * @param g the Graphics2D object
	 * @return array containing the highest number of measurements for selected trackers
	 * and the highest measurement value
	 */
	private int[] drawAxis(Graphics2D g) {
		FontMetrics fm = getFontMetrics(getFont());
		
		g.setColor(new Color(0));
		g.drawLine(0, 0, 0, -getHeight() + 2 * PADDING_AXIS);
		g.drawLine(0, 0, getWidth() - 2 * PADDING_AXIS, 0);
		
		int highestCount = 1;
		int highestMeasure = 1;
		
		// For each participant selected
		for (Participant person : selected.keySet()) {
			// For each participant's trackers selected
			for (Tracker t : selected.get(person)) {
				List<Measurement> curM = t.getMeasurementsForType(
						panel.getSelectedMeasurementType());
				
				if (curM.isEmpty())
					continue;
				
				if (curM.size() > highestCount)
					highestCount = curM.size();
				
				for (Measurement measure : curM) {
					int currentValue = measure.getValue().intValue();
					if (currentValue > highestMeasure)
						highestMeasure = currentValue;
				}
			}
		}
		
		double xSpacing = ((double) getWidth() - 2 * PADDING_AXIS) / highestCount;
		double ySpacing = ((double) getHeight() - 2 * PADDING_AXIS) / (highestMeasure-1);
		
		// x axis
		for (int i=0; i<=highestCount; i+= 20) {
			// draw the labels 
			g.setColor(new Color(0));
			g.drawString(String.valueOf(i), (int) (i * xSpacing) - fm.stringWidth(String.valueOf(i))/2, 20);
			
			// draw the axis divisions
			g.setColor(new Color(205, 205 , 205));
			g.drawLine((int) (i * xSpacing), 10, (int) (i * xSpacing), -getHeight() + 2 * PADDING_AXIS);
		}
						
		// y axis
		for (int i=0; i<=highestMeasure; i+=10) {
			// draw the labels 
			g.setColor(new Color(0));
			g.drawString(String.valueOf(i), -15 - fm.stringWidth(String.valueOf(i)), (int) (-i * ySpacing));

			// draw the axis divisions
			g.setColor(new Color(205, 205 , 205));
			g.drawLine(-10, (int) (-i * ySpacing), getWidth() - 2 * PADDING_AXIS, (int) (-i * ySpacing));			
		}
		
		return new int[] {highestCount, highestMeasure};
	}
	
	/**
	 * Draws the graphs.
	 *
	 * @param g the Graphics2D object
	 */
	public void drawGraphs(Graphics2D g) {
		Random ranGen = new Random();
		
		int[] highestVals = drawAxis(g);
		int highestCount = highestVals[0];
		int highestMeasure = highestVals[1];
		
		int spacing = 0;
		
		// For each participant selected
		for (Participant person : selected.keySet()) {
			// For each participant's trackers selected
			for (Tracker t : selected.get(person)) {
				
				// randomised dark colours for every tracker on screen
				g.setColor(new Color(Color.HSBtoRGB(
						ranGen.nextFloat()*100, ranGen.nextFloat(), (float)0.6)));
				
				// draws the key of selected combinations
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
				g.drawString(person.getName() + " -", spacing, 50);
				g.drawString(t.getName(), spacing, 65);
				spacing +=40;
				
				List<Point> points = new ArrayList<>();
				List<Measurement> curM = t.getMeasurementsForType(
					panel.getSelectedMeasurementType());
				
				if (curM.isEmpty())
					continue;
				
				double xScale = ((double) getWidth() - 2 * PADDING_AXIS) / highestCount;
				double yScale = ((double) getHeight() - 2 *  PADDING_AXIS) / highestMeasure;
				
				for (int i=0; i<curM.size(); i++) {
					int curMeasVal = curM.get(i).getValue().intValue();
					points.add(new Point((int) (i * xScale), (int) (-curMeasVal * yScale)));
						
				}
				
				for (int i=0; i<points.size()-1; i++)
					g.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
			}
		}
	}
 
	/**
	 * Paint component.
	 *
	 * @param g the Graphics object
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.translate(PADDING_AXIS, getHeight() - PADDING_AXIS);		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		drawGraphs(g2);
		
	}
}
