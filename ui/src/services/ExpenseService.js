import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/expenses';

export const getExpenses = () => axios.get(API_BASE_URL);
export const getExpenseById = (id) => axios.get(`${API_BASE_URL}/${id}`);
export const createExpense = (expense) => axios.post(API_BASE_URL, expense);
export const updateExpense = (id, expense) => axios.put(`${API_BASE_URL}/${id}`, expense);
export const deleteExpense = (id) => axios.delete(`${API_BASE_URL}/${id}`);

