package AnScan;
import java.io.*;
import java.util.*;

public class AnomalyScanner {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Willkommen beim Anomaly Scanner");

            // Eingabe von Dateipfaden und Parametern
            System.out.print("Input Path: ");
            String inputFile = scanner.nextLine();
            System.out.print("Output: ");
            String outputFile = scanner.nextLine();
            System.out.print("SMA-Länge: ");
            int smaLength = scanner.nextInt();
            System.out.print("Gib die SMA-Abweichung in Prozent an (z.B. 10 für 10%): ");
            double deviationPercent = scanner.nextDouble();

            System.out.println("Verarbeite Datei: " + inputFile);

            // Daten aus der CSV-Datei lesen und Anomalien erkennen
            List<Double> closingPrices = readClosingPrices(inputFile);
            double sma = calculateSMA(closingPrices, smaLength);
            detectAnomalies(inputFile, outputFile, sma, deviationPercent);
            scanner.close();


            System.out.println("Vorgang abgeschlossen. Ergebnisse in " + outputFile + " gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
            
        }
    }

    // Methode zum Lesen der Schlusskurse aus der CSV-Datei
    public static List<Double> readClosingPrices(String inputFile) throws IOException {
        List<Double> closingPrices = new ArrayList<>();
        Scanner fileScanner = new Scanner(new File(inputFile));
        fileScanner.nextLine(); // Überspringen der Kopfzeile
        while (fileScanner.hasNextLine()) {
            String[] values = fileScanner.nextLine().split(";");
            try {
                double closingPrice = Double.parseDouble(values[4].replace(",", "."));
                closingPrices.add(closingPrice);
            } catch (NumberFormatException ignored) {
                // Ignorieren von ungültigen Daten
            }
        }
        fileScanner.close();
        return closingPrices;
    }

    // Methode zur Berechnung des SMA
    private static double calculateSMA(List<Double> closingPrices, int period) {
        double sum = 0;
        for (int i = 0; i < period; i++) {
            sum += closingPrices.get(i);
        }
        return sum / period;
    }

    // Methode zur Erkennung und Speicherung von Anomalien
    private static void detectAnomalies(String inputFile, String outputFile, double sma, double deviationPercent) throws IOException {
        PrintWriter writer = new PrintWriter(outputFile);
        Scanner fileScanner = new Scanner(new File(inputFile));
        fileScanner.nextLine(); // Überspringen der Kopfzeile
        while (fileScanner.hasNextLine()) {
            String[] values = fileScanner.nextLine().split(";");
            try {
                double closingPrice = Double.parseDouble(values[4].replace(",", "."));
                boolean isAnomaly = Math.abs(closingPrice - sma) > deviationPercent / 100 * sma;
                if (isAnomaly) {
                    writer.println(values[0] + ";" + closingPrice + "; ANOMALIE");
                }
            } catch (NumberFormatException ignored) {
                // Ignorieren von ungültigen Daten
            }
        }
        fileScanner.close();
        writer.close();
    }
}
