package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.enum_class.SubscriptionStatus;
import com.example.demo.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s WHERE s.email = :email AND :today BETWEEN s.startDate AND s.endDate AND s.status = 'ACTIVE'")
    Optional<Subscription> findActiveSubscriptionByEmail(@Param("email") String email, @Param("today") LocalDate today);
    
    //it will give a status of subscription plan which we want to implement
    List<Subscription> findByStatus(SubscriptionStatus status);

}