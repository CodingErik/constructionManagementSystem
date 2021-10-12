package com.example.repositoty;

import com.example.model.Machinery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineryRepository extends JpaRepository<Machinery, Integer> {

}
