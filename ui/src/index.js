// 📁 src/index.js

/**
 * ✅ INTERVIEW READY COMMENT:
 * This is the entry point of the React application.
 * - It renders the <App /> component into the root DOM node.
 * - ReactDOM.createRoot (introduced in React 18) replaces ReactDOM.render.
 * - This file bootstraps the entire app.
 */

import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

// 👇 This is where your app is mounted to the root div in public/index.html
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
