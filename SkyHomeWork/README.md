ETL Application to extract, transform and load lap times
--------------------------------------------------------
 - Language and features used: Java 8 with functional features such as Stream, Function, Supplier etc
 - Unit testing with Junit 5 jupiter
 - Build tool : Gradle

Application
-----------
Components are segregated based on their responsibility. 
 - Extractor - has logic to extract the data from CSV file and produces list of Lap times with driver name
 - Transformer - expects a List of driver names and corresponding lap times, aggregates the lap times of each drivers and sorts based on the aggregated  lap times. The output is a List of aggregated lap times for each driver
 - Loader - expects a List of driver lap times and writes to a csv file in a given location
 
Running the application and tests with Gradle
---------------------------------------------
 - run command `./gradlew test` for the tests
 - run command `./gradlew run --args=<filepath>` to run the application, the result file will be generated at `/tmp/sorted_result.csv`
 