package com.example.fullstack.services;

import com.example.fullstack.model.OfficeBearer;
import com.example.fullstack.repositories.OfficeBearerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeBearerService {
    @Autowired
    private OfficeBearerRepository officeBearerRepository;
    public List<OfficeBearer> getAllOfficeBearers(){
        return officeBearerRepository.findAll();
    }
    public OfficeBearer addOfficeBearer(OfficeBearer officeBearer){
        return officeBearerRepository.save(officeBearer);
    }
}
