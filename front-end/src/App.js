import logo from './logo.svg';
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

function App() {
  return (
    <div className='App'>
      <Router>
        <div>
          <NavBar />
          <Switch>
            <Route exact path={['/', '/home']} component={Home} />
            <Route exact path='/login' component={Login} />
            <Route exact path='/register' component={RegisterPage} />
            <Route exact path='/MyProfile' component={MyProfile} />
            <Route exact path='/Projects' component={Projects} />
            <Route exact path='/Tasks' component={Tasks} />
            <Route component={NoMatch} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
