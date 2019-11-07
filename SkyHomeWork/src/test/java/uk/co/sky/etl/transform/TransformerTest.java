package uk.co.sky.etl.transform;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uk.co.sky.etl.dto.Lap;
import uk.co.sky.etl.helper.LapsDataSupplier;

public class TransformerTest {
	
	@Test
	@DisplayName("Test laps data being transformed")
	public void shouldTransformLapsData() {
		//Given
		List<Lap> lapsData = LapsDataSupplier.lapsDataWithAlonzoAtTop.get();
		Transformer transformer = new Transformer(lapsData);
		
		//When 
		List<Lap> transformedData = transformer.transform();
		
		//Then
		assertNotNull(transformedData);
		assertEquals(3, transformedData.size());
		assertAll("transformed data",
				() -> assertEquals("Alonzo", transformedData.get(0).getDriver()),
				() -> assertEquals(4.526666666666666, transformedData.get(0).getLapTime()),
				() -> assertEquals("Hamilton", transformedData.get(1).getDriver()),
				() -> assertEquals(4.5633333333333335, transformedData.get(1).getLapTime()),
				() -> assertEquals("Verstrappen", transformedData.get(2).getDriver()),
				() -> assertEquals(4.63, transformedData.get(2).getLapTime())
		);
	}
	
	@Test
	@DisplayName("Test should throw IllegalArgumentException when laps data is empty")
	public void shouldThrowErrorForEmptyLapsData() {
		//Given
		List<Lap> lapsData = Collections.emptyList();
		
		//Then
		assertThrows(IllegalArgumentException.class, () -> new Transformer(lapsData));
	}
	
	@Test
	@DisplayName("Test should throw IllegalArgumentException when laps data is null")
	public void shouldThrowErrorForNullLapsData() {
		//Given
		List<Lap> lapsData = null;
		
		//Then
		assertThrows(IllegalArgumentException.class, () -> new Transformer(lapsData));
	}
	
	@Test
	@DisplayName("Test should sort ascending for a given laps data")
	public void shouldSortAscendingLapsData() {
		//Given
		List<Lap> lapsData = LapsDataSupplier.lapsDataWithAscendingOrder.get();
		Transformer transformer = new Transformer(lapsData);
		
		//When 
		List<Lap> transformedData = transformer.transform();
		
		//Then
		assertNotNull(transformedData);
		assertEquals(3, transformedData.size());
		assertAll("transformed data",
				() -> assertEquals("Alonzo", transformedData.get(0).getDriver()),
				() -> assertEquals(4.32, transformedData.get(0).getLapTime()),
				() -> assertEquals("Hamilton", transformedData.get(1).getDriver()),
				() -> assertEquals(4.65, transformedData.get(1).getLapTime()),
				() -> assertEquals("Verstrappen", transformedData.get(2).getDriver()),
				() -> assertEquals(4.75, transformedData.get(2).getLapTime())
		);
		
	}
	
	@Test
	@DisplayName("Test should average given laps data")
	public void shouldAverageLapsData() {
		//Given
		List<Lap> lapsData = LapsDataSupplier.lapsDataTwoLapsForHamilton.get();
		Transformer transformer = new Transformer(lapsData);
		
		//When 
		List<Lap> transformedData = transformer.transform();
		
		//Then
		assertNotNull(transformedData);
		assertEquals(1, transformedData.size());
		assertAll("transformed data",
				() -> assertEquals("Hamilton", transformedData.get(0).getDriver()),
				() -> assertEquals(4.54, transformedData.get(0).getLapTime())
		);
		
	}

}
