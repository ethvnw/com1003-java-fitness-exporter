package assignment2021.handoutquestions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.*;
import assignment2021.codeprovided.handoutquestions.AbstractFitnessQuestions;


/**
 * Generates the questions and answers to match the brief
 * @author Ethan Watts
 */
public class FitnessQuestions extends AbstractFitnessQuestions {


	public FitnessQuestions(Collection<Participant> participants) {
		super(participants);
	}


	@Override
	public String toString() {
		DecimalFormat f = new DecimalFormat("0.00");
		
		String q10 = "";
		for (int i=0; i<this.getHRMCountPerFT().size(); i++) {
			q10 += "	Total count of heart rate measurements for FT"+i+": " 
				+ getHRMCountPerFT().get(i)+"\n";
		}
			
		String q12 = "";
		for (int i=0; i<this.getStepsMCountForFT234().size(); i++) {
			int tracker = i + 2;
			q12 += "	Total count of step measurements for FT"+tracker+": " 
				+ getStepsMCountForFT234().get(i)+"\n";
		}
		
		String q14 = "";
		for(String name : this.getHighestNumberOfStepsParticipants())
			q14 += "	- " + name + "\n";
			
		String q15 = "";
		for(String name : this.getHighestWalkedDistanceParticipants())
			q15 += "	- " + name + "\n";
		
		String q17 = "";
		for(String name : this.getAvgStepsAboveGlobalParticipantsForFT1()) {
			Participant par = Participant.filterParticipantsByName(getParticipants(), name).iterator().next();
			String[] trackers = {"FT1"};
			q17 += "	- " + name + " with " + f.format(getPersonalAvgSpecMTypeSpecTrackers(
					par, MeasurementType.STEPS, trackers)) + " steps\n";
		}
		
		String q18 = "";
		for(String name : this.getAvgStepsBelowGlobalParticipantsForFT1()){
			Participant par = Participant.filterParticipantsByName(getParticipants(), name).iterator().next();
			String[] trackers = {"FT1"};
			q18 += "	- " + name + " with " + f.format(getPersonalAvgSpecMTypeSpecTrackers(
					par, MeasurementType.STEPS, trackers)) + " steps\n";
		}
		
		String q20 = "";
		for(String name : this.getAvgEEAboveGlobalParticipantsForFT2FT3()) {
			Participant par = Participant.filterParticipantsByName(getParticipants(), name).iterator().next();
			String[] trackers = {"FT2", "FT3"};
			q20 += "	- " + name + " with " + f.format(getPersonalAvgSpecMTypeSpecTrackers(
					par, MeasurementType.ENERGY_EXPENDITURE, trackers)) + " energy expenditure\n";
		}

		String q21 = "";
		for(String name : this.getAvgEEBelowGlobalParticipantsForFT2FT3()) {
			Participant par = Participant.filterParticipantsByName(getParticipants(), name).iterator().next();
			String[] trackers = {"FT2", "FT3"};
			q21 += "	- " + name + " with " + f.format(getPersonalAvgSpecMTypeSpecTrackers(
					par, MeasurementType.ENERGY_EXPENDITURE, trackers)) + " energy expenditure\n";
		}
		
		String q23 = "";
		for(String name : this.getAvgHRAboveGlobalParticipants()) {
			Participant par = Participant.filterParticipantsByName(getParticipants(), name).iterator().next();
			String[] trackers = {"All"};
			q23 += "	- " + name + " with " + f.format(getPersonalAvgSpecMTypeSpecTrackers(
					par, MeasurementType.HEART_RATE, trackers)) + " heart rate\n";
		}
		
		String q24 = "";
		for(String name : this.getAvgHRBelowGlobalParticipants()) {
			Participant par = Participant.filterParticipantsByName(getParticipants(), name).iterator().next();
			String[] trackers = {"All"};
			q24 += "	- " + name + " with " + f.format(getPersonalAvgSpecMTypeSpecTrackers(
					par, MeasurementType.HEART_RATE, trackers)) + " heart rate\n";
		}
		
		String masterQuestions 
		= "\nQ1.	Total Number of participants: " + this.getTotalParticipants() 
			+ "\n\n"
			
		+ "Q2.	Number of participants with heart rate measurements: " 
			+ this.getParticipantsNumberWithHRM() + " out of " + 
			this.getTotalParticipants() + "\n\n"
			
		+ "Q3.	Number of participants with step measurements: " 
			+ this.getParticipantsNumberWithStepsM() + " out of " 
			+ this.getTotalParticipants() + "\n\n"
			
		+ "Q4.	Number of participants with distance measurements: " 
			+ this.getParticipantsNumberWithDistanceM() + " out of " 
			+ this.getTotalParticipants() + "\n\n"
			
		+ "Q5.	Number of participants with energy expenditure measurements: " 
			+ this.getParticipantsNumberWithEEM() + " out of " 
			+ this.getTotalParticipants() + "\n\n"
			
		+ "Q6.	Total number of heart rate measurements in dataset: " 
			+ this.getTotalHRMCount() + "\n\n"
			
		+ "Q7.	Total number of step measurements in dataset: " 
			+ this.getTotalStepsCount() + "\n\n"
			
		+ "Q8.	Total number of distance measurements in dataset: " 
			+ this.getTotalDistanceCount() + "\n\n"
			
		+ "Q9.	Total number of energy expenditure measurements in dataset: " 
			+ this.getTotalEECount() + "\n\n"
			
		+ "Q10." + q10 + "\n"
		
		+ "Q11.	Total number of energy expenditure measurements for FT1: " 
			+ this.getEEMCountForFT1() + "\n\n"
			
		+ "Q12. " + q12 + "\n"
		
		+ "Q13.	Total number of distance measurements for FT5: "
			+ this.getDistanceMCountForFT5() + "\n\n"
			
		+ "Q14.	List of participant(s) with highest single measurement of steps across trackers (" 
		+ getHighestOverallMType(MeasurementType.STEPS) + "): "	+ "\n" + q14 + "\n"
			
		+ "Q15.	List of participant(s) with highest single measurement of walked distance across trackers ("
		+ getHighestOverallMType(MeasurementType.DISTANCE) + "): "+ "\n" + q15 + "\n"
			
		+ "Q16.	Global average number of steps for FT1: " 
			+ f.format(this.getGlobalAverageStepsForFT1()) + "\n\n"
		
		+ "Q17.	List of participant(s) with average step count over global: " 
			+ "\n" + q17 + "\n"
			
		+ "Q18.	List of participant(s) with average step count below global: " 
			+	 "\n" + q18 + "\n"
		
		+ "Q19.	Global average energy expenditure for FT2 and FT3: " 
			+ f.format(this.getGlobalAverageEEForFT2FT3()) + "\n\n"
			
		+ "Q20.	List of participant(s) with average energy expenditure over global: " 
			+ "\n" + q20 + "\n"
			
		+ "Q21.	List of participant(s) with average energy expenditure below global: " 
			+ "\n" + q21 + "\n"
			
		+ "Q22.	Global average heart rate for whole dataset: " 
			+ f.format(this.getGlobalAverageHR()) + "\n\n"
			
		+ "Q23.	List of participant(s) with average heart rate over global: " 
			+ "\n" + q23 + "\n"
		
		+ "Q24.	List of participant(s) with average heart rate below global: " 
			+ "\n" + q24 + "\n";
		
		return masterQuestions;
	}

