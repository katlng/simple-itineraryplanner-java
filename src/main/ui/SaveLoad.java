package ui;

import com.google.gson.Gson;

import java.io.IOException;

public interface SaveLoad {

    public Gson setupGson();

    public void save(String filename) throws IOException;

    public void load(String filename) throws IOException;
}
