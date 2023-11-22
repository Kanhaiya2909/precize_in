package com.precize.service;

import com.precize.model.Candidate;
import com.precize.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate insertCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public int getRank(String name) {
        // Implement logic to get rank from the database
        // Example: SELECT COUNT(*) + 1 FROM Candidate WHERE satScore > (SELECT satScore FROM Candidate WHERE name = :name)
        Integer rank = candidateRepository.getRank(name);
        return rank != null ? rank : -1;
    }

    public void updateScore(String name, int newScore) {
        candidateRepository.findByName(name).ifPresent(candidate -> {
            candidate.setSatScore(newScore);
            candidateRepository.save(candidate);
        });
    }

    public Candidate getCandidateByName(String name){
        return candidateRepository.findByName(name).get();
    }

    public ResponseEntity<String> deleteCandidateByName(String name) {
            int deletedCount = candidateRepository.deleteByName(name);

            if (deletedCount > 0) {
                return ResponseEntity.ok( "Record deleted successfully");
            } else {
                return ResponseEntity.ok("No Found");
            }
    }

    public boolean isCandidatePassed(String name) {
        return candidateRepository.findByName(name)
                .map(Candidate::isPassed)
                .orElse(false); // Default to false if candidate not found
    }

}

