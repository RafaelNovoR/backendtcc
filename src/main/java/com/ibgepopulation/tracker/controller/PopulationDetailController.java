package com.ibgepopulation.tracker.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/population-detail")
@CrossOrigin(origins = {"http://localhost:3000", "https://fronendtcc-s9yd.vercel.app"})
@RequiredArgsConstructor
public class PopulationDetailController {

    @GetMapping("/getPessoaByName")
    public ResponseEntity<PessoaPOJO> getPessoaByName(@RequestParam String name) {
        PessoaPOJO pessoa = new PessoaPOJO(); // Replace with actual logic to find Pessoa by name
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("getEmpByName")
    public Employee getEmpByName(@RequestParam String name) {
        return new Employee();
    }

}

@Data
class PessoaPOJO {
    String name;
    LocalDate idade;
    String email;

    void Pessoa() {
        this.name = "";
        this.idade = null;
        this.email = "";
    }
}

interface Manager {
    void printManagerRoles();
}

@Data
class Employee extends PessoaPOJO implements Manager {
    double salary;
    String empCode;

    // Constructor
    public Employee() {
        // Initialize example data (optional)
        this.name = "John Doe";
        this.idade = LocalDate.of(1985, 5, 20);
        this.email = "employee@example.com";
        this.salary = 55000.00;
        this.empCode = "EMP12345";
        
        // Print manager roles upon creation
        printManagerRoles();
    }

    // Implementing the Manager interface method
    @Override
    public void printManagerRoles() {
        System.out.println("Manager Roles:");
        System.out.println("1. Oversee team activities");
        System.out.println("2. Delegate tasks to team members");
        System.out.println("3. Ensure project deadlines are met");
        System.out.println("4. Provide feedback and support to the team");
    }
}
