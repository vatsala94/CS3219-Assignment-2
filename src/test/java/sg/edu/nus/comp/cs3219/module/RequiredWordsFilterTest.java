package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class RequiredWordsFilterTest {
	LineStorage inputLineStorage;
	LineStorage afterFilterLineStorage;
	RequiredWordsFilter requireFilter;
	
	@Before
	public void setUp() {
		inputLineStorage = new LineStorage();
		afterFilterLineStorage = new LineStorage();
		requireFilter = new RequiredWordsFilter(afterFilterLineStorage);
		Set<String> words = new HashSet<>();
		words.add("man");
		words.add("steel");
		requireFilter.setRequiredWords(words);
		inputLineStorage.addObserver(requireFilter);
	}

	@Test
	public void test() {
		inputLineStorage.addLine("Man of Steel");
		inputLineStorage.addLine("of Steel Man");
		inputLineStorage.addLine("Steel Man of");
		
		assertEquals(2, afterFilterLineStorage.size());

		assertEquals("man of steel", afterFilterLineStorage.get(0).toString());
		assertEquals("steel man of", afterFilterLineStorage.get(1).toString());
	}
}
