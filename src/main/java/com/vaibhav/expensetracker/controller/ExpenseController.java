/*
 "The controller is the REST entry point. I used @RestController to expose API endpoints 
 and used constructor-based dependency injection for the service. The controller maps HTTP 
 verbs like GET, POST, PUT, DELETE to service layer methods. I return ResponseEntity to give
  proper HTTP status codes and bodies. The design cleanly separates API logic from business 
 logic."

 This is a classic RESTful controller using Spring Boot.
 Dependency Injection is done using @Autowired.
 It returns standard ResponseEntity to provide correct HTTP status codes.
 The model fields (description, amount, category, date) must match exactly 
 with what we use in the controller.
*/

package com.vaibhav.expensetracker.controller;

import com.vaibhav.expensetracker.model.Expense;
import com.vaibhav.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// This class is a REST controller that will handle all HTTP requests for Expense
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access backend APIs
@RestController
@RequestMapping("/api/expenses") // Base URI for all expense-related APIs
public class ExpenseController {

    @Autowired // Injects the repository dependency automatically
    private ExpenseRepository expenseRepository;

    // ✅ GET all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll(); // Fetches all rows from DB
    }

    // ✅ GET expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return ResponseEntity.ok(optionalExpense.get());
        } else {
            return ResponseEntity.notFound().build(); // 404 if not found
        }
    }

    // ✅ CREATE new expense
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense); // Saves the new expense to DB
    }

    // ✅ UPDATE existing expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            
            // ✅ Update fields. Make sure these match model field names
            expense.setDescription(updatedExpense.getDescription());
            expense.setAmount(updatedExpense.getAmount());
            expense.setCategory(updatedExpense.getCategory());
            expense.setDate(updatedExpense.getDate());

            expenseRepository.save(expense); // Save updated record
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build(); // If not found, return 404
        }
    }

    // ✅ DELETE expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
