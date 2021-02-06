package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IOController implements Controller {
    
    private String nextString;
    
    private final List<String> listString = new ArrayList<>();
    

    @Override
    public void setNextString(final String s) throws NullPointerException {
        this.nextString = Objects.requireNonNull(s, "NO NULL VALUES");
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return this.listString;
    }

    @Override
    public void printCurrentString() throws IllegalStateException {
        if (this.nextString == null) {
            throw new IllegalStateException();
        }
        listString.add(this.nextString);
        System.out.println(this.nextString);
    }

}
