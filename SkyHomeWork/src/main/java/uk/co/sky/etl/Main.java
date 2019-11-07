package uk.co.sky.etl;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		if(args.length == 0) {
			System.out.println("Please provide a valid CSV file path!");
			System.exit(0);
		}
		
		if(args.length > 1)
			System.out.println("Taking the first argument as CSV file path");
		
		String filePath = args[0].trim();
		
		if(filePath.isEmpty() || !filePath.endsWith(".csv")) {
			System.out.println("Please provide a valid CSV file path!");
			System.exit(0);
		}
		
		new ETLApp().run(filePath);
	}

}
