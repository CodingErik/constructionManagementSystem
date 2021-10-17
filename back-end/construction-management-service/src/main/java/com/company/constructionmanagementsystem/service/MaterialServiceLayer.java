package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Material;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class MaterialServiceLayer {
    MaterialRepository materialRepository;
    MaterialWarehouseClient materialWarehouseClient;

    @Autowired
    public MaterialServiceLayer(MaterialRepository materialRepository, MaterialWarehouseClient materialWarehouseClient) {
        this.materialRepository = materialRepository;
        this.materialWarehouseClient = materialWarehouseClient;
    }

    public String requestMaterials(Material material) {

        try {
            materialWarehouseClient.updateMaterialAfterRetrieve(material);
            Material currentProjectMaterials = materialRepository.findByProjectId(material.getProjectId()).get();
            Material finalProjectMaterials = new Material();
            finalProjectMaterials.setBrick(currentProjectMaterials.getBrick() + material.getBrick());
            finalProjectMaterials.setCement(currentProjectMaterials.getCement() + material.getCement());
            finalProjectMaterials.setLumber(currentProjectMaterials.getLumber() + material.getLumber());
            finalProjectMaterials.setSteel(currentProjectMaterials.getSteel() + material.getSteel());
            finalProjectMaterials.setProjectId(material.getProjectId());
            finalProjectMaterials.setId(material.getId());

            materialRepository.save(finalProjectMaterials);
            return "the following material was added to the project " + material;
        } catch (FeignException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
