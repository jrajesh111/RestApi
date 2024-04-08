package com.sathya.rest.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sathya.rest.entity.Hospital;
import com.sathya.rest.model.HospitalDetails;
import com.sathya.rest.repository.HospitalRepository;

@Service
public class HospitalService 
{
	@Autowired
	HospitalRepository hospitalRepository;
	public Hospital createHospital(HospitalDetails hospitalDetails) {
		//Reading the data from model class set the data to entity class
		Hospital hospital=new Hospital();
		hospital.setName(hospitalDetails.getName());
		hospital.setLocation(hospitalDetails.getLocation());
		hospital.setRating(hospitalDetails.getRating());
		hospital.setEmail(hospitalDetails.getEmail());
		hospital.setMobile(hospitalDetails.getMobile());
		
		hospital.setCreaAT(LocalDateTime.now());
		hospital.setCreateBy(System.getProperty("user.name"));
		
		//save method create save the hospital in database return the hospital
		
		Hospital saveHospital=hospitalRepository.save(hospital);
		return saveHospital;
	}
	
	public Hospital getHospital(Long id) 
	{
		
		Optional<Hospital> optionalHospital=hospitalRepository.findById(id);
		if(optionalHospital.isPresent())
		{
			return optionalHospital.get();
		}else {
		
		return null;
		}	
	}
	public List<Hospital> getHospitalList() {
		
		return hospitalRepository.findAll();
	}
	public void deleteHospital(Long id) 
	{
		hospitalRepository.deleteById(id);
	}

	public List<Hospital> saveAllHospitals(List<HospitalDetails> hospitals) 
	{
		//reading data from model class set the data to entity class
		List<Hospital> hospitals1=new ArrayList<>();
		for(HospitalDetails  hospitalDetails: hospitals)
		{
			Hospital hospital=new Hospital();
			
			hospital.setName(hospitalDetails.getName());
			hospital.setLocation(hospitalDetails.getLocation());
			hospital.setRating(hospitalDetails.getRating());
			hospital.setEmail(hospitalDetails.getEmail());
			hospital.setMobile(hospitalDetails.getMobile());
			
			hospital.setCreaAT(LocalDateTime.now());
			hospital.setCreateBy(System.getProperty("user.name"));
			
			hospitals1.add(hospital);
		}
		return hospitalRepository.saveAll(hospitals1);
	}

	public Hospital updateHospital(Long id, HospitalDetails updatedDetails) 
	{
		
		// Assuming you have a service class to handle updating     
        Optional<Hospital> hospital =  hospitalRepository.findById(id);       
        if(hospital.isPresent()) {
			Hospital hospital2 = hospital.get();
			
			hospital2.setName(updatedDetails.getName());
			hospital2.setLocation(updatedDetails.getLocation());
			hospital2.setRating(updatedDetails.getRating());
			
			hospital2.setCreaAT(LocalDateTime.now());
			hospital2.setCreateBy(System.getProperty("user.name"));
			
		return hospitalRepository.save(hospital2);
        }	
		else 
		{
			 return null;			 
		}
	}
	public Hospital saveField(Long id, HospitalDetails updatedDetails) 
	{	
		Optional<Hospital> hospital =  hospitalRepository.findById(id);   
	        if (hospital.isPresent()) 
	        {	
	        	Hospital hospital2=hospital.get();
	        	
	            if (updatedDetails.getName() != null) {
	            	hospital2.setName(updatedDetails.getName());
	            }
	            if (updatedDetails.getLocation() != null) {
	            	hospital2.setLocation(updatedDetails.getLocation());
	            }
	            if (updatedDetails.getRating() != 0) {
	            	hospital2.setRating(updatedDetails.getRating());
	            }
	            return hospitalRepository.save(hospital2); 
	        }
			return null;
	} 
	public Hospital getHospital(String name) 
	{
		return hospitalRepository.findByName(name);
	}
	public List<Hospital> getHospitalsByNameAndLocation(String name, String location) 
	{		
		return hospitalRepository.findByNameAndLocation(name, location);
	}
	public List<Hospital> findHospitalsByRatingRange(double minRating, double maxRating) 
	{	
		return hospitalRepository.findByRatingBetween(minRating, maxRating);
	}
	
	public List<Hospital> findHospitalsByListOfId(List<Long> ids) 
	{	
		return hospitalRepository.findByIdIn(ids);
	}

	public List<Hospital> getAllHospitalSorted() 
	{
		Sort sort = Sort.by(Direction.ASC,"name");
		return hospitalRepository.findAll(sort);
	}
	
	public Page<Hospital> getDataByPage(int pageNo, int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return hospitalRepository.findAll(pageable);	
	}	
}