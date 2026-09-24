import java.util.Optional;

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
    public static Optional<LetturaSensore> parsePacchetto(String dato){
        Double temperatura = 0.0;
        Integer umiditaPercentuale = 0;
        Long timestampUnix = 0L;
        Boolean batteriaScarica = false;

        LetturaSensore sensore= new LetturaSensore(null,umiditaPercentuale,timestampUnix
                ,batteriaScarica);
        return Optional.of(sensore);
    }
}