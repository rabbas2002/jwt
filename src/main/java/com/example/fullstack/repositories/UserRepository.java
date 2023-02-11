package com.example.fullstack.repositories;

import com.example.fullstack.UserStatus;
import com.example.fullstack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE User SET userStatus =:status  WHERE id=:userId")
    public void changeUserStatus(@Param("userId")Long userId, @Param("status") UserStatus status);

    User findByBusinessName(String userName);


}
