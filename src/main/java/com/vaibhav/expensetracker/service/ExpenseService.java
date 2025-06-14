/*"The service layer in Spring Boot contains all the business logic. 
It talks to the repository to perform database operations. 
I’ve used constructor-based dependency injection for better testability and clarity. 
The service returns data to the controller in the form of lists or optional types. 
I kept the service clean, with clear separation from the web layer, 
making it easy to maintain and unit test."
 * 
 */

package com.vaibhav.expensetracker.service; 
// This defines the package where this class belongs. 
// It's grouped under 'service' because this is part of the business logic layer.

import com.vaibhav.expensetracker.model.Expense;
// Importing the Expense model (our JPA entity)

import com.vaibhav.expensetracker.repository.ExpenseRepository;
// Importing the interface that handles database operations

import org.springframework.beans.factory.annotation.Autowired;
// Allows Spring to automatically inject the required dependency

import org.springframework.stereotype.Service;
// Marks this class as a service component, managed by the Spring container

import java.util.List;
import java.util.Optional;
// These are Java utility types:
// List = to return multiple expenses
// Optional = to safely return 0 or 1 value without risking NullPointerException

/**
 * ✅ This is the Service class where we handle business logic.
 * It talks to the ExpenseRepository (DB layer) and is used by the Controller (API layer).
 */
@Service // This tells Spring to treat this class as a service bean for dependency injection
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    // Declare a variable for the repository.
    // 'final' ensures it’s only set once — in the constructor.

    /**
     * ✅ Constructor-based dependency injection (recommended by Spring).
     * Spring will call this constructor and provide the ExpenseRepository automatically.
     */
    @Autowired // This is optional in newer Spring versions if only one constructor exists.
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * ✅ Retrieve all expenses from the database.
     * This uses the inherited findAll() method from JpaRepository.
     */
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll(); // Returns List<Expense>
    }

    /**
     * ✅ Retrieve a specific expense by its ID.
     * We return an Optional to handle the case where ID might not exist.
     */
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    /**
     * ✅ Save a new expense OR update an existing one.
     * If 'expense.id' exists, it'll update. If not, it inserts a new one.
     */
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense); // Handles both insert and update
    }

    /**
     * ✅ Delete an expense by its ID.
     * If ID doesn't exist, Spring will throw EmptyResultDataAccessException.
     */
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    /**
     * ✅ Custom query method: find expenses by category.
     * We assume that findByCategory(String) is defined in ExpenseRepository.
     * Spring Data JPA will generate the SQL automatically based on the method name.
     */
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    // 🔁 Alternatives / advanced:
    // - You could define an interface ExpenseService and implement this class from it.
    // - You could also add validation logic here before saving data (e.g., check amount > 0).
}
