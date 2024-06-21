package assignment2021.gui;

import java.nio.channels.SelectableChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.gui.AbstractGUIPanel;

/**
 * The Class GUIPanel.
 * @author Ethan watts
 */
public class GUIPanel extends AbstractGUIPanel {

	private static final long serialVersionUID = -1257936627636425453L;

	/**
	 * Instantiates a new GUI panel.
	 *
	 * @param participants the participants
	 */
	public GUIPanel(Collection<Participant> participants) {
		super(participants);
		super.visualisedCurvesDetailsTextArea.setText("No trackers have been added");
		setDatasetSummaryBox();
	}
	
	/**
	 * Gets the participants.
	 *
	 * @return the participants
	 */
	public Collection<Participant> getParticipants() {
		return this.participants;
	}
	
	private void setDatasetSummaryBox() {
		super.datasetSummaryTextArea.setText("");
		int numMale = Participant.filterParticipantsByGender(participants, "M").size();
		int numFemale = Participant.filterParticipantsByGender(participants, "F").size();
		int lowestAge = Integer.MAX_VALUE;
		int highestAge = 0;
		
		super.datasetSummaryTextArea.append("Number of participants: " + participants.size());
		
		for (Participant person : participants) {
			int age = person.getAge();
			if (age > highestAge)
				highestAge = age;
			
			else if (age < lowestAge)
				lowestAge = age;	
		}
		
		super.datasetSummaryTextArea.append("\nThere are " + numMale + " males and " + numFemale + " females");
		super.datasetSummaryTextArea.append("\nThe lowest age in the dataset is: " + lowestAge + ", and the highest is: " + highestAge);
	}
	
	/**
	 * Sets the curve details box.
	 *
	 * @param selected the map of participants and trackers which are shown on screen
	 */
	protected void setCurveDetailsBox (Map<Participant, Set<Tracker>> selected) {
		if (selected.isEmpty())
			super.visualisedCurvesDetailsTextArea.setText("No trackers have been added");
		
		else {
			super.visualisedCurvesDetailsTextArea.setText("Displayed participants:\n");
			for (Participant person : selected.keySet()) {
				super.visualisedCurvesDetailsTextArea.append(
						"- " + person.getName() + ", " + person.getAge() + ", " + person.getGender() + "\n");
				super.visualisedCurvesDetailsTextArea.append("   - Displayed trackers: ");
				
				for (Tracker tr : selected.get(person))
					super.visualisedCurvesDetailsTextArea.append(tr.getName() + ", ");
				
				super.visualisedCurvesDetailsTextArea.append("\n\n");
			}
		}
	}


	@Override
	protected void loadParticipantsNames() {
		int i = 0;
		participantsArray = new String[participants.size()];

		for (Participant p : participants) {
			participantsArray[i] = p.getName();
			i++;
		}
		System.out.println(Arrays.toString(participantsArray));
	}
	

	@Override
	protected void loadTrackers() {
		Collection<String> trackerNames = ((Participant) participants.toArray()[0]).getAllTrackerNames();
		trackersArray = new String[trackerNames.size() + 1];
		trackersArray[0] = "all";
		int i = 1;
		for (String trackerName : trackerNames) {
			trackersArray[i] = trackerName;
			i++;
		}
		System.out.println(Arrays.toString(trackersArray));
	}
	
	
	@Override
	public void addListeners() {
		addCurvesButton.addActionListener(e -> {
			visualisedCurvesPanel.addToPanel(getSelectedParticipantName(), 
				getSelectedTrackersNames());
		});
		
		removeAllCurvesButton.addActionListener(e -> {
			visualisedCurvesPanel.resetDisplay();
		});
		
		comboListMeasurementType.addActionListener(e -> {
			visualisedCurvesPanel.repaint();
		});
		
		cbAverageValue.addItemListener(e -> {
			visualisedCurvesPanel.repaint();
		});
		
		cbIncrements.addItemListener(e -> {
			visualisedCurvesPanel.repaint();
		});
		
		cbMaxMinValues.addItemListener(e -> {
			visualisedCurvesPanel.repaint();
		});
	}

	/**
	 * Gets the selected participant name.
	 *
	 * @return the selected participant name
	 */
	@Override
	public String getSelectedParticipantName() {
		return comboListParticipants.getSelectedItem().toString();
	}

	/**
	 * Gets the selected trackers names.
	 *
	 * @return the selected trackers names
	 */
	@Override
	public String getSelectedTrackersNames() {
		if (comboListTrackers.getSelectedItem().toString() == "all") 
			return Tracker.FILTER_ANY;
		
		return comboListTrackers.getSelectedItem().toString();
	}

	/**
	 * Gets the selected measurement type.
	 *
	 * @return the selected measurement type
	 */
	@Override
	public MeasurementType getSelectedMeasurementType() {
		return MeasurementType.fromMeasurementName(comboListMeasurementType.getSelectedItem().toString());
	}

	/**
	 * Checks if is show average selected.
	 *
	 * @return true, if show average selected
	 */
	@Override
	public boolean isShowAverageSelected() {
		return cbAverageValue.isSelected();
	}

	/**
	 * Checks if is show min max selected.
	 *
	 * @return true, if show min max selected
	 */
	@Override
	public boolean isShowMinMaxSelected() {
		return cbMaxMinValues.isSelected();
	}

	/**
	 * Checks if is show increments selected.
	 *
	 * @return true, if show increments selected
	 */
	@Override
	public boolean isShowIncrementsSelected() {
		return cbIncrements.isSelected();
	}

}
