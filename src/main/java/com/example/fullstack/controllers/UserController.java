package com.example.fullstack.controllers;

import com.example.fullstack.config.JwtTokenCreator;
import com.example.fullstack.config.JwtTokenValidator;
import com.example.fullstack.model.OfficeBearer;
import com.example.fullstack.model.Product;
import com.example.fullstack.model.Request;
import com.example.fullstack.model.User;
import com.example.fullstack.services.OfficeBearerService;
import com.example.fullstack.services.ProductService;
import com.example.fullstack.services.RequestService;
import com.example.fullstack.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin"})
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OfficeBearerService officeBearerService;
    @Autowired
    private RequestService requestService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("login")
    public String loginUser(){
        return "Successfully logged in by user: "+ SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @PostMapping("register")
    public Request registerRequest(@RequestBody Request request){
        return requestService.sendRequest(request);

    }
    @PostMapping("admin/addProduct")
    public Product addProducts(@RequestBody Product product){
        return productService.addProducts(product);
    }

    @PutMapping("user/{userId}/edit")
    public User editUserDetails(@PathVariable Long userId,@RequestBody User user){
        return userService.editUser(userId,user);
    }
    @PostMapping("admin/addOfficeBearer")
    public OfficeBearer addOfficeBearer(@RequestBody OfficeBearer officeBearer){
        return officeBearerService.addOfficeBearer(officeBearer);
    }
    @GetMapping({"user/officeBearers","admin/officeBearers"})
    public List<OfficeBearer>getAllOfficeBearers(){
        return officeBearerService.getAllOfficeBearers();
    }
    @GetMapping({"user/products","admin/products"})
    public List<Product>getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("user")
    public User getUser(Principal principal){
        return userService.getUserByName(principal.getName());
    }
    @DeleteMapping("admin/decline/{userId}")
    public void declineRequest(@PathVariable Long userId){
        requestService.declineRequest(userId);

    }
    @GetMapping("admin/approve/{requestId}")
    public User addUser(@PathVariable  Long requestId){
        return userService.createUser(requestId);
    }

    @GetMapping("/admin/requests")
    public List<Request>getRequests(){
        return requestService.getRequests();
    }
    @GetMapping("/admin/users")
    public List<User>getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/admin/blockUser/{id}")
    public void blockUser(@PathVariable Long id){
        userService.blockUser(id);
    }
    @GetMapping("/admin/unblockUser/{id}")
    public void unblockUser(@PathVariable Long id){
        userService.unblockUser(id);
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Inside refresh token...");
        JwtTokenCreator generator = new JwtTokenCreator();
        JwtTokenValidator validation = new JwtTokenValidator();
        validation.validateJwtToken( request, response, true);
        generator.generateToken( request, response);
    }



}
