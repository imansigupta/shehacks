package demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.models.Root;
import demo.models.FreeSlots;
import demo.models.ScheduleItem;

@SpringBootApplication
public class Day1SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day1SpringBootApplication.class, args);
	}
	
}
