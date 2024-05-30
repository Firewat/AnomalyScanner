# Anomaly Scanner

Anomaly Scanner is a Java-based application designed to read stock closing prices from a CSV file, calculate the Simple Moving Average (SMA), and detect anomalies based on a specified deviation percentage. The results are saved to an output file.

## Features

- Reads closing prices from a CSV file.
- Calculates the Simple Moving Average (SMA) for a given period.
- Detects anomalies where the closing price deviates from the SMA by a specified percentage.
- Outputs the results to a specified file, indicating anomalies.

## Prerequisites

- Java 8 or higher
- A CSV file with stock data where closing prices are in the fifth column.

## Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/AnScan.git
cd AnScan
```

2. Compile the code:

```bash
javac AnomalyScanner.java
```

## Usage

1. Run the application:

```bash
java AnomalyScanner
```

2. Follow the prompts to enter the input file path, output file path, SMA length, and the deviation percentage.

## Example

```
Willkommen beim Anomaly Scanner
Input Path: input.csv
Output: output.csv
SMA-Länge: 5
Gib die SMA-Abweichung in Prozent an (z.B. 10 für 10%): 10
Verarbeite Datei: input.csv
Vorgang abgeschlossen. Ergebnisse in output.csv gespeichert.
```

## CSV File Format

The input CSV file should have the following format (semicolon-separated):

```
Date;Open;High;Low;Close;Volume
2023-01-01;100;105;95;100;10000
2023-01-02;101;106;96;101;15000
...
```

## Output File Format

The output file will contain entries for detected anomalies with the following format (semicolon-separated):

```
Date;Closing Price;Status
2023-01-02;101;ANOMALIE
```

## Code Overview

### Main Class: `AnomalyScanner`

- **Main Method**: Handles user input, reads data, calculates SMA, and detects anomalies.
- **`readClosingPrices` Method**: Reads the closing prices from the input CSV file.
- **`calculateSMA` Method**: Calculates the SMA for the specified period.
- **`detectAnomalies` Method**: Detects and writes anomalies to the output file.

### Exception Handling

- The code handles `IOException` for file operations and `NumberFormatException` for parsing numeric values from the CSV.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README provides a comprehensive overview of the Anomaly Scanner application, including installation, usage instructions, and a summary of the code's functionality.
