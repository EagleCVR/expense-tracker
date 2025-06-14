// 📁 src/api.js

/**
 * ✅ INTERVIEW NOTE:
 * This file sets up a pre-configured Axios instance to handle API communication.
 * Having a separate file for API abstraction follows the separation of concerns principle.
 */

import axios from 'axios';

// Create Axios instance
const api = axios.create({
  baseURL: 'http://localhost:8080/api/expenses', // Backend base URL
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
