package edu.seminolestate.term;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;
 
/**
 * This file handles the output of the AssetDB into the GUI.
 * @author Hector Deida
 *
 */
public class Deida_CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    /**
     * @param textArea The window where the output is printed.
     */
    public Deida_CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    /**
     *@exception IOException on input error
     *@see IOException
     */
    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}