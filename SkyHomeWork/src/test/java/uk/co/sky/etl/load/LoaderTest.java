package uk.co.sky.etl.load;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uk.co.sky.etl.ETLApp;
import uk.co.sky.etl.dto.Lap;
import uk.co.sky.etl.extract.Extractor;
import uk.co.sky.etl.helper.LapsDataSupplier;

public class LoaderTest {
	
	@Test
	@DisplayName("Test result file is created for given data")
	public void shouldLoadLapsData() {
		//Given
		List<Lap> lapsData = LapsDataSupplier.lapsDataWithAlonzoAtTop.get();
		Loader loader = new Loader(lapsData);
		
		//When 
		boolean fileCreated = loader.load();
		
		//Then
		assertTrue(fileCreated);
	}
	
	@Test
	@DisplayName("Test result file is created for given data")
	public void shouldLoadDataForGivenLapsData() {
		//Given
		List<Lap> lapsData = LapsDataSupplier.lapsDataTwoLapsForHamilton.get();
		Loader loader = new Loader(lapsData);
		
		//When 
		boolean fileCreated = loader.load();
		
		//Then
		assertTrue(fileCreated);
		
		//read the result file
		Extractor extractor = new Extractor(ETLApp.RESULT_OUTPUT_PATH);
		List<Lap> lapsResultData = extractor.extract();
		assertNotNull(lapsResultData);
		assertEquals(2, lapsResultData.size());
		assertAll("loaded data",
				() -> assertEquals("Hamilton", lapsResultData.get(0).getDriver()),
				() -> assertEquals(4.43, lapsResultData.get(0).getLapTime()),
				() -> assertEquals("Hamilton", lapsResultData.get(1).getDriver()),
				() -> assertEquals(4.65, lapsResultData.get(1).getLapTime())
		);
	}
	
	@Test
	@DisplayName("Test should throw IllegalArgumentException when laps data is empty")
	public void shouldThrowErrorForEmptyLapsData() {
		//Given
		List<Lap> lapsData = Collections.emptyList();
		
		//Then
		assertThrows(IllegalArgumentException.class, () -> new Loader(lapsData));
	}
	
	@Test
	@DisplayName("Test should throw IllegalArgumentException when laps data is null")
	public void shouldThrowErrorForNullLapsData() {
		//Given
		List<Lap> lapsData = null;
		
		//Then
		assertThrows(IllegalArgumentException.class, () -> new Loader(lapsData));
	}

}
