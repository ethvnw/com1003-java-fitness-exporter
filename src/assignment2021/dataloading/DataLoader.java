package assignment2021.dataloading;

import java.util.List;

import assignment2021.codeprovided.dataloading.AbstractDataLoader;
import assignment2021.codeprovided.dataloading.DataParsingException;
import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementFactory;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;

/**
 * DataLoader.java
 * 
 * @author Ethan Watts
 */

public class DataLoader extends AbstractDataLoader {

	/**
	 * Parses the provided lines to create a Participant. This is called by
	 * loadDataFile, where the provided lines have been read directly from the file.
	 * 
	 * @param lines the lines to parse.
	 * @return the parsed Participant.
	 * @throws DataParsingException if a parsing error occurred, e.g. if the data is
	 *                              not in the correct structure.
	 */
	@Override
	public Participant loadDataLines(List<String> lines) throws DataParsingException {
		Participant p = setupParticipant(lines.get(0));
		lines.remove(0);

		MeasurementType curMeasureType = null;
		String[] trackers = null;

		for (String row: lines) {
			// Checks every line to see if it is a measurement type
			if (MeasurementType.fromMeasurementName(row) != null) {
				curMeasureType = MeasurementType.fromMeasurementName(row);
				continue;
			}
			
			if (row.equals(""))
				throw new DataParsingException("Blank line in participant file");

			String[] splitLine = row.split(CELL_SEPARATOR);

			// Sets the trackers for current measurement type
			if (splitLine[0].equals("Count")) {
				trackers = splitLine;
				continue;
			}

			// Adding each measurement on the line to its corresponding tracker
			try {
				for (int curTracker=1; curTracker<trackers.length; curTracker++) {
					p.addMeasurementToTracker(
							trackers[curTracker], 
							MeasurementFactory.createMeasurement (
									curMeasureType, 
									Integer.valueOf(splitLine[0]), 
									splitLine[curTracker]
									)
							);
				}
			} catch (NullPointerException e) {
				throw new DataParsingException("Invalid format for participant on \"" + row + "\"");
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new DataParsingException("Tracker mising a value on \"" + row + "\"");
			}
		}
		return p;
	}

	private Participant setupParticipant(String details) throws DataParsingException {
		Participant p = null;
		String[] partDetails = details.split(",");

		if (partDetails.length != 3)
			throw new DataParsingException(
				"Participant doesn't have valid name/age/gender");

		p = new Participant(
				partDetails[0], 
				Integer.valueOf(partDetails[1]),
				partDetails[2]);
		
		return p;
	}
}
