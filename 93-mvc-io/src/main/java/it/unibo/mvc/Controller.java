package it.unibo.mvc;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This interface represents a controller in the MVC pattern,
 * responsible for managing the flow of data between the model and the view.
 */
public interface Controller {
    public void setNextString(String s) throws NullPointerException;

    public String getNextString();

    public List<String> getStringHystory();

    public void printCurrentString();
}
