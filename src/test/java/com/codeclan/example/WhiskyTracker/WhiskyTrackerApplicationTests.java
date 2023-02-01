package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	private DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskiesByYear(){
		List<Distillery> found = distilleryRepository.findByWhiskiesYear(2018);
		assertTrue(found.size()>0);
	}

	@Test
	public void canFindDistilleriesByRegion(){
		List<Distillery> found = distilleryRepository.findDistilleryByRegion("Highland");
		assertTrue(found.size()>0);
	}

	@Test
	public void canfindDistilleriesThatHaveWhiskiesByAgeQueryString() {
		List<Distillery> found = distilleryRepository.findByWhiskiesAge(12);
		assertTrue(found.size() > 0);
	}
}
