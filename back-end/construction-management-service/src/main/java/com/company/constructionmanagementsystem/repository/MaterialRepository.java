package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Optional<Material> findByProjectId(Integer id);
}
