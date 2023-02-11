package com.example.fullstack.repositories;

import com.example.fullstack.model.OfficeBearer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeBearerRepository extends JpaRepository<OfficeBearer,Long> {

}