	/**
	 * Gets the total number of participants.
	 *
	 * @return the total number of participants
	 */
	@Override
	public int getTotalParticipants() {
		return super.getParticipants().size();
	}

	/**
	 * Gets the number of participants with tracked values 
	 * for the specified measurement type.
	 *
	 * @param type the measurement type
	 * @return the number of participants matching
	 */
	private int getParticipantsNumberWithSpecMType(MeasurementType type) {
		int total = 0;
		for (Participant person : super.getParticipants()) {
			// Adds to total if filtered collection not empty
			if (!Measurement.filterMeasurementsByType(
				person.getAllMeasurements(), type).isEmpty())
					total++;
		}
		return total;
	}
	
	/**
	 * Gets the number of participants with HRMs
	 *
	 * @return the number of participants
	 */
	@Override
	public int getParticipantsNumberWithHRM() {
		return this.getParticipantsNumberWithSpecMType(
				MeasurementType.HEART_RATE);
	}

	/**
	 * Gets the number of participants with step measurements
	 *
	 * @return the number of participants
	 */
	@Override
	public int getParticipantsNumberWithStepsM() {
		return this.getParticipantsNumberWithSpecMType(MeasurementType.STEPS);
	}

	/**
	 * Gets the number of participants with distance measurements
	 *
	 * @return the number of participants
	 */
	@Override
	public int getParticipantsNumberWithDistanceM() {
		return this.getParticipantsNumberWithSpecMType(
				MeasurementType.DISTANCE);
	}

