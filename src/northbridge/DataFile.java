package northbridge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class DataFile {
    private ObjectOutputStream fOUT;
    private ObjectInputStream fIn;
    private Character mode;
    
   
    public DataFile(String fileName, Character mode) throws MyException, FileNotFoundException, IOException {
        this.setMode(mode);
    
        //based on mode you can use the class to write or read
        if(this.getMode().equals('W'))
            this.fOUT = new ObjectOutputStream(new FileOutputStream(fileName)); //Output
        else
            this.fIn = new ObjectInputStream(new FileInputStream(fileName));    //Input
    }

    public void setMode(Character mode) throws MyException {
        if(mode.equals('R')||mode.equals('W'))
            this.mode = mode;
        else
            throw new MyException("Attenzione! modalita' apertura file errata.");
    }

    public Character getMode() {
        return this.mode;
    }

    public void toFile(Object o) throws MyException, IOException{
        if(this.getMode().equals('R')) 
            throw new MyException("Attenzione! file aperto in lettura.");
        this.fOUT.writeObject(o);
        //flush = prende il file e lo trasforma in stream
        this.fOUT.flush();
    }
    
    public Object fromFile() throws MyException, IOException, ClassNotFoundException{
        if(this.getMode().equals('W'))
            throw new MyException("Attenzione! file aperto in scrittura.");
        return this.fIn.readObject();
    }
    
    public void closeFile() throws IOException{
        if(this.getMode().equals('W'))
            this.fOUT.close();
        else
            this.fIn.close();
    }
}
