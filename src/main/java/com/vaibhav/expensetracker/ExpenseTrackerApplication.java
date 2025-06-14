// Define the package structure this class belongs to.
// Helps organize your code and avoid class name conflicts.
package com.vaibhav.expensetracker;

// Import Spring Boot’s main class for launching the application.
import org.springframework.boot.SpringApplication;

// Import the annotation that marks this as a Spring Boot application.
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point of the Expense Tracker application.
 *
 * @SpringBootApplication is a convenience annotation that does three things:
 *  1. @Configuration - Marks this class as a source of bean definitions.
 *  2. @EnableAutoConfiguration - Enables Spring Boot’s automatic configuration.
 *  3. @ComponentScan - Tells Spring to scan this package and subpackages for components.
 */
@SpringBootApplication
public class ExpenseTrackerApplication {

    /**
     * The main() method is the first method that gets called when the application starts.
     * SpringApplication.run(...) boots the Spring application, creates the application context,
     * sets up the embedded server (like Tomcat), and runs everything.
     *
     * @param args Command-line arguments (not commonly used in most Spring Boot apps)
     */
    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }
}
