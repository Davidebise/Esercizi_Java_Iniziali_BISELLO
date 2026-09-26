import java.io.Serializable;

public class Ticket implements Comparable<Ticket> {
    private String id;
    private String descrizione;
    private String livello;
    private Long timestampArrivo;

    public Ticket(String id, String descrizione, String livello, Long timestampArrivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.livello = livello;
        this.timestampArrivo = timestampArrivo;
    }

    public String getId() {
        return id;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public String getLivello() {
        return livello;
    }
    public Long getTimestampArrivo() {
        return timestampArrivo;
    }

    public int PriorityToTime(){
        switch(this.livello) {
            case "CRITICO":
                return 15;
            case "ALTO":
                return 30;
            case "MEDIO":
                return 60;
            case "BASSO":
                return 120;
        }
        return 0; //controlli effettuati gia prima
    }
    @Override
    public int compareTo(Ticket other) {
        int compLivello = Integer.compare(this.PriorityToTime(), other.PriorityToTime());
        if (compLivello != 0) {
            return compLivello;
        }
        return Long.compare(this.timestampArrivo, other.timestampArrivo); //confronto timestamp se sono uguali
    }

    @Override
    public String toString() {
        return "Id:" + id + " - " + "Descrizione:" + descrizione + " - " + "Livello:" + livello + " - " +
                "Timestamp arrivo:" + timestampArrivo;
    }
}
