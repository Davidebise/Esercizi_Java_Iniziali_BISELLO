import static java.lang.IO.println;

void main(){
    println("Lettura 1:");
    Optional<LetturaSensore> sensore = LetturaSensore.parsePacchetto("temp=23.5;umid=61;ts=1732000000;batt_low=false");
    println("\n"+sensore.toString()+"\n\n");
    println("Lettura 2:");
    sensore=null;
    sensore = LetturaSensore.parsePacchetto("temp=xx.xx;umid=14;ts=2742455610;batt_low=true");
    println("\n"+sensore.toString()+"\n\n");
    println("Lettura 3:");
    sensore=null;
    sensore = LetturaSensore.parsePacchetto("temp=14.6;umid=ciao;ts=6936001200;batt_low=false");
    println("\n"+sensore.toString()+"\n\n");
    println("Lettura 4:");
    sensore=null;
    sensore = LetturaSensore.parsePacchetto("temp=;umid=150;ts=gg;batt_low=false");
    println("\n"+sensore.toString()+"\n\n");
    println("Lettura 5:");
    sensore=null;
    sensore = LetturaSensore.parsePacchetto("temp=30.3;umid=34;ts=1732000000;batt_low=");
    println("\n"+sensore.toString()+"\n\n");
    println("Lettura 6:");
    sensore=null;
    sensore = LetturaSensore.parsePacchetto("temp=27.8;umid=76;ts=1732000000;batt_low=true");
    println("\n"+sensore.toString()+"\n\n");
    println("Lettura 7:");
    sensore=null;
    sensore = LetturaSensore.parsePacchetto("temp=12.9;umid=;ts=1732000000;batt_low=false");
    println("\n"+sensore.toString()+"\n\n");
    println("Lettura 8:");
    sensore=null;
    sensore = LetturaSensore.parsePacchetto("temp=21.2;umid=45;ts=nooo;batt_low=false");
    println("\n"+sensore.toString()+"\n\n");
    Integer valore1 = 62;
    Integer  valore2 = 67;
    ConfrontaBatteria(valore1,valore2);
}
void ConfrontaBatteria(Integer valore1, Integer valore2){
    println("Confronto valori:"+valore1+" - "+valore2);
    println(valore1.equals(valore2));
    return;
}