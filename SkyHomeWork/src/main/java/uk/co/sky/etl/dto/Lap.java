package uk.co.sky.etl.dto;

import java.text.DecimalFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Lap {
	@NonNull
	private String driver;
	private double lapTime;
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.###");
		return String.join(",", this.driver, df.format(this.lapTime));
	}
}
