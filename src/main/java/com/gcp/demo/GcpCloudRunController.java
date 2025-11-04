package com.gcp.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GcpCloudRunController {

    private final EmployeeRepository employeeRepository;

    public GcpCloudRunController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Welcome to Google Cloud-run Home Page");
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<?> addNewEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @GetMapping("/employee/name/{name}")
    public ResponseEntity<?> getEmployee(@PathVariable("name") String name){
        return ResponseEntity.ok(employeeRepository.findByNameIgnoreCase(name));
    }

    @GetMapping("/employee/email/{email}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(employeeRepository.findByEmail(email));
    }
}
