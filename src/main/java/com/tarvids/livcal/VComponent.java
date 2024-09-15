package com.tarvids.livcal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class VComponent
{
    private String DTStamp;
    private String UID;

    VComponent(String activityID)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        LocalDateTime now = LocalDateTime.now();
        this.DTStamp = dtf.format(now);
        this.UID = activityID + "@livcal.tarvids.com";
    }

    public String getDTStamp()
    {
        return DTStamp;
    }

    public String getUID()
    {
        return UID;
    }
}
