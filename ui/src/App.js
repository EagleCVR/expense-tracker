// 📁 src/App.js

/**
 * ✅ INTERVIEW READY COMMENT:
 * This is the main React component of the Expense Tracker app.
 * It handles:
 * - Fetching all expenses from the backend using Axios.
 * - Rendering a list of expenses.
 * - Integrating the ExpenseForm to create or update expenses.
 * - Deleting expenses.
 * React Hooks (`useState`, `useEffect`) are used for state and side effects.
 * Axios is used via a custom instance for clean API communication.
 */

import React, { useState, useEffect } from 'react';
import ExpenseForm from './components/ExpenseForm';
import api from './api';

const App = () => {
  const [expenses, setExpenses] = useState([]); // All expense records
  const [editingExpense, setEditingExpense] = useState(null); // Currently edited expense

  // 🔁 Fetch expenses from backend on component mount
  const fetchExpenses = async () => {
    try {
      const response = await api.get('/');
      setExpenses(response.data); // Set data from response
    } catch (error) {
      console.error('❌ Failed to fetch expenses:', error);
    }
  };

  useEffect(() => {
    fetchExpenses(); // Fetch once component loads
  }, []);

  // 🧹 Clear editing mode
  const clearEditing = () => setEditingExpense(null);

  // ❌ Delete expense by ID
  const handleDelete = async (id) => {
    try {
      await api.delete(`/${id}`);
      fetchExpenses(); // Refresh after deletion
    } catch (error) {
      console.error('❌ Failed to delete:', error);
    }
  };

  // 🖊️ Start editing mode
  const handleEdit = (expense) => {
    setEditingExpense(expense);
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>💰 Expense Tracker</h1>

      <ExpenseForm
        editingExpense={editingExpense}
        onSave={fetchExpenses}
        onClear={clearEditing}
      />

      <h2>📋 All Expenses</h2>
      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Amount</th>
            <th>Category</th>
            <th>Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map((expense) => (
            <tr key={expense.id}>
              <td>{expense.id}</td>
              <td>{expense.description}</td>
              <td>{expense.amount}</td>
              <td>{expense.category}</td>
              <td>{expense.date}</td>
              <td>
                <button onClick={() => handleEdit(expense)}>✏️ Edit</button>{' '}
                <button onClick={() => handleDelete(expense.id)}>🗑️ Delete</button>
              </td>
            </tr>
          ))}
          {expenses.length === 0 && (
            <tr>
              <td colSpan="6">No expenses found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default App;
