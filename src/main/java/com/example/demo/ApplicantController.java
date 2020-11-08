package com.example.demo;

/*
Author Rafi
*/


import com.example.demo.model.Applicant;
import com.example.demo.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ApplicantController {

    @Autowired
    ApplicantRepository applicantRepository;

    @GetMapping("/applicants")
    public ResponseEntity<List<Applicant>> getAllApplicantes() {
        try {

            List<Applicant> applicantes = new ArrayList<Applicant>();
            applicantRepository.findAll().forEach(applicantes::add);

            if (applicantes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(applicantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/applicants/populate")
    public ResponseEntity<List<Applicant>> populateApplicantes() {

        try {
            Applicant applicant1 = applicantRepository.save(new Applicant("Rafi", "abc@gmail.com", "Male", 55, "abcssadasasd", "None", Applicant.Status.PENDING));
            Applicant applicant2 = applicantRepository.save(new Applicant("Nurul", "abcd@gmail.com", "Male", 56, "abcsadsadsa", "Expert", Applicant.Status.PENDING));
            Applicant applicant3 = applicantRepository.save(new Applicant("Karim", "abce@gmail.com", "Male", 60, "abcsadsadasdas", "Pro", Applicant.Status.PENDING));
            Applicant applicant4 = applicantRepository.save(new Applicant("Rebecca", "abcef@gmail.com", "Female", 56, "abcsadasdasdsa", "Pro", Applicant.Status.PENDING));
            Applicant applicant5 = applicantRepository.save(new Applicant("Hall", "abcefg@gmail.com", "Female", 65, "abcsadsadasdas", "Experienced", Applicant.Status.PENDING));


            List<Applicant> applicantes = new ArrayList<>();
            applicantes.add(applicant1);
            applicantes.add(applicant2);
            applicantes.add(applicant3);
            applicantes.add(applicant4);
            applicantes.add(applicant5);

            return new ResponseEntity<>(applicantes, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/applicant/status")
    public ResponseEntity<Applicant> updateStatusOfApplicant(@RequestBody Applicant applicant) {

        Applicant.Status givenStatus = checkEligibility(applicant);
        applicant.setStatus(givenStatus);

        if (givenStatus == Applicant.Status.INVITED) {
            applicant.setNotes("ADMINISTRATION DETAILS ABOUT INTERVIEW");
        }

        applicantRepository.save(applicant);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    public Applicant.Status checkEligibility(Applicant applicant) {

        int noOfAcceptedApplicants = getNoOfAcceptedApplicants();

        if (applicant.getGender().equals("Male")) {

            if (applicant.getAge() >= 55) {
                return Applicant.Status.DECLINED;
            }

        } else if (applicant.getGender().equals("Female")) {

            if (applicant.getAge() >= 57) {
                return Applicant.Status.DECLINED;
            }
            return Applicant.Status.INVITED;

        } else if (applicant.getPassword().length() <= 9) {
            return Applicant.Status.DECLINED;

        } else if (noOfAcceptedApplicants >= 200){
            return Applicant.Status.PENDING;
        }

        return Applicant.Status.ACCEPTED;
    }

    public int getNoOfAcceptedApplicants() {

        List<Applicant> applicants = new ArrayList<Applicant>();
        applicantRepository.findAll().forEach(applicants::add);

        int count = 0;
        for (Applicant applicant : applicants) {
            if (applicant.getStatus() == Applicant.Status.ACCEPTED) {
                count += 1;
            }
        }

        return count;
    }

}

