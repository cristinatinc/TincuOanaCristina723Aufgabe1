import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

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
                    System.out.println("Geben Sie einen Wert: ");
                    Integer wert = scanner.nextInt();
                    scanner.nextLine();
                    spieleMitKapGrosWert(spielorte, wert);
                    break;
                }
                case "2" -> {
                    spieleNachDatum(spielorte);
                    break;
                }
                case "3" -> {
                    writeResult("spielanzahl.txt", spielorte);
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

    public static void spieleMitKapGrosWert(List<Spielort> spielorte, Integer wert) {
        spielorte.stream()
                .filter(spielort -> spielort.getKapazitat()>= wert)
                .forEach(spielort -> System.out.println(spielort.getTeam1() + " vs " + spielort.getTeam2() + " - Datum " + spielort.getDate() + " -Ort " + spielort.getSpielort()));

    }

    public static void spieleNachDatum(List<Spielort> spielorte) {
        spielorte.stream()
                .sorted(Comparator.comparing(Spielort::getDate))
                .filter(spielort -> spielort.getDate().isAfter(LocalDate.of(2024,06,30)))
                .forEach(spielort -> System.out.println(spielort.getDate() + " " + spielort.getTeam1() + " vs " + spielort.getTeam2()));

    }

    public static void writeResult(String filename, List<Spielort> people) throws IOException {
        HashMap<String, Integer> ortSpiele = new HashMap<>();
        for(Spielort person : people){
            ortSpiele.put(person.getSpielort(), ortSpiele.getOrDefault(person.getSpielort(), 0) + 1);
        }

        List<Map.Entry<String, Integer>> ortList = new ArrayList<>(ortSpiele.entrySet());
        ortList.sort(Comparator.comparing(Map.Entry::getValue));

        ortList = ortList.reversed();

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
        for(Map.Entry<String, Integer> entry : ortList){
            writer.write(entry.getKey() + "%" + entry.getValue());
            writer.newLine();
        }
        writer.close();
        System.out.println("wrote in file " + filename);
    }


}