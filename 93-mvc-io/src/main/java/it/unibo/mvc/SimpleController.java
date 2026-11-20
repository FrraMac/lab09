package it.unibo.mvc;

import java.util.List;
import java.util.ArrayList;

/**
 * A simple implementation of the {@link Controller} interface.
 */
public final class SimpleController implements Controller {
    private String currentString;
    private List<String> strings = new ArrayList<String>();
    @Override
    public String getNextString() {
        if(currentString == null) {
            throw new IllegalStateException();
        }
        return currentString;
    }

    @Override
    public List<String> getStringHystory() {
        return strings;
    }

    @Override
    public void printCurrentString() {
        System.out.println(currentString);
    }

    @Override
    public void setNextString(String s) throws NullPointerException {
        strings.add(s);
        currentString = s;
    }

}
