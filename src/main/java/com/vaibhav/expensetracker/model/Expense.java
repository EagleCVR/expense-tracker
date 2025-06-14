/*"Expense.java is a JPA entity that models the structure of the expenses table in our
 database. It's the core data structure used to persist and retrieve expense records. 
 I use annotations like @Entity, @Id, and @Column to define database mappings, 
 and Lombok annotations like @Data to eliminate boilerplate code. It's used across the 
 full stack — from REST controllers to the repository layer — to keep the application strongly
  typed and consistent." */

package com.vaibhav.expensetracker.model; // This is the package declaration for organizing the class logically

// ✅ Lombok annotations to reduce boilerplate
import lombok.Data; // Automatically generates getters, setters, toString, equals, and hashCode
import lombok.NoArgsConstructor; // Generates a no-argument constructor
import lombok.AllArgsConstructor; // Generates a constructor with all class fields

// ✅ JPA imports for object-relational mapping (ORM)
import javax.persistence.Entity; // Marks this class as a JPA entity (maps to a table in the DB)
import javax.persistence.Id; // Marks the primary key field
import javax.persistence.GeneratedValue; // Specifies auto-generation of ID values
import javax.persistence.GenerationType; // Strategy for ID generation (like AUTO, IDENTITY, etc.)
import javax.persistence.Column; // Maps a field to a column in the DB table
import javax.persistence.Table; // Optionally sets the table name explicitly

/**
 * ✅ The Expense class represents a single expense entry.
 * It will be stored in the database using Spring Data JPA.
 * Lombok reduces boilerplate and JPA handles persistence.
 */
import java.time.LocalDate;

@Entity // Marks this class as a JPA entity mapped to a DB table
@Table(name = "expenses") // Optional: Set table name explicitly
@Data // Lombok: generates getters, setters, toString(), equals(), etc.
@NoArgsConstructor // Lombok: needed by JPA
@AllArgsConstructor // Lombok: allows easy creation with all fields
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "category")
    private String category;

    @Column(name = "date")
    private LocalDate date; // 📅 Adds the ability to store when expense was made
}