	/**
	 * Gets the number of participants with energy expenditure measurements
	 *
	 * @return the number of participants
	 */
	@Override
	public int getParticipantsNumberWithEEM() {
		return this.getParticipantsNumberWithSpecMType(
				MeasurementType.ENERGY_EXPENDITURE);
	}
	
	/**
	 * Gets the total count of specified measurement type for
	 * the whole dataset
	 *
	 * @param type the measurement type
	 * @return the total number of the type
	 */
	private int getTotalSpecMType(MeasurementType type) {
		int total = 0;
		for (Participant person : super.getParticipants()) {
			Collection<Measurement> numMeas = Measurement.filterMeasurementsByType(
					person.getAllMeasurements(), type);
			
			if (!numMeas.isEmpty())
					total += numMeas.size();
		}
		return total;
	}

	/**
	 * Gets the total HRM count.
	 *
	 * @return the total HRM count
	 */
	@Override
	public int getTotalHRMCount() {
		return this.getTotalSpecMType(MeasurementType.HEART_RATE);
	}

	/**
	 * Gets the total steps count.
	 *
	 * @return the total steps count
	 */
	@Override
	public int getTotalStepsCount() {
		return this.getTotalSpecMType(MeasurementType.STEPS);
	}

	/**
	 * Gets the total distance count.
	 *
	 * @return the total distance count
	 */
	@Override
	public int getTotalDistanceCount() {
		return this.getTotalSpecMType(MeasurementType.DISTANCE);
	}

	/**
	 * Gets the total EE count.
	 *
	 * @return the total EE count
	 */
	@Override
	public int getTotalEECount() {
		return this.getTotalSpecMType(MeasurementType.ENERGY_EXPENDITURE);
	}

	/**
	 * Gets the HRM count per FT.
	 *
	 * @return the HRM count per FT
	 */
	@Override
	public List<Integer> getHRMCountPerFT() {
		List<Integer> hCount = new ArrayList<Integer>();
		ArrayList<Tracker> allTrackers = new ArrayList<Tracker>();
		ArrayList<String> trackerNames = new ArrayList<String>();
		
		for (Participant person : super.getParticipants()) 
			allTrackers.addAll(person.getAllTrackers());
		
		// Sort trackers by their names
		allTrackers.sort(Comparator.comparing(Tracker::getName));
		
		// Creates ArrayList containing each unique name for tracker
		for (Tracker t : allTrackers) {
			if (!trackerNames.contains(t.getName()))
				trackerNames.add(t.getName());
		}
		
		for (String name : trackerNames) {
			Collection<Tracker> reqTrackers = Tracker.filterTrackersByName(allTrackers, name);
			int total = 0;
			
			// Sums the number of measures for each tracker with specified name
			for (Tracker t : reqTrackers)
				total += t.getMeasurementsForType(MeasurementType.HEART_RATE).size();
			
			hCount.add(total);
		}
		return hCount;
	}

	/**
	 * Gets the EEM count for FT1.
	 *
	 * @return the EEM count for FT1
	 */
	@Override
	public int getEEMCountForFT1() {
		ArrayList<Tracker> allTrackers = new ArrayList<>();
		int total = 0;
		
		for (Participant person : super.getParticipants())
			allTrackers.add(person.getTracker("FT1"));
		
		for (Tracker t : allTrackers) {
			total += t.getMeasurementsForType(MeasurementType.ENERGY_EXPENDITURE).size();
		}
		return total;
	}

