package uk.co.sky.etl.load;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import uk.co.sky.etl.ETLApp;
import uk.co.sky.etl.dto.Lap;

public class Loader {
	private List<Lap> laps;
	
	public Loader(List<Lap> laps) {
		if(laps == null || laps.isEmpty() )
			throw new IllegalArgumentException("laps data and file path is required");
		this.laps = laps;
	}
	
	/**
	 * Write the given data into a file in CSV format
	 * @return true if write is success else exception
	 * @throws IOException
	 */
	public boolean load(){
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(ETLApp.RESULT_OUTPUT_PATH))){
			laps.stream()
				.map(Lap::toString)
				.forEach(driverLap -> {
					try {
						writer.append(driverLap); 
						writer.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
