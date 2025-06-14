// 📄 ExpenseList.js
// ✅ Interview-Ready Explanation:
// This React component fetches a list of expenses from a backend REST API built with Spring Boot.
// It uses React Hooks (useState, useEffect) to manage state and side effects.
// Commonly asked interview points:
// 1. useEffect is used for data fetching after the initial render (componentDidMount behavior).
// 2. fetch is a native JavaScript API used to make HTTP requests.
// 3. We use conditional rendering and map() to dynamically display the list.
// 4. This is a functional component — preferred in modern React with Hooks.
// 5. Real-world projects would use axios and a dedicated API service file (can be discussed in interviews).

// 📁 src/components/ExpenseList.js

/**
 * ✅ INTERVIEW READY COMMENT:
 * This component is responsible for reading (GET) and deleting (DELETE) expenses using our backend API.
 * It uses React hooks (`useEffect`, `useState`) to manage data and side-effects.
 * Separation of concerns is maintained by keeping display and data-fetch logic modular.
 */

import React, { useEffect, useState } from 'react';
import api from '../api'; // Axios instance

const ExpenseList = ({ onEdit }) => {
  // Local state to hold list of expenses
  const [expenses, setExpenses] = useState([]);

  // Runs once when the component is mounted
  useEffect(() => {
    fetchExpenses(); // Fetch all expenses from backend
  }, []);

  // Function to fetch expenses
  const fetchExpenses = async () => {
    try {
      const response = await api.get('/');
      setExpenses(response.data); // Store API response in state
    } catch (error) {
      console.error('❌ Failed to fetch expenses:', error);
    }
  };

  // Function to handle deletion
  const deleteExpense = async (id) => {
    try {
      await api.delete(`/${id}`);
      setExpenses(expenses.filter(exp => exp.id !== id)); // Remove deleted item from UI
    } catch (error) {
      console.error('❌ Failed to delete expense:', error);
    }
  };

  return (
    <div>
      <h2>💰 Expenses</h2>
      {expenses.length === 0 ? (
        <p>No expenses found.</p>
      ) : (
        <ul>
          {expenses.map((expense) => (
            <li key={expense.id}>
              <strong>{expense.description}</strong> - ₹{expense.amount} ({expense.category}) on {expense.date}
              <button onClick={() => onEdit(expense)}>✏️ Edit</button>
              <button onClick={() => deleteExpense(expense.id)}>🗑️ Delete</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ExpenseList;