	/**
	 * Gets the number of step measurements for FT234.
	 *
	 * @return the number of step measurements
	 */
	@Override
	public List<Integer> getStepsMCountForFT234() {
		List<Integer> sCount = new ArrayList<Integer>();
		ArrayList<Tracker> allTrackers = new ArrayList<>();
		final String[] trackerNames = {"FT2", "FT3", "FT4"};
		
		// Add all FT234s to master list
		for (Participant person : super.getParticipants()) {
			allTrackers.add(person.getTracker("FT2"));
			allTrackers.add(person.getTracker("FT3"));
			allTrackers.add(person.getTracker("FT4"));
		}
		
		// Sort trackers by their names
		allTrackers.sort(Comparator.comparing(Tracker::getName));
		
		for (String name : trackerNames) {
			Collection<Tracker> reqTrackers = Tracker.filterTrackersByName(allTrackers, name);
			int total = 0;
			
			// Sums the number of measures for each tracker with specified name
			for (Tracker t : reqTrackers)
				total += t.getMeasurementsForType(MeasurementType.STEPS).size();
			
			sCount.add(total);
		}
		return sCount;
	}

	/**
	 * Gets the number of distance measurements for FT5.
	 *
	 * @return the number of distance measurements
	 */
	@Override
	public int getDistanceMCountForFT5() {
		ArrayList<Tracker> allTrackers = new ArrayList<>();
		int total = 0;
		
		for (Participant person : super.getParticipants())
			allTrackers.add(person.getTracker("FT5"));
		
		for (Tracker t : allTrackers) {
			total += t.getMeasurementsForType(MeasurementType.DISTANCE).size();
		}
		return total;
	}
	
	/**
	 * Sorts a given measurement list.
	 *
	 * @param toSort the list to sort
	 * @return the sorted list
	 */
	public List<Measurement> sortMList(List<Measurement> toSort) {
		// Implement comparison function to sort measurements by their value
		Collections.sort(toSort, new Comparator<Measurement>() {
			@Override
			public int compare(Measurement o1, Measurement o2) {
				return Double.compare(o1.getValue().doubleValue(), 
					o2.getValue().doubleValue());
			}
		});
		
		return toSort;
	}
	
	/**
	 * Gets the highest overall value for a specified measurement type.
	 *
	 * @param type the measurement type
	 * @return the highest overall value
	 */
	private double getHighestOverallMType(MeasurementType type) {
		double largestVal = 0;
		
		for (Participant person : super.getParticipants()) {
			if (getHighestNumberSpecMType(person, type) > largestVal)
				largestVal = getHighestNumberSpecMType(person, type);
		}
		
		return largestVal;
	}
	
	/**
	 * Gets the highest value for a specified participant and measurement type
	 *
	 * @param person the participant
	 * @param type the measurement type
	 * @return the highest value
	 */
	private double getHighestNumberSpecMType(Participant person, MeasurementType type) {
		double largestTrNum = 0;

		for (Tracker tr : person.getAllTrackers()) {
			List<Measurement> allMeasurements = tr.getMeasurementsForType(type);
			
			this.sortMList(allMeasurements);
			if (allMeasurements.isEmpty())
				return 0;
			int curTrNum = allMeasurements.get(allMeasurements.size()-1).getValue().intValue();
			if (curTrNum > largestTrNum)
				largestTrNum = curTrNum;
		}
		
		return largestTrNum;
	}
	
	/**
	 * Gets the participants with the highest number of steps
	 *
	 * @return the list of participants
	 */
	@Override
	public Set<String> getHighestNumberOfStepsParticipants() {		
		double highestNum = getHighestOverallMType(MeasurementType.STEPS);
		Set<String> listHighestP = new HashSet<String>();
		
		for (Participant person : super.getParticipants()) {
			double largestVal = this.getHighestNumberSpecMType(person, MeasurementType.STEPS);
			if (largestVal == highestNum)
				listHighestP.add(person.getName());
		}
		return listHighestP;
	}

	/**
	 * Gets the participants with the highest walked distance
	 *
	 * @return the list of participants
	 */
	@Override
	public Set<String> getHighestWalkedDistanceParticipants() {
		double highestNum = getHighestOverallMType(MeasurementType.DISTANCE);
		Set<String> listHighestP = new HashSet<String>();
		
		for (Participant person : super.getParticipants()) {
			double largestVal = this.getHighestNumberSpecMType(person, MeasurementType.DISTANCE);
			if (largestVal == highestNum)
				listHighestP.add(person.getName());
		}
		return listHighestP;
	}
	
