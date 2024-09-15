package com.tarvids.livcal;

import org.json.JSONArray;
import org.json.JSONObject;

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
            JSONObject jsonObj = jsonArr.getJSONObject(i);

            String activityName = jsonObj.getString("activityname");
            String activityDesc = jsonObj.getString("activitydesc");
            String start = jsonObj.getString("start");
            String end = jsonObj.getString("end");
            String locationDesc = jsonObj.getString("locationdesc");
            String uniqueID = jsonObj.getString("uniqueid");

            StringBuilder description = new StringBuilder();


            JSONArray locationsJsonArr = jsonObj.getJSONArray("locations");
            if (!locationsJsonArr.isEmpty())
            {
                for (int j = 0; j < locationsJsonArr.length(); j++)
                {
                    String catalogueUrl = locationsJsonArr.getJSONObject(j).getString("CatalogueUrl");
                    if (!catalogueUrl.isEmpty())
                    {
                        description.append("View Room Info: ").append(catalogueUrl).append("\\n\\n");
                    }
                }
            }

            JSONArray staffsJsonArr = jsonObj.getJSONArray("staffs");
            if (!staffsJsonArr.isEmpty())
            {
                description.append("Staff Details: ").append("\\n");
                for (int j = 0; j < staffsJsonArr.length(); j++)
                {
                    String fullName = staffsJsonArr.getJSONObject(j).getString("FullName");
                    String email = staffsJsonArr.getJSONObject(j).getString("Email");
                    String phoneNumber = staffsJsonArr.getJSONObject(j).getString("PhoneNumber");

                    description.append(fullName).append("\\n");
                    description.append(email).append("\\n");
                    description.append(phoneNumber).append("\\n");
                }
            }

            calendar.addEvent(activityDesc, start, end, locationDesc, description.toString(), uniqueID);
        }

        // Save the created iCalendar to a file
        String outFile = args[1];
        Path outPath = Path.of(outFile);
        calendar.writeCalendar(outPath);
    }
}