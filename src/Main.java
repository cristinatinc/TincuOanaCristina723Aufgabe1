import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Spielort> spielorte = readFromTSV("spielorte.tsv");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("\nWählen Sie eine Option:");
            System.out.println("1. Zeigt die Spiele an, die in einer Stadt mit einer Kapazität gleich oder über einem bestimmten Wert stattfi nden.");
            System.out.println("2. Zeigt auf dem Bildschirm alle Spiele an, die nach dem Datum 2024-06-30 in der Stadt München stattfi nden. ");
            System.out.println("3. Die Anzahl der Spiele pro Stadt berechnet und in einer Datei spielanzahl.txt speichert.");
            System.out.println("0. Beenden");

            userInput = scanner.nextLine();

            switch (userInput){
                case "0" -> {
                    System.out.println("Anwendung wird stoppen...");
                    break;
                }
                case "1" -> {
                    break;
                }
                case "2" -> {
//                    sortAvengersWithWin(avengers);
                    break;
                }
                case "3" -> {
//                    writeResult(outputFile, avengers);
                    break;
                }
                default -> {break;}

            }

        }while(!userInput.equals("0"));

    }

    public static List<Spielort> readFromTSV(String filename) throws IOException {
        List<Spielort> spielorte = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));

        // Skip the header row by starting the loop from index 1
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split("\t");

            int ID = Integer.parseInt(parts[0]);  // Parse the ID as integer
            String team1 = parts[1];
            String team2 = parts[2];
            String tempDate = parts[3];
            LocalDate date = LocalDate.parse(tempDate);
            String spielort = parts[4];
            int kapazitat = Integer.parseInt(parts[5]);

            spielorte.add(new Spielort(ID, team1, team2, date, spielort, kapazitat));
        }
        return spielorte;
    }
}