package demo.models;

public class FreeSlots {
String start;
String end;
public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}
public String getEnd() {
	return end;
}
public void setEnd(String end) {
	this.end = end;
}
public FreeSlots(String start, String end) {
	super();
	this.start = start;
	this.end = end;
}
}
