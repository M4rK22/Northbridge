
package northbridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


//classic console input

public class ConsoleInput {
    private BufferedReader input = null;

    
    //i use a buffered reader to generalize datas
    public ConsoleInput() {
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public Integer readInteger() throws IOException{
        return Integer.valueOf(this.input.readLine());
    }
    
    public Double readDouble() throws IOException{
        return Double.valueOf(this.input.readLine());
    }
    
    public Boolean readBoolean() throws IOException{
        return Boolean.valueOf(this.input.readLine());
    }
    
    public String readString() throws IOException{
        return this.input.readLine();
    }
    
    
    //to read enums and be sure no other values will be in input
    public TipoPrenotazione readTipologia() throws IOException{
        String tipo = this.input.readLine().toUpperCase(Locale.ITALY);
        
        switch(tipo){
            case "PR" -> {
                return TipoPrenotazione.PR;
            }
            case "PERSONALE" -> {
                return TipoPrenotazione.PERSONALE;
            }
            case "ANNUNCIO" -> {
                return TipoPrenotazione.ANNUNCIO;
            }
            case "TAVOLO" -> {
                return TipoPrenotazione.TAVOLO;
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
        
    }
    
    public Luogo readLuogo() throws IOException{
        String tipo = this.input.readLine().toUpperCase();
        
        switch(tipo){
            case "ARENA" -> {
                return Luogo.ARENA;
            }
            case "PISTA" -> {
                return Luogo.PISTA;
            }
            case "BAR" -> {
                return Luogo.BAR;
            }
            case "VIP" -> {
                return Luogo.VIP;
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
        
    }
    
    public Drink readDrink() throws IOException{
        String tipo = this.input.readLine().toUpperCase();
        
        switch(tipo){
            case "ACQUA" -> {
                return Drink.ACQUA;
            }
            case "BEVANDE" -> {
                return Drink.BEVANDE;
            }
            case "ANALCOLICO" -> {
                return Drink.ANALCOLICO;
            }
            case "COCKTAIL" -> {
                return Drink.COCKTAIL;
            }
            case "BIRRA" -> {
                return Drink.BIRRA;
            }
            case "VINO" -> {
                return Drink.VINO;
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }

    }
    
    //european pattern
    public LocalDate readDate() throws IOException{
        return LocalDate.parse(this.input.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy")); 
    }
    
}
