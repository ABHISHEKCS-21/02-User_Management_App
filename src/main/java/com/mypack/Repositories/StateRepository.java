package com.mypack.Repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypack.Entity.State;
@Configuration
@Repository
public interface StateRepository extends JpaRepository<State,Serializable> {

	List<State> findByCountryId(Integer countryId);


}
