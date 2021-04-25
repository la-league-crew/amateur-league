package com.league.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.entity.Round;

@Repository
public interface RoundRepo extends JpaRepository<Round, Long> {

}
