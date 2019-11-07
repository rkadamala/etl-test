package uk.co.sky.etl.extract;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import uk.co.sky.etl.dto.Lap;

public class Extractor {
	private final String COMMA_DELIM = ",";
	private String filePath;
	
	public Extractor(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Extract the CSV data into a list of Lap objects
	 * @return List<Lap> list of extracted lap information
	 * @throws IOException
	 */
	public List<Lap> extract() {
		try(Stream<String> lapDataStream = Files.lines(Paths.get(filePath))){
			return lapDataStream
					.filter(lapData -> !lapData.trim().isEmpty() 
							&& lapData.trim().split(",").length==2)// only valid data will be processed otherwise ignored
					.map(lapData -> lapData.split(COMMA_DELIM))
					.map(this::mapToLapDTO)
					.collect(toList());
		} catch (IOException e) {
			e.printStackTrace();
			return emptyList();
		}
	}
	/**
	 * Map the extracted line of CSV data into a DTO for future processing easiness
	 * @param lapData string array of driver name and lap time
	 * @return Lap object containing driver name and lap time
	 */
	private Lap mapToLapDTO(String[] lapData) {
		return Lap.builder().driver(lapData[0]).lapTime(Double.parseDouble(lapData[1])).build();
	}
}
