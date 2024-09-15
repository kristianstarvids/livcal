package com.tarvids.livcal;

import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // Read JSON file and store its contents to a string
        String filename = args[0];
        Path path = Path.of(filename);
        String str = Files.readString(path);

        // Create iCalendar and populate it with events from JSON data
        VCalendar calendar = new VCalendar();
        JSONArray jsonArr = new JSONArray(str);
        for (int i = 0; i < jsonArr.length(); i++) {
            calendar.addEvent(
                    jsonArr.getJSONObject(i).getString("activitydesc"),
                    jsonArr.getJSONObject(i).getString("start"),
                    jsonArr.getJSONObject(i).getString("end"),
                    jsonArr.getJSONObject(i).getString("locationdesc"),
                    jsonArr.getJSONObject(i).getString("uniqueid"));
        }

        // Save the created iCalendar to a file
        String outFile = args[1];
        Path outPath = Path.of(outFile);
        calendar.writeCalendar(outPath);
    }
}