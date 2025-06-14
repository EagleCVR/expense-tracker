// 📁 src/components/ExpenseForm.js

/**
 * ✅ INTERVIEW READY COMMENT:
 * This React component serves as a reusable form to CREATE (POST) and UPDATE (PUT) expenses.
 * It uses controlled components via React state to bind form values.
 * The form handles both "add" and "edit" based on whether `editingExpense` prop is set.
 * Axios is used via a centralized API instance for backend communication.
 */

import React, { useState, useEffect } from 'react';
import api from '../api'; // Axios instance

const ExpenseForm = ({ editingExpense, onSave, onClear }) => {
  // Local form state
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState('');
  const [category, setCategory] = useState('');
  const [date, setDate] = useState('');

  // Fill form when editing starts
  useEffect(() => {
    if (editingExpense) {
      setDescription(editingExpense.description);
      setAmount(editingExpense.amount);
      setCategory(editingExpense.category);
      setDate(editingExpense.date);
    }
  }, [editingExpense]);

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const expenseData = {
      description,
      amount: parseFloat(amount),
      category,
      date,
    };

    try {
      if (editingExpense) {
        // PUT - Update
        await api.put(`/${editingExpense.id}`, expenseData);
      } else {
        // POST - Create
        await api.post('/', expenseData);
      }

      // Reset form
      setDescription('');
      setAmount('');
      setCategory('');
      setDate('');

      // Notify parent to reload
      onSave();
    } catch (error) {
      console.error('❌ Error saving expense:', error);
    }
  };

  return (
    <div>
      <h2>{editingExpense ? '✏️ Edit Expense' : '➕ Add New Expense'}</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Description"
          value={description}
          required
          onChange={(e) => setDescription(e.target.value)}
        />
        <input
          type="number"
          placeholder="Amount"
          value={amount}
          required
          onChange={(e) => setAmount(e.target.value)}
        />
        <input
          type="text"
          placeholder="Category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        />
        <input
          type="date"
          placeholder="Date"
          value={date}
          onChange={(e) => setDate(e.target.value)}
        />

        <button type="submit">{editingExpense ? 'Update' : 'Add'}</button>
        {editingExpense && (
          <button type="button" onClick={onClear}>
            Cancel
          </button>
        )}
      </form>
    </div>
  );
};

export default ExpenseForm;
