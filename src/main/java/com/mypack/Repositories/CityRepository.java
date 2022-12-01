package com.mypack.Repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypack.Entity.City;

@Configuration
@Repository
public interface CityRepository extends JpaRepository<City, Serializable> {

	List<City> findByStateId(Integer stateId);

}
