package com.riwi.clanes_crud.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.riwi.clanes_crud.entities.Cohort;
import com.riwi.clanes_crud.repositories.CohortRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private final CohortRepository cohortRepository;

    @Override
    public void run(String... args) throws Exception {
        
        if (this.cohortRepository.count() > 0) return;            
     

        Cohort cohort1 = Cohort.builder().name("Cohort 1").build();
        Cohort cohort2 = Cohort.builder().name("Cohort 2").build();
        Cohort cohort3 = Cohort.builder().name("Cohort 3").build();

        this.cohortRepository.save(cohort1);
        this.cohortRepository.save(cohort2);
        this.cohortRepository.save(cohort3);
        log.info("Database seeded Cohorts completed");
    }
    
}
