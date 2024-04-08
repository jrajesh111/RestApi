package com.sathya.rest.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sathya.rest.entity.Hospital;
@Repository
@Component
public interface HospitalRepository extends JpaRepository<Hospital , Long>
{
	public Hospital findByName(String name);
	
	public List<Hospital> findByNameAndLocation(String name,String location);
	
	public List<Hospital> findByRatingBetween(double minRating,double maxRating);

	public List<Hospital> findByIdIn(List<Long> ids);

	
}
