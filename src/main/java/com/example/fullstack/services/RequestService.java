package com.example.fullstack.services;

import com.example.fullstack.model.Request;
import com.example.fullstack.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request sendRequest(Request request){
        return requestRepository.save(request);
    }
    public void deleteRequest(Long id){
        requestRepository.deleteById(id);
    }

    public void declineRequest(Long id){
        requestRepository.deleteById(id);
    }


    public List<Request> getRequests(){
        return requestRepository.findAll();
    }
}
