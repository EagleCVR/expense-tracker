/*"ExpenseRepository extends JpaRepository, giving us all the common CRUD methods out 
of the box. We don't need to write any implementation — Spring Data JPA handles it for 
us at runtime. The findByCategory() method is an example of a derived query — 
Spring builds the SQL based on the method name."
 */

package com.vaibhav.expensetracker.repository;
// This package declaration must match the folder path exactly

import com.vaibhav.expensetracker.model.Expense;
// Importing the Expense entity — this is the data model we want to access from the database

import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository provides all built-in CRUD methods like findAll(), save(), deleteById(), etc.

import org.springframework.stereotype.Repository;
// Optional but good for clarity: marks this interface as a Spring-managed repository bean

import java.util.List;
// Required for the custom finder method that returns a list of expenses

/**
 * ✅ This interface acts as a bridge between our application and the database.
 * Spring Data JPA will automatically create the implementation at runtime.
 */
@Repository // Marks this interface as a Repository component for Spring to scan and register
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    // ✅ Custom finder method — Spring will auto-generate the SQL query
    List<Expense> findByCategory(String category);

    // 🔁 You can add more methods like:
    // List<Expense> findByName(String name);
    // List<Expense> findByAmountGreaterThan(Double amount);
}
