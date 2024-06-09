import React from 'react';

import Navbar from './components/navbar';
import SideBar from './components/sidebar';
const App: React.FC = () => (
  <div>
    <Navbar />
    <SideBar />
    {/* Các component khác */}
  </div>
);

export default App;
