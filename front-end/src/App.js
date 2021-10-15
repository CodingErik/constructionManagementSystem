import React, { useEffect, useState, createContext, useReducer } from 'react';
import NavBar from './components/NavBar';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import Home from './pages/Home';
import Login from './pages/Login';
import RegisterPage from './pages/RegisterPage';
import MyProfile from './pages/MyProfile';
import Projects from './pages/Projects';
import Tasks from './pages/Tasks';
import NoMatch from './pages/NoMatch';
import SingleProjectPage from './pages/SingleProjectPage';
import SingleTaskPage from './pages/SingleTaskPage';
import AdminSingleEmployeePage from './pages/AdminSingleEmployeeEditPage';
import AdminAllEmployeeViewPage from './pages/AdminAllEmployeeViewPage';

function App() {
  return (
    <div className='App'>
      <Router>
        <div>
          <Route
            path={[
              '/MyProfile',
              '/Projects',
              '/Tasks',
              '/SingleProjectPage',
              '/SingleTaskPage',
              '/home',
              '/AdminSingleEmployeePage',
              '/AdminAllEmployeeViewPage'
            ]}
          >
            <NavBar />
          </Route>

          <Switch>
            <Route exact path='/home' component={Home} />
            <Route exact path='/register' component={RegisterPage} />
            <Route exact path={['/', '/login']} component={Login} />
            <Route exact path='/MyProfile' component={MyProfile} />
            <Route exact path='/Projects' component={Projects} />
            <Route exact path='/Tasks' component={Tasks} />
            <Route exact path='/SingleProjectPage/:projectId' component={SingleProjectPage} />
            <Route exact path='/SingleTaskPage/:taskId' component={SingleTaskPage} />
            <Route exact path='/AdminSingleEmployeePage/:employeeId' component={AdminSingleEmployeePage} />
            <Route exact path='/AdminAllEmployeeViewPage' component={AdminAllEmployeeViewPage} />
            <Route component={NoMatch} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
