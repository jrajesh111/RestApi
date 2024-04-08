package com.sathya.rest.controlar;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.rest.entity.Hospital;
import com.sathya.rest.model.HospitalDetails;
import com.sathya.rest.repository.HospitalRepository;
import com.sathya.rest.service.HospitalService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/hospital")

public class HospitalControlar 
{	
	@Autowired
	HospitalService hospitalService;
	@PostMapping("/save")
	public Hospital postMethodName(@Valid @RequestBody HospitalDetails hosdDetails) 
	{
		return 	hospitalService.createHospital(hosdDetails);
	}
	@GetMapping("/{id}")
	public Hospital getHospital(@PathVariable Long id)
	{
		return hospitalService.getHospital(id);	
	}
	@GetMapping("/getall")
	public List<Hospital> getHospitals()
	{
		return hospitalService.getHospitalList();	
	}
	
	@DeleteMapping("/{id}")
	public void deleteHospitalByID(@PathVariable Long id)
	{
		hospitalService.deleteHospital(id);
	}
	
	@PostMapping("/saveall")
	public List<Hospital> saveAll(@RequestBody List<HospitalDetails> hospitals)
	{
		return hospitalService.saveAllHospitals(hospitals);
	}
	@PutMapping("/update/{id}")
    public Hospital updateHospitalDetails(@PathVariable("id") Long id, @RequestBody HospitalDetails updatedDetails) 
	{     
            return hospitalService.updateHospital(id,updatedDetails);      
	}	
	
	// Update specific fields of hospital details
    @PatchMapping("/savefield/{id}")
    public Hospital updateField(@PathVariable("id") Long id, @RequestBody HospitalDetails updatedfield)
    {      
    		return hospitalService.saveField(id,updatedfield);
    }   
    
    // find by name
    @GetMapping("/name/{name}")
    public Hospital getHospitalByName(@PathVariable("name") String name)
    {
    	
		return hospitalService.getHospital(name);
    	
    }
    
    @GetMapping("/search/{name}/{location}")
    public List<Hospital> getHospitalsByNameAndLocation(@PathVariable String name, @PathVariable String location) 
    {
    	return hospitalService.getHospitalsByNameAndLocation(name, location);
    }
    
    @GetMapping("/hospitals")
    public List<Hospital> getHospitalsByRatingRange(@RequestParam double minRating, @RequestParam double maxRating) {
        return hospitalService.findHospitalsByRatingRange(minRating, maxRating);
    }
    
    @GetMapping("/hospitals/byIds")
    public List<Hospital> getHospitalsByIds(@RequestBody List<Long> ids)
    {
        return hospitalService.findHospitalsByListOfId(ids);
    }
    
    @GetMapping("/sorting")
    public List<Hospital> getHospitalBySort() {
        return hospitalService.getAllHospitalSorted();     
    } 
    @GetMapping("/{pageNo}/{pageSize}")
    public Page<Hospital> getHospitalPage(@PathVariable int pageNo,@PathVariable  int pageSize)
    {
		return hospitalService.getDataByPage(pageNo,pageSize);	
    }
}
