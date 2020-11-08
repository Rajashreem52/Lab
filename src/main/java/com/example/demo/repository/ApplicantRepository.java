package com.example.demo.repository;

/*
Author Rafi
*/

import com.example.demo.model.Applicant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    List<Applicant> findByNameContaining(String name, Sort sort);
}
