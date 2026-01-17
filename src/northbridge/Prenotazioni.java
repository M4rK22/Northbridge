package northbridge;

import java.time.LocalDate;


public class Prenotazioni{
    private Prenotazione prenotazioni[];
    //dynamic array dim
    private Integer nPrenotazioni;
    //max array dim
    private final Integer maxPrenotazioni=500;

    public Prenotazioni(){
        this.nPrenotazioni=0;
        this.prenotazioni=new Prenotazione[this.maxPrenotazioni];
    }

    public void aggiungiPrenotazione(Prenotazione a){
        if(this.nPrenotazioni < this.maxPrenotazioni){
            this.prenotazioni[this.nPrenotazioni]=a;
            this.nPrenotazioni++;
        }
    }

    public void disdiciPrenotazione(String id){
        for(int i=0; i<this.nPrenotazioni; i++){
            if(prenotazioni[i]!=null && prenotazioni[i].getIdBiglietto().equals(id)){
                //compact the array by scaling the elements
                for(int j=i; j<this.nPrenotazioni-1;j++){
                    prenotazioni[j]=prenotazioni[j+1];
                }
                prenotazioni[this.nPrenotazioni-1] = null;
                this.nPrenotazioni--;
                break;
            }
        }
    }

    public Integer ricercaData(LocalDate data){
        Integer cont=0;
        for(int i=0; i<this.nPrenotazioni; i++){
            if(this.prenotazioni[i]!=null && this.prenotazioni[i].getData().equals(data)){
                cont++;
            }
        }
        return cont;
    }

    public Double totaleIncassi(){
        Double tot=0.0;
        for(int i=0; i<this.nPrenotazioni; i++){
            if(this.prenotazioni[i]!=null){
                tot= tot+this.prenotazioni[i].getPrezzo();
            }
        }
        return tot;
    }

    public Integer postiResidui(){
        return this.maxPrenotazioni-this.nPrenotazioni;
    }

    public void checkIn(String id){
        for(int i=0; i<this.nPrenotazioni; i++){
            if(this.prenotazioni[i]!=null && this.prenotazioni[i].getIdBiglietto().equals(id)){
                this.prenotazioni[i].setCheckIn(true);
                break;
            }
        }
    }

    public Integer contaPerLuogo(Luogo l){
        Integer cont=0;
        for(int i=0; i<this.nPrenotazioni; i++){
            if(this.prenotazioni[i]!=null && this.prenotazioni[i].getLuogo().equals(l)){
                cont++;
            }
        }
        return cont;
    }

    public Integer getnPrenotazioni(){
        return nPrenotazioni;
    }

    public Prenotazione[] getPrenotazioni(){
        return prenotazioni;
    }
}