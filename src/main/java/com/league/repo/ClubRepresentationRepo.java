package com.league.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.entity.ClubRepresentation;

@Repository
public interface ClubRepresentationRepo extends JpaRepository<ClubRepresentation, Long>{

}
