package sg.edu.nus.comp.cs3219.control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MasterControlTest {

	MasterControl master;
	
	@Before
	public void setUp() {
		master = new MasterControl();
	}

	@Test
	public void testExample1() {
		Set<String> requireWords = new HashSet<>();
		requireWords.add("");
		//requiredWords.size() must be 1, but it is not the case when it is created from a HashSet
		
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");

		List<String> input = new ArrayList<>();
		input.add("The Day after Tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, requireWords);

		assertEquals(6, result.size());
		assertEquals("Day after Tomorrow the", result.get(0));
		assertEquals("Fast and Furious", result.get(1));
		assertEquals("Furious Fast and", result.get(2));
		assertEquals("Man of Steel", result.get(3));
		assertEquals("Steel Man of", result.get(4));
		assertEquals("Tomorrow the Day after", result.get(5));
	}
	
	@Test
	public void testExample2() {
		
		Set<String> requireWords = new HashSet<>();
		requireWords.add("day");
		requireWords.add("fast");
		requireWords.add("furious");
		requireWords.add("man");
		requireWords.add("steel");
		requireWords.add("tomorrow");
		
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");

		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");

		List<String> result = master.run(input, ignoreWords, requireWords);

		assertEquals(6, result.size());
		assertEquals("Day after tomorrow the", result.get(0));
		assertEquals("Fast and furious", result.get(1));
		assertEquals("Furious fast and", result.get(2));
		assertEquals("Man of steel", result.get(3));
		assertEquals("Steel man of", result.get(4));
		assertEquals("Tomorrow the day after", result.get(5));
	}
	
	
	@Test
	public void testExample3() {
		
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		
		Set<String> requireWords = new HashSet<>();
		requireWords.add("fast");
		requireWords.add("man");
		

		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");
		
		List<String> result = master.run(input, ignoreWords, requireWords);

		assertEquals(2, result.size());
		assertEquals("Fast and Furious", result.get(0));
		assertEquals("Man of Steel", result.get(1));
	}
	
	@Test
	public void testExample4() {
		
		Set<String> ignoreWords = new HashSet<>();
		
		Set<String> requireWords = new HashSet<>();
		requireWords.add("fast");
		requireWords.add("man");
		

		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");
		
		List<String> result = master.run(input, ignoreWords, requireWords);

		assertEquals(2, result.size());
		assertEquals("Fast and Furious", result.get(0));
		assertEquals("Man of Steel", result.get(1));
	}
	
	@Test
	public void testExample5() {
		
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		ignoreWords.add("fast");
		
		
		Set<String> requireWords = new HashSet<>();
		requireWords.add("fast");
		requireWords.add("man");
		

		List<String> input = new ArrayList<>();
		input.add("The day after tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");
		
		List<String> result = master.run(input, ignoreWords, requireWords);

		assertEquals(1, result.size());
		assertEquals("Man of Steel", result.get(0));
	}
}

