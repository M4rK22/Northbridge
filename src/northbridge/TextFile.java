/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package northbridge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author uricc_jo2iomb
 */

public class TextFile {
    private BufferedReader reader;
    private BufferedWriter writer;
    private Character mode;
    
    //MyException viene generata da setMode, NotFoundException generato dal fileInputStream, IOEXception generato dalla creazione del buffer
    public TextFile(String fileName, Character mode) throws MyException, FileNotFoundException, IOException {
        this.setMode(mode);
        if(this.getMode().equals('R'))
            this.reader = new BufferedReader(new FileReader(fileName));
        else
            this.writer = new BufferedWriter(new FileWriter(fileName));

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

    public void toFile(String line) throws MyException, IOException{
        if(this.getMode().equals('R')) 
            throw new MyException("Attenzione! file aperto in lettura.");
        this.writer.write(line);
        this.writer.newLine();
    }
    
    public String fromFile() throws MyException, IOException, ClassNotFoundException{
        if(this.getMode().equals('W'))
            throw new MyException("Attenzione! file aperto in scrittura.");
        String temp = this.reader.readLine();
        if(temp == null)
            throw new MyException("Attenzione! fine file raggiunta.");
        
        return temp;
    }
    
    public void closeFile() throws IOException{
        if(this.getMode().equals('W'))
            this.writer.close();
        else
            this.reader.close();
    }
}

