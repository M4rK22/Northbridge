package northbridge;

import java.util.StringTokenizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Start{
    public static void main(String[] args){
        
        //setting up datas
        ConsoleInput input=new ConsoleInput();
        Prenotazioni gestore=new Prenotazioni();
        String nomeFile="prenotazioni.csv";
        DateTimeFormatter df=DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int scelta=-1;
        do{
            try{
                //basic menu
                System.out.println("\n--- GESTIONE NORTHBRIDGE ---");
                System.out.println("1. Nuova Prenotazione");
                System.out.println("2. Visualizza Tutte");
                System.out.println("3. Disdici (Rimuovi)");
                System.out.println("4. Check-in");
                System.out.println("5. Statistiche (Incassi/Capienza)");
                System.out.println("6. Ricerca per Data");
                System.out.println("7. Persone per Luogo");
                System.out.println("8. Ordina Drink");
                System.out.println("9. LEGGI CSV");
                System.out.println("10.SALVA CSV");
                System.out.println("0. ESCI");
                System.out.print("Scelta: ");
                scelta=input.readInteger();

                switch(scelta){
                    case 1 -> {
                        System.out.print("Prezzo biglietto: ");
                        Double prz=input.readDouble();
                        System.out.print("ID: ");
                        String id=input.readString();
                        System.out.print("Nominativo: ");
                        String nom=input.readString();
                        System.out.print("Data (gg/mm/aaaa): ");
                        LocalDate d=input.readDate();
                        System.out.print("Luogo(ARENA,PISTA,BAR,VIP): ");
                        Luogo l=input.readLuogo();
                        System.out.print("VIP (true/false): ");
                        Boolean v=input.readBoolean();
                        System.out.print("Nome tavolo: ");
                        String t=input.readString();
                        System.out.print("Tipo(PR,PERSONALE,ANNUNCIO,TAVOLO): ");
                        TipoPrenotazione tp=input.readTipologia();
                        gestore.aggiungiPrenotazione(new Prenotazione(prz,id,nom,d,l,v,t,false,tp));
                    }
                    case 2 -> {
                        for(int i=0;i<gestore.getnPrenotazioni();i++){
                            Prenotazione p=gestore.getPrenotazioni()[i];
                            System.out.println(p.getIdBiglietto()+" | "+p.getNominativo()+" | "+p.getLuogo()+" | "+p.getPrezzo()+"€");
                        }
                    }
                    case 3 -> {
                        System.out.print("ID da rimuovere: ");
                        gestore.disdiciPrenotazione(input.readString());
                    }
                    case 4 -> {
                        System.out.print("ID per Check-in: ");
                        gestore.checkIn(input.readString());
                    }
                    case 5 -> {
                        System.out.println("Incasso totale: "+gestore.totaleIncassi());
                        System.out.println("Posti liberi: "+gestore.postiResidui());
                    }
                    case 6 -> {
                        System.out.print("Inserisci data (gg/mm/aaaa): ");
                        System.out.println("Prenotazioni trovate: "+gestore.ricercaData(input.readDate()));
                    }
                    case 7 -> {
                        System.out.print("Inserisci luogo: ");
                        System.out.println("Persone presenti: "+gestore.contaPerLuogo(input.readLuogo()));
                    }
                    case 8 -> {
                        System.out.print("ID Biglietto per ordine drink: ");
                        String id=input.readString();
                        boolean trovato=false;
                        for(int i=0;i<gestore.getnPrenotazioni();i++){
                            if(gestore.getPrenotazioni()[i].getIdBiglietto().equals(id)){
                                System.out.print("Scegli drink(ACQUA,BEVANDE,ANALCOLICO,COCKTAIL,BIRRA,VINO): ");
                                Drink d=input.readDrink();
                                gestore.getPrenotazioni()[i].aggiungiDrink(d);
                                System.out.println("Drink aggiunto. Nuovo totale: "+gestore.getPrenotazioni()[i].getPrezzo());
                                trovato=true;
                                break;
                            }
                        }
                        if(!trovato)System.out.println("ID non trovato.");
                    }
                    case 9 -> {
                        TextFile fIn=new TextFile(nomeFile,'R');
                        fIn.fromFile(); 
                        try{
                            while(true){
                                String riga=fIn.fromFile();
                                StringTokenizer st=new StringTokenizer(riga,";");
                                
                                String id=st.nextToken();
                                String nom=st.nextToken();
                                
                                LocalDate dat=LocalDate.parse(st.nextToken(),df);
                                Luogo luo=Luogo.valueOf(st.nextToken());
                                Double prz=Double.valueOf(st.nextToken());
                                Boolean v=Boolean.valueOf(st.nextToken());
                                String tav=st.nextToken();
                                Boolean chk=Boolean.valueOf(st.nextToken());
                                TipoPrenotazione tp=TipoPrenotazione.valueOf(st.nextToken());
                                
                                gestore.aggiungiPrenotazione(new Prenotazione(prz,id,nom,dat,luo,v,tav,chk,tp));
                            }
                        }catch(MyException e){
                            System.out.println("Caricamento completato.");
                        }
                        fIn.closeFile();
                    }
                    case 10 -> {
                        TextFile fOut=new TextFile(nomeFile,'W');
                        fOut.toFile("id;nome;data;luogo;prezzo;vip;tavolo;checkin;tipo");
                        for(int i=0;i<gestore.getnPrenotazioni();i++){
                            fOut.toFile(gestore.getPrenotazioni()[i].toString());
                        }
                        fOut.closeFile();
                        System.out.println("File salvato correttamente.");
                    }
                }
            }catch(Exception e){
                MyException me=new MyException(e);
                System.out.println("ERRORE: "+me.getMsg());
            }
        }while(scelta!=0);
    }
}