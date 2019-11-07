package uk.co.sky.etl.helper;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import uk.co.sky.etl.dto.Lap;

public interface LapsDataSupplier {
	static final Supplier<Lap> alonzo432 = () -> Lap.builder().driver("Alonzo").lapTime(4.32).build();
	static final Supplier<Lap> alonzo438 = () -> Lap.builder().driver("Alonzo").lapTime(4.38).build();
	static final Supplier<Lap> alonzo488 = () -> Lap.builder().driver("Alonzo").lapTime(4.88).build();
	static final Supplier<Lap> verstrappen475 = () -> Lap.builder().driver("Verstrappen").lapTime(4.75).build();
	static final Supplier<Lap> verstrappen455 = () -> Lap.builder().driver("Verstrappen").lapTime(4.55).build();
	static final Supplier<Lap> verstrappen459 = () -> Lap.builder().driver("Verstrappen").lapTime(4.59).build();
	static final Supplier<Lap> hamilton465 = () -> Lap.builder().driver("Hamilton").lapTime(4.65).build();
	static final Supplier<Lap> hamilton461 = () -> Lap.builder().driver("Hamilton").lapTime(4.61).build();
	static final Supplier<Lap> hamilton443 = () -> Lap.builder().driver("Hamilton").lapTime(4.43).build();
	
	static final Supplier<List<Lap>> lapsDataWithAlonzoAtTop = () -> {
		return Arrays.asList(alonzo432.get(), alonzo438.get(), alonzo488.get(),
				verstrappen475.get(), verstrappen455.get(), verstrappen459.get(),
				hamilton465.get(), hamilton461.get(), hamilton443.get());
	};
	
	static final Supplier<List<Lap>> lapsDataWithAscendingOrder = () -> {
		return Arrays.asList(alonzo432.get(), verstrappen475.get(), hamilton465.get());
	};
	
	static final Supplier<List<Lap>> lapsDataTwoLapsForHamilton = () -> {
		return Arrays.asList(hamilton443.get(), hamilton465.get());
	};

}
