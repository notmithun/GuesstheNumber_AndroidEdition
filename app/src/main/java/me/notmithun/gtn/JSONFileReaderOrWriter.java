package me.notmithun.gtn;

import android.util.Log;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JSONFileReaderOrWriter {

    private static final String TAG = "JSON File Reader / Writer - Guess the Number by notmithun_";

    public static void Reader() {
        Gson gson = new Gson();

        // Read JSON from a file
        try (Reader reader = new FileReader("person.json")) {

            // convert the JSON data to a Java object
            jsonStuff jsonStuff = gson.fromJson(reader, jsonStuff.class);
            Log.i(TAG, String.valueOf(jsonStuff));

        } catch (IOException  e) {
            throw new RuntimeException(e);
        }
    }
}
