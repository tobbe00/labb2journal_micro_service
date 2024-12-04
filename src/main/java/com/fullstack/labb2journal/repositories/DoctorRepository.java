package com.fullstack.labb2journal.repositories;

import com.fullstack.labb2journal.entitys.Doctor;
import com.fullstack.labb2journal.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    boolean existsByUser(User user);

    Doctor findByUser(User user);


}
