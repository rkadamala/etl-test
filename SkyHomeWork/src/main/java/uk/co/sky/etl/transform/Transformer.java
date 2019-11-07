package uk.co.sky.etl.transform;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import uk.co.sky.etl.dto.Lap;

public class Transformer {
	
	private List<Lap> laps;
	
	/**
	 * Function maps the map entry to Lap DTO
	 */
	private Function<Map<String, Double>, List<Lap>> mapToLapDTO = averages -> 
			averages.entrySet().stream()
				.map(mapEntry -> Lap.builder().driver(mapEntry.getKey()).lapTime(mapEntry.getValue()).build())
				.collect(toList());
	
	/**
	 * Function finds the average of the laptimes for each driver 
	 * and aggregates into a map with key as driver and value as average laptime
	 */
	private Function<List<Lap>, Map<String, Double>> averageLapTime = laps -> {
		return laps
				.stream()
				.collect(groupingBy(Lap::getDriver, averagingDouble(Lap::getLapTime)));
	};
	
	/**
	 * Function sorts the given list of Lap DTOs ascending by comparing laptime
	 */
	private Function<List<Lap>, List<Lap>> sortAscending = averages -> {
		return averages.stream()
				.sorted(comparing(Lap::getLapTime, (time1, time2) -> time1.compareTo(time2)))
				.collect(toList());
	};
	
	public Transformer(List<Lap> laps) {
		if(laps == null || laps.isEmpty())
			throw new IllegalArgumentException("Non-Null/Non Empty List of Lap time is required");
		
		this.laps = laps;
	}
	
	/**
	 * Transform the given list of lap times by averaging and sorting ascending order
	 * @return List<Lap> list of averaged and sorted laptimes
	 */
	public List<Lap> transform() {
			
		return averageLapTime
				.andThen(mapToLapDTO)
				.andThen(sortAscending)
				.apply(laps);
	}
	
}
