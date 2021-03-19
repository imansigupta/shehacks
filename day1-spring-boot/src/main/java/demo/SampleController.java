package demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.models.FreeSlots;
import demo.models.Root;
import demo.models.ScheduleItem;

//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
@RestController
public class SampleController {
	@RequestMapping("/sample")
	public String display() {
		return getData();
	}
		
	

	private String getData()
	{		
			int [] myarray = new int[24];
			Arrays.fill(myarray, 0);
			myarray=updateAvailability(myarray,"id1");
			myarray=updateAvailability(myarray,"id2");
			myarray=updateAvailability(myarray,"id3");
			myarray=updateAvailability(myarray,"id4");
			
			return getFreeSlots(myarray);
			
	}
	
	private  String getFreeSlots(int[] myarray) {
		List<FreeSlots> fs= new ArrayList<FreeSlots>();
	for(int i=0;i<24;i++) {
		if(myarray[i]==0) {
			fs.add(new FreeSlots(i+":00",i+1+":00"));
		}
	} 
	ObjectMapper mapper = new ObjectMapper();
	 String s="";
	try {
		s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fs);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    System.out.println(s);
    return s;
    
	}


	private  int[] updateAvailability(int [] myarray,String id) {
		ObjectMapper om=new ObjectMapper();
		File f= new File("/Users/swatityagi/Desktop/Microservices and Rest API/MST/day1/day1-spring-boot/src/main/resources/"+id+".json");
		Root root=null;
		try {
			root=om.readValue(f, Root.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int start=Integer.parseInt(root.workingHours.startTime.substring(0, 2));
		for(int i=0;i<start;i++) {myarray[i]++;}
		int end=Integer.parseInt(root.workingHours.endTime.substring(0, 2));
		for(int i=end;i<24;i++) {myarray[i]++;}
		for(ScheduleItem si:root.scheduleItems) {
			int scstart=Integer.parseInt(si.start.dateTime.substring(11, 13));
			int scend=Integer.parseInt(si.end.dateTime.substring(11, 13));
			for (int i=scstart;i<scend;i++) {
				{myarray[i]++;}
			}}
		return myarray;
	}

	

}
