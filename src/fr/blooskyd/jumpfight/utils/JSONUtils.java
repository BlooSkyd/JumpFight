package fr.blooskyd.jumpfight.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class JSONUtils {
    private final File file;

    public JSONUtils(String name) {
        this.file = new File(name);

        if(!(this.file.exists())) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject load() throws IOException, ParseException {
        String line = null;
        Scanner scanner = new Scanner(this.file);
        if (scanner.hasNextLine()) line = scanner.nextLine();
        scanner.close();
        if ( line == null || line.isEmpty() ) return new JSONObject();
        return (JSONObject) new JSONParser().parse(line);
    }

    public void save(JSONObject json) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            writer.write(json.toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
