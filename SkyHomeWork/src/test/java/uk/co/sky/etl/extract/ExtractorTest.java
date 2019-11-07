package uk.co.sky.etl.extract;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uk.co.sky.etl.dto.Lap;

public class ExtractorTest {
	
	String testDataFilePath;
	
	@BeforeEach
	public void init() {
		testDataFilePath = ExtractorTest.class.getClassLoader().getResource("lap-data.csv").getPath();
	}
	
	@Test
	@DisplayName("Test extracting CSV data from test file")
	public void shouldExtractCSV() {
		//Given
		Extractor extractor = new Extractor(testDataFilePath);
		
		//When 
		List<Lap> extractedData = extractor.extract();
		
		//Then
		assertNotNull(extractedData);
		assertEquals(9, extractedData.size());
		assertAll("extracted data",
				() -> assertEquals("Alonzo", extractedData.get(0).getDriver()),
				() -> assertEquals(4.32, extractedData.get(0).getLapTime()),
				() -> assertEquals("Hamilton", extractedData.get(6).getDriver()),
				() -> assertEquals(4.61, extractedData.get(6).getLapTime())
		);
	}
	
	@Test
	@DisplayName("Test extracting CSV data from test file having wrong entries")
	public void shouldIgnoreInvalidEntriesFromCSV() {
		//Given
		String invalidDataFile = ExtractorTest.class.getClassLoader().getResource("lap-data-with-incorrect-entrie.csv").getPath();
		Extractor extractor = new Extractor(invalidDataFile);
		
		//When 
		List<Lap> extractedData = extractor.extract();
		
		//Then
		assertNotNull(extractedData);
		assertEquals(6, extractedData.size());
		assertAll("extracted data",
				() -> assertEquals("Alonzo", extractedData.get(0).getDriver()),
				() -> assertEquals(4.32, extractedData.get(0).getLapTime()),
				() -> assertEquals("Verstrappen", extractedData.get(5).getDriver()),
				() -> assertEquals(4.59, extractedData.get(5).getLapTime())
		);
	}
	
	@Test
	@DisplayName("Test extracting CSV data from non existing test file")
	public void shouldReturnEmptyListForWrongFilePath() {
		//Given
		Extractor extractor = new Extractor("/my/test/path");
		
		//When 
		List<Lap> extractedData = extractor.extract();
		
		//Then
		assertNotNull(extractedData);
		assertEquals(0, extractedData.size());
	}

}
