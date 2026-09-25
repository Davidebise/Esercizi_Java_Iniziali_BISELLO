import static java.lang.IO.println;

void main(){
ex2();
}
void ex1(){
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
    Integer  valore2 = 68;
    ConfrontaBatteria(valore1,valore2);
}
void ex2(){
    int[] codes = {200,404,500,100,700};
    ArrayList<RichiestaHttp> array = new ArrayList<>();
    LinkedList<RichiestaHttp> lista = new LinkedList<>();
    HashSet<String> hash_set = new HashSet<>();
    TreeSet<Long> tree_set = new TreeSet<>();

    for (int i = 0; i < 30; i++) {
        RichiestaHttp http= new RichiestaHttp("172.45.32."+(int)(Math.random()*7 + 1),"https://www.miosito.it/documents",codes[(int)(Math.random()*5)],(int)(Math.random()*500 + 100),System.currentTimeMillis());
        array.add(http);
        lista.addLast(http);
        if (lista.size() > 10) {
            lista.removeFirst();
        }

        if(http.getStatusCode()>=400 && http.getStatusCode()<600){
            hash_set.add(http.getIp());
        }
        tree_set.add(http.getTempoRispostaMs());
        println("Pacchetto "+ (i+1) +" " +  http.toString() + "\n");
    } //la funzione current time restiuisce i ms trascorsi a partire dal 01/01/70

    ArrayList<RichiestaHttp> last_500 = LastN500(array,3);
    println("Ultime N richieste con status >=500");
    for (int i = 0; i < last_500.size(); i++) {
        println("Pacchetto "+ (i+1) +" " +  last_500.get(i).toString() + "\n");
    }

    println("Ultime 10 richieste\n");
    for (int i = 0; i < lista.size(); i++) {
        println(lista.get(i).toString() + "\n");
    }

    println("Ip sospetti:\n");
    for(String ip : hash_set){
        println("- "+ ip+"\n");
    }

    println("Tempo percentile: " + calcolaPercentile90(tree_set)); // 90% dei pacchetti hanno avuto una risposta piu veloce
}
Long calcolaPercentile90(TreeSet<Long> tempi) {
    int indice_target = (int)(0.90 * (tempi.size()-1)); //-1 serve perchè indice parte da 0 e va fino a size - 1

    Long percentile = null;
    int count = 0;
    for (Long tempo : tempi) { //scorro gli elementi finchè non trovo il percentile
        if (count == indice_target) {
            percentile = tempo;
            break;
        }
        count++;
    }

    SortedSet<Long> meno_veloci = tempi.tailSet(percentile, true); //tailSet restituisce un sorted set che è un'interfaccia di treeset

    return percentile;
}
ArrayList<RichiestaHttp> LastN500(ArrayList<RichiestaHttp> array,int n){
    ArrayList<RichiestaHttp> last_500 = new ArrayList<>();
    RichiestaHttp http;
    for (int i = array.size() - 1; i >= 0; i--) {
        http = array.get(i);
        if(http.getStatusCode()>=500){
            last_500.add(http);
            if(last_500.size()==n){
                return last_500;
            }
        }
    }
    return last_500;
}
void ConfrontaBatteria(Integer valore1, Integer valore2){
    println("Confronto valori:"+valore1+" - "+valore2);
    println(valore1.equals(valore2));
    return;
}