package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Subscription;
import com.example.demo.service.SubscriptionService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final SubscriptionService subscriptionService;

    public MemberController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    //VIEW To All Active Members
    @GetMapping("/onlySubscribed")
    public ResponseEntity<List<Subscription>> getSubscribedMembers() {
        List<Subscription> subscriptions = subscriptionService.getActiveSubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
    
    //VIEW To All Cancle Members
    @GetMapping("/onlyCanceled")
    public ResponseEntity<List<Subscription>> getCancledMembers() {
        List<Subscription> subscriptions = subscriptionService.getCanceledSubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
    
    //VIEW To All Expired plan Members
    @GetMapping("/onlyExpired")
    public ResponseEntity<List<Subscription>> getExpiredMembers() {
        List<Subscription> subscriptions = subscriptionService.getExpiredSubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}

