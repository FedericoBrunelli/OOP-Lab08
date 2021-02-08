package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamView implements DrawNumberView {

    private final PrintStream out;
    
    public PrintStreamView(final PrintStream stream) {
        out = stream;
    }
    
    public PrintStreamView(final String path) throws FileNotFoundException{
        out = new PrintStream(new FileOutputStream(new File(path)));
    }
    
    @Override
    public void setObserver(DrawNumberViewObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    @Override
    public void numberIncorrect() {
        out.println("You musst enter a number");
    }

    @Override
    public void result(DrawResult res) {
        out.println(res.getDescription());

    }

    @Override
    public void limitsReached() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayError(String message) {
        out.println("Error: " + message);
    }

}
