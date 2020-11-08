package com.example.demo;

/*
Author Rafi
*/

import com.example.demo.model.Applicant;
import com.example.demo.repository.ApplicantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ApplicantTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ApplicantRepository repository;


//    Applicant applicant1 = applicantRepository.save(new Applicant("Rafi", "abc@gmail.com", "Male", 55, "abcssadasasd", "None", Applicant.Status.PENDING));
//    Applicant applicant2 = applicantRepository.save(new Applicant("Nurul", "abcd@gmail.com", "Male", 56, "abcsadsadsa", "Expert", Applicant.Status.PENDING));
//    Applicant applicant3 = applicantRepository.save(new Applicant("Karim", "abce@gmail.com", "Male", 60, "abcsadsadasdas", "Pro", Applicant.Status.PENDING));
//    Applicant applicant4 = applicantRepository.save(new Applicant("Rebecca", "abcef@gmail.com", "Female", 56, "abcsadasdasdsa", "Pro", Applicant.Status.PENDING));
//    Applicant applicant5 = applicantRepository.save(new Applicant("Hall", "abcefg@gmail.com", "Female", 65, "abcsadsadasdas", "Experienced", Applicant.Status.PENDING));

    @Test
    public void applicationsEmpty() {

        Iterable<Applicant> applicants = repository.findAll();
        assertThat(applicants).isEmpty();
    }

    @Test
    public void noOfApplicationLimitTest() {

        Applicant applicant1 = new Applicant("Rafi", "abc@gmail.com", "Male", 55, "abcssadasasd", "None", Applicant.Status.ACCEPTED);
        entityManager.persist(applicant1);

        Applicant applicant2 = new Applicant("Nurul", "abcd@gmail.com", "Male", 56, "abcsadsadsa", "Expert", Applicant.Status.PENDING);
        entityManager.persist(applicant2);

        Iterable<Applicant> applicants = repository.findAll();
        int count = 0;
        for (Applicant applicant : applicants) {
            if (applicant.getStatus() == Applicant.Status.ACCEPTED) {
                count += 1;
            }
        }

        assertThat(count).isEqualTo(1);
        assertThat(applicants).hasSize(2).contains(applicant1, applicant2);
    }

    @Test
    public void maleApplicantTest() {

        Applicant applicant1 = new Applicant("Rafi", "abc@gmail.com", "Male", 54, "abcssadasasd", "None", Applicant.Status.PENDING);
        entityManager.persist(applicant1);

        assertThat(applicant1.getAge()).isLessThan(55);


    }

    @Test
    public void femaleApplicantTest() {

        Applicant applicant1 = new Applicant("Rebecca", "abcef@gmail.com", "Female", 56, "abcsadasdasdsa", "Pro", Applicant.Status.PENDING);
        entityManager.persist(applicant1);

        assertThat(applicant1.getAge()).isLessThan(57);
    }

    @Test
    public void passwordLengthTest() {

        Applicant applicant1 = new Applicant("Rebecca", "abcef@gmail.com", "Female", 56, "abcsadasdasdsa", "Pro", Applicant.Status.PENDING);
        entityManager.persist(applicant1);

        assertThat(applicant1.getPassword().length()).isGreaterThan(9);
    }

    @Test
    public void changeStatusTest() {

        Applicant applicant1 = new Applicant("Rebecca", "abcef@gmail.com", "Female", 56, "abcsadasdasdsa", "Pro", Applicant.Status.PENDING);
        entityManager.persist(applicant1);

        applicant1.setStatus(Applicant.Status.INVITED);
        entityManager.persist(applicant1);

        assertThat(applicant1.getStatus()).isEqualTo(Applicant.Status.INVITED);
    }

    @Test
    public void emptyNotesTest() {

        Applicant applicant1 = new Applicant("Rebecca", "abcef@gmail.com", "Female", 56, "abcsadasdasdsa", "Pro", Applicant.Status.PENDING);
        entityManager.persist(applicant1);

        assertThat(applicant1.getNotes()).isEqualTo("");
    }

}
