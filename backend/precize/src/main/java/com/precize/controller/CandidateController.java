package com.precize.controller;

import com.precize.model.Candidate;
import com.precize.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin("*")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/save")
    public ResponseEntity<Candidate> insertCandidate(@RequestBody Candidate candidate) {
        Candidate insertedCandidate = candidateService.insertCandidate(candidate);
        return ResponseEntity.ok(insertedCandidate);
    }

    @GetMapping("/view-all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/get/{name}")
    public Candidate getCandidateByName(@PathVariable("name") String name){
        return this.candidateService.getCandidateByName(name);
    }

    @GetMapping("/get-rank/{name}")
    public Integer getRank(@PathVariable String name) {
        return this.candidateService.getRank(name);
    }

    @PutMapping("/update-score/{name}")
    public ResponseEntity<Candidate> updateScore(@PathVariable String name, @RequestParam int newScore) {
        candidateService.updateScore(name, newScore);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCandidate(@PathVariable String name) {
        return this.candidateService.deleteCandidateByName(name);
    }

    @GetMapping("/is-passed/{name}")
    public boolean isPasses(@PathVariable String name){
        return this.candidateService.isCandidatePassed(name);
    }
}

