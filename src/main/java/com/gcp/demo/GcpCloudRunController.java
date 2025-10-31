package com.gcp.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GcpCloudRunController {

    @GetMapping()
    public  String hello(){
        return "Welcome to GCP Cloud Run Service - Home Page";
    }

    @GetMapping("/{user}")
    public  String helloUser(@PathVariable String user){
        return "Welcome to GCP Cloud Run Service, " + user;
    }

    @GetMapping("/about")
    public  String about(){
        return "This is a demo service deployed in GCP Cloud Run";
    }
}
