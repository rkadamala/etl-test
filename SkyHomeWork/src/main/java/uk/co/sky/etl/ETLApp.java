package uk.co.sky.etl;

import java.util.List;
import java.util.function.Function;

import uk.co.sky.etl.dto.Lap;
import uk.co.sky.etl.extract.Extractor;
import uk.co.sky.etl.load.Loader;
import uk.co.sky.etl.transform.Transformer;

public class ETLApp {
	public static final String RESULT_OUTPUT_PATH = "/tmp/sorted_result.csv";
	private Function<String, List<Lap>> extract = filePath -> new Extractor(filePath).extract();
	private Function<List<Lap>, List<Lap>> transform = laps -> new Transformer(laps).transform();
	private Function<List<Lap>, List<Lap>> log = laps -> {
		laps.stream().forEach(System.out::println);
		return laps;
	};
	private Function<List<Lap>, Boolean> load = laps -> new Loader(laps).load();
	
	public void run(String filePath) {
		this.extract
			.andThen(this.transform)
				.andThen(this.log)
					.andThen(this.load)
						.apply(filePath);
	}

}
