package com.riwi.beautySalon.domain.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riwi.beautySalon.domain.entities.Appointment;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    @Query(value = "select a from appointment a join fetch a.client c where c.id = :idClient")
    Optional<Appointment> findByClientId(Long idClient);

    @Query("SELECT COUNT(a) FROM appointment a WHERE a.employee.id = :employeeId AND a.dateTime = :dateTime")
    public Long countByEmployeeIdAndDateTime(@Param("employeeId") Long employeeId, @Param("dateTime") LocalDateTime dateTime);
}
