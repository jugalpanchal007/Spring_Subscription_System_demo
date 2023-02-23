package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enum_class.SubscriptionStatus;
import com.example.demo.model.Subscription;
import com.example.demo.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subscription not found with id " + id));
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Long id, Subscription subscription) {
        Subscription existingSubscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found with id " + id));
        existingSubscription.setEmail(subscription.getEmail());
        existingSubscription.setStartDate(subscription.getStartDate());
        existingSubscription.setEndDate(subscription.getEndDate());
        existingSubscription.setStatus(subscription.getStatus());
        return subscriptionRepository.save(existingSubscription);
    }
    
    public Subscription updateSubscriptionStatus(Long id, SubscriptionStatus status) {
        Subscription subscription = getSubscriptionById(id);
        subscription.setStatus(status);
        return subscriptionRepository.save(subscription);
    }

    //VIEW To All Active Members
    public List<Subscription> getActiveSubscriptions() {
        return subscriptionRepository.findByStatus(SubscriptionStatus.ACTIVE);
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
    
    
    public Optional<Subscription> findActiveSubscriptionByEmail(String email) {
        LocalDate today = LocalDate.now();
        return subscriptionRepository.findActiveSubscriptionByEmail(email, today);
    }

    public boolean isUserSubscribed(String email) {
        return findActiveSubscriptionByEmail(email).isPresent();
    }
    
    //VIEW To All Cancle Members
    public List<Subscription> getCanceledSubscriptions() {
        return subscriptionRepository.findByStatus(SubscriptionStatus.CANCELED);
    }
    
    //VIEW To All Expired plan Members
    public List<Subscription> getExpiredSubscriptions() {
        return subscriptionRepository.findByStatus(SubscriptionStatus.EXPIRED);
    }
}
