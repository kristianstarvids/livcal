package com.tarvids.livcal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VEvent extends VComponent
{
    private String DTStart;
    private String DTEnd;
    private String summary;
    private String location;

    VEvent(String summary, String DTStart, String DTEnd, String location, String activityID)
    {
        super(activityID);
        this.summary = summary;
        this.DTStart = formatDT(DTStart);
        this.DTEnd = formatDT(DTEnd);
        this.location = location;
    }

    private String formatDT(String input)
    {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

        return dateTime.format(outputFormatter);
    }

    public String getSummary()
    {
        return summary;
    }

    public String getDTStart()
    {
        return DTStart;
    }

    public String getDTEnd()
    {
        return DTEnd;
    }

    public String getLocation()
    {
        return location;
    }
}
