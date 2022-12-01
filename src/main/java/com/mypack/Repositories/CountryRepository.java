package com.mypack.Repositories;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypack.Entity.Country;
@Configuration
@Repository
public interface CountryRepository extends JpaRepository<Country, Serializable> {

}
