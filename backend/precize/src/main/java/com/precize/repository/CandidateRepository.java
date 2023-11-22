package com.precize.repository;

import com.precize.model.Candidate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByName(String name);


    @Transactional
    @Modifying
    @Query("DELETE FROM Candidate c WHERE c.name = :name")
    int deleteByName(@Param("name") String name);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM Candidate c1 WHERE c1.name = :name) " +
            "THEN (SELECT COUNT(*) + 1 FROM Candidate c WHERE c.sat_score > (SELECT c1.sat_score FROM Candidate c1 WHERE c1.name = :name)) " +
            "ELSE -1 END", nativeQuery = true)
    Integer getRank(@Param("name") String name);

}
