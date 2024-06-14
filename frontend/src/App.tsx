import React from 'react';

import Navbar from './components/Navbar';
import SideBar from './components/Sidebar';
const App: React.FC = () => (
  <div>
    <Navbar />
    <SideBar />
    {/* Các component khác */}
  </div>
);

export default App;
