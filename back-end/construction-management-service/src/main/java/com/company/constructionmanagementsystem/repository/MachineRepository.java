package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface MachineRepository extends JpaRepository<Machine, Integer> {
    Optional<Machine> findByProjectId(Integer id);
    void deleteMachineByProjectId (Integer projectId);

}
