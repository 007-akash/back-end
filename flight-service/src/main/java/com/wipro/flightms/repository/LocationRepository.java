package com.wipro.flightms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.flightms.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

}
