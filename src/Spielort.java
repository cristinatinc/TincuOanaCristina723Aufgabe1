import java.time.LocalDate;
import java.time.LocalTime;

public class Spielort {
    private int id;
    private String team1;
    private String team2;
    private LocalDate date;
    private String spielort;
    private int kapazitat;

    @Override
    public String toString() {
        return "Spielort{" +
                "id=" + id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", date=" + date +
                ", spielort='" + spielort + '\'' +
                ", kapazitat=" + kapazitat +
                '}';
    }

    public Spielort(int id, String team1, String team2, LocalDate date, String spielort, int kapazitat) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.spielort = spielort;
        this.kapazitat = kapazitat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSpielort() {
        return spielort;
    }

    public void setSpielort(String spielort) {
        this.spielort = spielort;
    }

    public int getKapazitat() {
        return kapazitat;
    }

    public void setKapazitat(int kapazitat) {
        this.kapazitat = kapazitat;
    }
}
