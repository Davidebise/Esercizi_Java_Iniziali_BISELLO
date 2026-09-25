import java.util.Optional;

import static java.lang.IO.println;

class LetturaSensore{
    private Double temperatura;
    private Integer umiditaPercentuale;
    private Long timestampUnix;
    private Boolean batteriaScarica;

    public LetturaSensore(){
        temperatura = 0.0;
        umiditaPercentuale = 0;
        timestampUnix = 0L;
        batteriaScarica = false;
    }
    public LetturaSensore(Double temperatura, Integer umiditaPercentuale, Long timestampUnix,Boolean batteriaScarica){
        this.temperatura = temperatura;
        this.umiditaPercentuale = umiditaPercentuale;
        this.timestampUnix = timestampUnix;
        this.batteriaScarica = batteriaScarica;
    }
    public Double getTemperatura() {
        return temperatura;
    }
    public Integer getUmiditaPercentuale() {
        return umiditaPercentuale;
    }
    public Long getTimestampUnix() {
        return timestampUnix;
    }
    public Boolean getBatteriaScarica() {
        return batteriaScarica;
    }
    public static Optional<LetturaSensore> parsePacchetto(String dato){
        Double temperatura;
        Integer umiditaPercentuale;
        Long timestampUnix;
        Boolean batteriaScarica;
        try {
            temperatura = Double.parseDouble(dato.split(";")[0].split("=")[1]);
        }
        catch(NumberFormatException ex){
            println("campo temperatura corrotto!");
            temperatura = null;
        }
        catch(IndexOutOfBoundsException ex){
            println("campo temperatura vuoto!");
            temperatura = null;
        }
        try {
            umiditaPercentuale = Integer.parseInt(dato.split(";")[1].split("=")[1]);
            if(umiditaPercentuale < 0 || umiditaPercentuale > 100){
                throw new IllegalArgumentException();
            }
        }
        catch(NumberFormatException ex){
            println("campo umidita corrotto!");
            umiditaPercentuale = null;
        }
        catch(IndexOutOfBoundsException ex){
            println("campo umidita vuoto!");
            umiditaPercentuale = null;
        }
        catch(IllegalArgumentException ex){
            println("campo umidita fuori range!");
            umiditaPercentuale = null;
        }
        try {
            timestampUnix = Long.parseLong(dato.split(";")[2].split("=")[1]);
        }
        catch(NumberFormatException ex){
            println("campo timestamp corrotto!");
            timestampUnix = null;
        }
        catch(IndexOutOfBoundsException ex){
            println("campo timestamp vuoto!");
            timestampUnix = null;
        }
        try {
            batteriaScarica = Boolean.parseBoolean(dato.split(";")[3].split("=")[1]);
        }
        catch(NumberFormatException ex){
            println("campo batteria corrotto!");
            batteriaScarica = null;
        }
        catch(IndexOutOfBoundsException ex){
            println("campo batteria vuoto!");
            batteriaScarica = null;
        }
        LetturaSensore sensore= new LetturaSensore(temperatura,umiditaPercentuale,timestampUnix
                ,batteriaScarica);
        return Optional.of(sensore);  //necessario se si vuole restituire l'optional
    }
    @Override
    public String toString(){
        return "Temperatura:" + temperatura + "Umidita:" + umiditaPercentuale + "timestamp:" + timestampUnix + "batteria:" + batteriaScarica;
    }
}