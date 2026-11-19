package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File file;

    public Controller() {
        String userHome = System.getProperty("user.home");
        file = new File(userHome, "output.txt");
    }

    public void setCurrentFile(File file) {
        this.file = file;
    }

    public File getCurrentFile() {
        return this.file;
    }

    public String getCurrentPath() {
        return this.file.getPath();
    }

    public void input(String text) throws IOException {
        Files.writeString(file.toPath(), text);
    }

}
