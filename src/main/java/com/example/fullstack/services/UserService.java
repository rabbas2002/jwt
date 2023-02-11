package com.example.fullstack.services;

import com.example.fullstack.UserStatus;
import com.example.fullstack.model.Request;
import com.example.fullstack.model.User;
import com.example.fullstack.repositories.RequestRepository;
import com.example.fullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestRepository requestRepository;


    public User createUser(Long requestId){
        Request request = requestRepository.findById(requestId).get();
        User user = new User();
        user.setUserStatus(UserStatus.ACTIVE);
        user.setPhoneNumber(request.getPhoneNumber());
        user.setContactPerson(request.getContactPerson());
        user.setDrugLicense(request.getDrugLicense());
        user.setBusinessName(request.getBusinessName());
        user.setGst(request.getGst());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
        requestRepository.deleteById(requestId);
        return userRepository.save(user);
    }
    public User createUser(User user){
        User user1 = new User();
        user1.setBusinessName(user.getBusinessName());
        user1.setUserStatus(user.getUserStatus());
        user1.setGst(user.getGst());
        user1.setContactPerson(user.getContactPerson());
        user1.setDrugLicense(user.getDrugLicense());
        user1.setPhoneNumber(user.getPhoneNumber());
//        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole());
        user1.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(user1);
    }
    private static boolean hasEdited(String newProperty){
        if(newProperty!=null && newProperty.length()!=0){
            return true;
        }
        return false;
    }

    public User loginUser(User loginUser){
        User user = userRepository.findByBusinessName(loginUser.getBusinessName());
        if(user!=null){
            System.out.println(user+" "+loginUser);
            if(user.getPassword().equals(loginUser.getPassword())){
                return user;
            }
        }
            return null;
    }
    public User editUser(Long userId,User editedUser){
       User existingUser =  userRepository.findById(userId).orElse(null);
       String newContactPerson = editedUser.getContactPerson();
       String newGST = editedUser.getGst();
       String newBusinessName = editedUser.getBusinessName();
       String newDrugLicense = editedUser.getDrugLicense();
       String newPhoneNumber = editedUser.getPhoneNumber();
       if(hasEdited(newContactPerson)){
           existingUser.setContactPerson(newContactPerson);
       }
       if(hasEdited(newGST)){
            existingUser.setGst(newGST);
       }
       if(hasEdited(newBusinessName)){
            existingUser.setBusinessName(newBusinessName);
       }
       if(hasEdited(newDrugLicense)){
            existingUser.setDrugLicense(newDrugLicense);
       }
       if(hasEdited(newPhoneNumber)){
            existingUser.setPhoneNumber(newPhoneNumber);
       }
       return userRepository.save(existingUser);

    }
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    public User getUserByName(String name){
        return userRepository.findByBusinessName(name);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void blockUser(Long userId){
       userRepository.changeUserStatus(userId,UserStatus.BLOCKED);
    }
    public void unblockUser(Long userId){
        userRepository.changeUserStatus(userId,UserStatus.ACTIVE);
    }
}
