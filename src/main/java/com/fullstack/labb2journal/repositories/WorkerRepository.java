package com.fullstack.labb2journal.repositories;


import com.fullstack.labb2journal.entitys.User;
import com.fullstack.labb2journal.entitys.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByUser(User user);

}
