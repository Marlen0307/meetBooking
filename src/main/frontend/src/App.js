import React from 'react';
import './App.css';
import Header from './components/Header';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListStudentsComponent from './components/ListStudentsComponent';
import AddStudent from './components/AddStudent';

function App() {
  return (
    <div >
      <Router>
          <Header/>
            <div className="container">
                <Switch>
                <Route exact path = "/" component = {ListStudentsComponent}></Route>
                <Route path = "/addStudent/:studentId" component = {AddStudent}></Route>
                {/* <Route path = "/updateStudent/:studentId" component = {UpdateStudent}></Route> */}
                </Switch>
            </div>
      </Router>   
    </div>

  );
}

export default App;
