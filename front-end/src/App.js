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
import SingleProjectPage from "./pages/SingleProjectPage";
import SingleTaskPage from './pages/SingleTaskPage';

function App() {
  return (
    <div className='App'>
      <Router>
        <div>
          <Route path={["/MyProfile", "/Projects", "/Tasks", "/SingleProjectPage", "/SingleTaskPage", "/home"]}>
            <NavBar />
          </Route>

          <Switch>
            <Route exact path={['/', '/home']} component={Home} />
            <Route exact path='/login' component={Login} />
            <Route exact path='/register' component={RegisterPage} />
            <Route exact path='/MyProfile/:employeeID' component={MyProfile} />
            <Route exact path='/Projects' component={Projects} />
            <Route exact path='/Tasks' component={Tasks} />
            <Route exact path='/SingleProjectPage/:projectId' component={SingleProjectPage} />
            <Route exact path='/SingleTaskPage/:taskId' component={SingleTaskPage} />
            <Route component={NoMatch} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