	/**
	 * Gets the global average for specified measurement type and specified trackers
	 *
	 * @param type the measurement type
	 * @param trackers the trackers to get data from
	 * @return the global average
	 */
	private double getGlobalAvgSpecMTypeSpecTrackers(MeasurementType type, String[] trackers) {
		double total = 0;
		int numMeasurements = 0;
		
		for (Participant person : super.getParticipants()) {
			List<Tracker> trackObjs = new ArrayList<Tracker>();
			// convert supplied argument of tracker names to their objects
			for (String tracker : trackers) 
				trackObjs.add(person.getTracker(tracker));
			
			for (Tracker tracker : trackObjs) {
				List<Measurement> allMeasurements = tracker.getMeasurementsForType(
						type);
				
				// cumulative average for steps and energy
				if (type.equals(MeasurementType.STEPS) || type.equals(MeasurementType.ENERGY_EXPENDITURE) ) {
					this.sortMList(allMeasurements);
					total += allMeasurements.get(allMeasurements.size()-1).getValue().doubleValue();
					numMeasurements ++;
					
				// average of all values in the list
				} else {
					for (Measurement value : allMeasurements) {
						total += value.getValue().doubleValue();
						numMeasurements ++;
					}
				}
			}
		}
		return total/numMeasurements;
	}

	/**
	 * Gets the global average step value for FT1
	 *
	 * @return the global average
	 */
	@Override
	public double getGlobalAverageStepsForFT1() {
		String [] trackers = {"FT1"};
		return getGlobalAvgSpecMTypeSpecTrackers(MeasurementType.STEPS, trackers);
	}
	
	/**
	 * Gets the personal average of specified measurement type and trackers
	 *
	 * @param person the participant to get the average for
	 * @param type the measurement type
	 * @param trackers the trackers to get data from, can pass string "All" to use all trackers
	 * @return the personal average
	 */
	private double getPersonalAvgSpecMTypeSpecTrackers(Participant person, MeasurementType type, String[] trackers) {
		double total = 0;
		int numMeasurements = 0;
		List<Tracker> trackObjs = new ArrayList<Tracker>();
		
		// convert supplied argument of tracker names to their objects
		// can supply all to get all trackers
		if (trackers[0].equals("All"))
			trackObjs.addAll(person.getAllTrackers());
		else {
			for (String tracker : trackers) 
				trackObjs.add(person.getTracker(tracker));	
		}		
		
		for (Tracker tracker : trackObjs) {
			List<Measurement> allMeasurements = tracker.getMeasurementsForType(
					type);

			// cumulative average for steps and energy
			if (type.equals(MeasurementType.STEPS) || type.equals(MeasurementType.ENERGY_EXPENDITURE)) {
				this.sortMList(allMeasurements);
				total += allMeasurements.get(allMeasurements.size()-1).getValue().doubleValue();
				numMeasurements ++;
				
			// average of all values in the list
			} else {
				for (Measurement value : allMeasurements) {
					total += value.getValue().doubleValue();
					numMeasurements ++;
				}
			}
		}
		return total/numMeasurements;
	}

	/**
	 * Gets the participants with a personal steps average above the global average
	 *
	 * @return the list of participants
	 */
	@Override
	public List<String> getAvgStepsAboveGlobalParticipantsForFT1() {
		double avgSteps = this.getGlobalAverageStepsForFT1();
		List<String> parAbove = new ArrayList<String>();
		String[] trackers = {"FT1"};
		
		for (Participant person : super.getParticipants()) {
			double partAvg = getPersonalAvgSpecMTypeSpecTrackers(person, MeasurementType.STEPS, trackers);
				
			if (partAvg > avgSteps)
				parAbove.add(person.getName());
		}
		return parAbove;
	}

