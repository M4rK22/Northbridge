package northbridge;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Prenotazione implements Serializable{
    private String idBiglietto;
    private String nominativo;
    private LocalDate data;
    private Luogo luogo;
    private Double prezzo;
    private Boolean vip;
    private String nomeTavolo;
    private Boolean checkIn;
    private TipoPrenotazione tipo;

    public Prenotazione(Double prezzo,String idBiglietto,String nominativo,LocalDate data,Luogo luogo,Boolean vip,String nomeTavolo,Boolean checkIn,TipoPrenotazione tipo){
        setIdBiglietto(idBiglietto);
        setNominativo(nominativo);
        setData(data);
        setLuogo(luogo);
        setPrezzo(prezzo);
        setVip(vip);
        setTipo(tipo);
        setNomeTavolo(nomeTavolo);
        this.checkIn=checkIn;
    }

    public String getIdBiglietto(){
        return idBiglietto;
    }

    public void setIdBiglietto(String idBiglietto){
        this.idBiglietto=idBiglietto;
    }

    public String getNominativo(){
        return nominativo;
    }

    public void setNominativo(String nominativo){
        this.nominativo=nominativo;
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        this.data=data;
    }

    public Luogo getLuogo(){
        return luogo;
    }

    public void setLuogo(Luogo luogo){
        this.luogo=luogo;
    }

    public Boolean getVip(){
        return vip;
    }

    public void setVip(Boolean vip){
        this.vip=vip;
    }

    public String getNomeTavolo(){
        return nomeTavolo;
    }

    public void setNomeTavolo(String nomeTavolo){
        this.nomeTavolo=nomeTavolo;
    }

    public Boolean getCheckIn(){
        return checkIn;
    }

    public void setCheckIn(Boolean checkIn){
        this.checkIn=checkIn;
    }

    public TipoPrenotazione getTipo(){
        return tipo;
    }

    public void setTipo(TipoPrenotazione tipo){
        this.tipo=tipo;
    }

    public Double getPrezzo(){
        return prezzo;
    }

    public void setPrezzo(Double prezzo){
        if(prezzo>0.0){
            this.prezzo=prezzo;
        }
    }

    @Override
    public String toString(){
        return this.idBiglietto+";"+this.nominativo+";"+this.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+";"+this.luogo+";"+this.prezzo+";"+this.vip+";"+this.nomeTavolo+";"+this.checkIn+";"+this.tipo;
    }

    public void aggiungiDrink(Drink d){
        double costo=0.0;
        switch(d){
            case ACQUA:
                costo=1.0;
                break;
            case BEVANDE:
                costo=2.50;
                break;
            case BIRRA:
                costo=4.0;
                break;
            case VINO:
                costo=6.0;
                break;
            case COCKTAIL:
                costo=6.0;
                break;
            case ANALCOLICO:
                costo=5.0;
                break;
            default:
                costo=0.0;
                break;
        }
        this.prezzo=this.prezzo+costo;
    }
}