package northbridge;

import java.io.IOException;
import java.time.format.DateTimeParseException;



public class MyException extends Exception{
    private final Exception originale;

    public MyException(Exception e){
        super(e);
        this.originale = e;
    }
    
    //overloading the function to output a string in case no exception will be raised but the valus is wrong (es: price<0)
    
    public MyException(String msg){
        super(msg);
        this.originale = null;
    }
    
    
    //it catches the exception and based on which prints a different msg
    
    public String getMsg(){
    if(this.originale instanceof IOException){
        return "Errore di lettura/scrittura file";
    }
    if(this.originale instanceof NumberFormatException){
        return "Hai inserito un valore tipo sbagliato";
    }
    if(this.originale instanceof NullPointerException){
        return "Elemento vettore inesistente";
    }
    if(this.originale instanceof ArrayIndexOutOfBoundsException){
        return "Errore: limite massimo superato o indice errato";
    }
    if(this.originale instanceof DateTimeParseException){
        return "Formato data non valido (usa GG-MM-AAAA)";
    }
    if(this.originale instanceof IllegalArgumentException){
        return "Valore non valido(controlla Luogo, Tipo o Drink)";
    }
    return "Errore imprevisto: " + originale.getMessage();

    }
    
}
