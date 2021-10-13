package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.ProjectMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectMaterialsRepository extends JpaRepository<ProjectMaterials, Integer> {
    Optional<ProjectMaterials> findByProjectId(Integer projectId);
}