	/**
	 * Gets the participants with a personal steps average below the global average
	 *
	 * @return the list of participants 
	 */
	@Override
	public List<String> getAvgStepsBelowGlobalParticipantsForFT1() {
		double avgSteps = this.getGlobalAverageStepsForFT1();
		List<String> parBelow = new ArrayList<String>();
		String[] trackers = {"FT1"};
		
		for (Participant person : super.getParticipants()) {
			double partAvg = getPersonalAvgSpecMTypeSpecTrackers(person, MeasurementType.STEPS, trackers);
				
			if (partAvg < avgSteps)
				parBelow.add(person.getName());
		}
		return parBelow;
	}

	/**
	 * Gets the global average EE value for FT23
	 *
	 * @return the global average
	 */
	@Override
	public double getGlobalAverageEEForFT2FT3() {
		String [] trackers = {"FT2", "FT3"};
		return getGlobalAvgSpecMTypeSpecTrackers(MeasurementType.ENERGY_EXPENDITURE, trackers);
	}

	/**
	 * Gets the participants with a personal EE average above the global average
	 *
	 * @return the list of participants 
	 */
	@Override
	public List<String> getAvgEEAboveGlobalParticipantsForFT2FT3() {
		double avgEE = this.getGlobalAverageEEForFT2FT3();
		List<String> parAbove = new ArrayList<String>();
		String[] trackers = {"FT2", "FT3"};
		
		for (Participant person : super.getParticipants()) {
			double partAvg = getPersonalAvgSpecMTypeSpecTrackers(person, MeasurementType.ENERGY_EXPENDITURE, trackers);
				
			if (partAvg > avgEE)
				parAbove.add(person.getName());
		}
		return parAbove;
	}

	/**
	 * Gets the participants with a personal EE average below the global average
	 *
	 * @return the list of participants 
	 */
	@Override
	public List<String> getAvgEEBelowGlobalParticipantsForFT2FT3() {
		double avgEE = this.getGlobalAverageEEForFT2FT3();
		List<String> parBelow = new ArrayList<String>();
		String[] trackers = {"FT2", "FT3"};
		
		for (Participant person : super.getParticipants()) {
			double partAvg = getPersonalAvgSpecMTypeSpecTrackers(person, MeasurementType.ENERGY_EXPENDITURE, trackers);
				
			if (partAvg < avgEE)
				parBelow.add(person.getName());
		}
		return parBelow;
		
	}
	
	/**
	 * Gets the global average HR.
	 *
	 * @return the global average HR
	 */
	@Override
	public double getGlobalAverageHR() {
		double totalAvg = 0;
		double noAvgs = 0;
		
		for (Participant person : super.getParticipants()) {
			for (String tracker : person.getAllTrackerNames()) {
				String[] trackerArr = {tracker};
				double trackerAvg = getPersonalAvgSpecMTypeSpecTrackers(person, MeasurementType.HEART_RATE, trackerArr);
				if (Double.isNaN(trackerAvg))
					continue;
				
				totalAvg += trackerAvg;
				noAvgs ++;
			}
		}
		return totalAvg/noAvgs;
	}

	/**
	 * Gets the participants with a personal HR average above the global average
	 *
	 * @return the list of participants
	 */
	@Override
	public List<String> getAvgHRAboveGlobalParticipants() {
		List<String> parAbove = new ArrayList<String>();
		double globalAvg = this.getGlobalAverageHR();
		
		for (Participant person : super.getParticipants()) {
			String[] trackers = {"All"};
			double personalAvg = getPersonalAvgSpecMTypeSpecTrackers(person, MeasurementType.HEART_RATE, trackers);
			
			if (personalAvg > globalAvg)
				parAbove.add(person.getName());
		}
		return parAbove;
	}

	/**
	 * Gets the participants with a personal HR average below the global average
	 *
	 * @return the list of participants
	 */
	@Override
	public List<String> getAvgHRBelowGlobalParticipants() {
		List<String> parBelow = new ArrayList<String>();
		double globalAvg = this.getGlobalAverageHR();
		
		for (Participant person : super.getParticipants()) {
			String[] trackers = {"All"};
			double personalAvg = getPersonalAvgSpecMTypeSpecTrackers(person, MeasurementType.HEART_RATE, trackers);
			
			if (personalAvg < globalAvg)
				parBelow.add(person.getName());
		}
		return parBelow;
	}
}
