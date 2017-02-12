package sg.edu.nus.comp.cs3219.control;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sg.edu.nus.comp.cs3219.model.LineStorage;
import sg.edu.nus.comp.cs3219.module.Alphabetizer;
import sg.edu.nus.comp.cs3219.module.CircularShifter;
import sg.edu.nus.comp.cs3219.module.RequiredWordsFilter;

public class MasterControl {
	final private Alphabetizer alphabetizer;
	final private CircularShifter shifter;
	final private RequiredWordsFilter requireFilter;

	private LineStorage rawInputLines;
	private LineStorage resultLines;
	private LineStorage rawResultLines;

	public MasterControl() {
		// Storage
		rawInputLines = new LineStorage();
		rawResultLines = new LineStorage();
		resultLines = new LineStorage();

		// Sub-modules
		shifter = new CircularShifter(resultLines);
		alphabetizer = new Alphabetizer();
		requireFilter = new RequiredWordsFilter(rawResultLines);

	}

	public List<String> run(List<String> input, Set<String> ignoredWords, Set<String> requiredWords) {
		rawInputLines.clearLines();
		resultLines.clearLines();
		rawResultLines.clearLines();
		

		// Set ignore words and required words (make them lowercase for comparison)
		shifter.setIgnoreWords(this.transformSetToLowercase(ignoredWords));
		requireFilter.setRequiredWords(this.transformSetToLowercase(requiredWords));
		
		if (requiredWords.size() == 1) {
			
			// Set up observation
			rawInputLines.addObserver(shifter);
			resultLines.addObserver(alphabetizer);
			
			// Add data line by line
			for (String line : input) {
				rawInputLines.addLine(line);
			}
		
			return resultLines.getAll();
		}
		else {
			// Set up observation
			rawInputLines.addObserver(shifter);
			resultLines.addObserver(requireFilter);
			rawResultLines.addObserver(alphabetizer);
			
			for (String line : input) {
				rawInputLines.addLine(line);
			}
		
			return rawResultLines.getAll();
		}
	}
	
	private Set<String> transformSetToLowercase(Set<String> set) {
		return set.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
	}
}
