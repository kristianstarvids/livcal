package com.tarvids.livcal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class VCalendar
{
    private String version;
    private String prodID;
    private String calScale;
    private ArrayList<VComponent> components;

    VCalendar()
    {
        this.version = "2.0";
        this.prodID = "-//livcal.tarvids.com//livcal";
        this.calScale = "GREGORIAN";
        this.components = new ArrayList<>();
    }

    public void addEvent(String summary, String startTime, String endTime, String location, String activityId)
    {
        VEvent event = new VEvent(summary, startTime, endTime, location, activityId);
        this.components.add(event);
    }

    public void writeCalendar(Path path)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("BEGIN:VCALENDAR").append(System.lineSeparator());
        sb.append("VERSION:").append(this.version).append(System.lineSeparator());
        sb.append("PRODID:").append(this.prodID).append(System.lineSeparator());
        sb.append("CALSCALE:").append(this.calScale).append(System.lineSeparator());
        for (VComponent component : this.components)
        {
            sb.append("BEGIN:VEVENT").append(System.lineSeparator());
            sb.append("DTSTAMP:").append(component.getDTStamp()).append(System.lineSeparator());
            sb.append("UID:").append(component.getUID()).append(System.lineSeparator());
            if (component instanceof VEvent event) {
                sb.append("DTSTART:").append(event.getDTStart()).append(System.lineSeparator());
                sb.append("DTEND:").append(event.getDTEnd()).append(System.lineSeparator());
                sb.append("SUMMARY:").append(event.getSummary()).append(System.lineSeparator());
                sb.append("LOCATION:").append(event.getLocation()).append(System.lineSeparator());
            }
            sb.append("END:VEVENT").append(System.lineSeparator());
        }
        sb.append("END:VCALENDAR").append(System.lineSeparator());

        try
        {
            Files.writeString(path, sb.toString());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
