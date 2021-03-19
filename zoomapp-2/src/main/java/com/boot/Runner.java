package com.boot;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.boot.ZoomMeetingObjectDTO;

@RestController
public class Runner {
	
	String zoomUserId = "ayushman16026@iiitd.ac.in";
	String yourPass = "Kas";
	
	String zoomApiKey = "xxRD3PEhTQGkpLldp4zh4g";
	String zoomApiSecret = "rP1YLBl53YWKaeOgDKVFdSYJrWmYwtwPcy9y";
	
	
	
	public Runner() {
		System.out.println("Runner class called...");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/create_meeting")
	public ZoomMeetingObjectDTO createMeeting(ZoomMeetingObjectDTO zoomMeetingObjectDTO) {
		
	        try{
	        	System.out.println("Request to create a Zoom meeting");
	      
		       // replace zoomUserId with your user ID
		        String apiUrl = "https://api.zoom.us/v2/users/" + zoomUserId + "/meetings";
	
		      // replace with your password or method
		        zoomMeetingObjectDTO.setPassword(yourPass);
		      // replace email with your email
		        zoomMeetingObjectDTO.setHost_email("ayushman16026@iiitd.ac.in");
	
		        //Custom Parameters
		        zoomMeetingObjectDTO.setTopic("HSBC Buddy - Talk Room 1");
		     
		        
		      // Optional Settings for host and participant related options
		        ZoomMeetingSettingsDTO settingsDTO = new ZoomMeetingSettingsDTO();
		       
		        settingsDTO.setJoin_before_host(false);
		        settingsDTO.setParticipant_video(true);
		        settingsDTO.setHost_video(false);
		        settingsDTO.setAuto_recording("cloud");
		        settingsDTO.setMute_upon_entry(true);
		        zoomMeetingObjectDTO.setSettings(settingsDTO);
	
		        RestTemplate restTemplate = new RestTemplate();
		        HttpHeaders headers = new HttpHeaders();
		        headers.add("Authorization", "Bearer " + generateZoomJWTTOken());
		        headers.add("content-type", "application/json");
		        HttpEntity<ZoomMeetingObjectDTO> httpEntity = new HttpEntity<ZoomMeetingObjectDTO>(zoomMeetingObjectDTO, headers);
		        ResponseEntity<ZoomMeetingObjectDTO> zEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ZoomMeetingObjectDTO.class);
		        
		        if(zEntity.getStatusCodeValue() == 201) {
		            System.out.println("Zooom meeeting response :");
		            System.out.println(zEntity.toString());
		            return zEntity.getBody();
		        } else {
		        	System.out.println("Error while creating zoom meeting :");
		            System.out.println(zEntity.getStatusCode());
		        }
	        }
	        catch(Exception e) {
	        	System.out.println("Error : ");
	        	e.printStackTrace();
	        }
	        return zoomMeetingObjectDTO;
	    }


	    /**
	     * Generate JWT token for Zoom using api credentials
	     * 
	     * @return JWT Token String
	     */
	    private String generateZoomJWTTOken() {
	        String id = UUID.randomUUID().toString().replace("-", "");
	        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	        Date creation = new Date(System.currentTimeMillis());
	        Date tokenExpiry = new Date(System.currentTimeMillis() + (1000 * 60));

	        Key key = Keys
	            .hmacShaKeyFor(zoomApiSecret.getBytes());
	        return Jwts.builder()
	            .setId(id)
	            .setIssuer(zoomApiKey)
	            .setIssuedAt(creation)
	            .setSubject("")
	            .setExpiration(tokenExpiry)
	            .signWith(key, signatureAlgorithm)
	            .compact();
	    }
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/list_meeting")
	    public ZoomMeetingsListResponseDTO listMeetings(Optional<String> userIdOrEmail, Optional<String> meetingType) {
	        System.out.println("Request to list all Zoom meetings by User id or email : " + userIdOrEmail);
	        // replace me with user id in case, listing meetings for a different user than admin
	        String listMeetingUrl = "https://api.zoom.us/v2/users/me/meetings";
	        // replace either user Id or email here with your user id/email
	        if(userIdOrEmail.isPresent()) {
	            listMeetingUrl = "https://api.zoom.us/v2/users/"+ userIdOrEmail.get() +"/meetings";
	        }
	        RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "Bearer " + generateZoomJWTTOken());
	        headers.add("content-type", "application/json");
	        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
	        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(listMeetingUrl);
	        if(meetingType.isPresent()) {
	            urlBuilder.queryParam("type", meetingType.get());
	        }
	        ResponseEntity<ZoomMeetingsListResponseDTO> response = restTemplate
	            .exchange(urlBuilder.toUriString(), HttpMethod.GET, requestEntity, ZoomMeetingsListResponseDTO.class);
	        if(response.getStatusCodeValue() == 200) {
	            return response.getBody();
	        } else if (response.getStatusCodeValue() == 404) {
	            System.out.println("Internal Server Error :User id or email not found for supplied value");
	        }
	        return null;
	    }

	    
	
}
