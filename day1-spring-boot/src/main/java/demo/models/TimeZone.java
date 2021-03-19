package demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeZone{
    @JsonProperty("@odata.type") 
    public String odataType;
    public int bias;
    public String name;
    public StandardOffset standardOffset;
    public DaylightOffset daylightOffset;
}