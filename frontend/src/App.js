import React from 'react';
import logo from './logo.svg';
import './App.css';
import Map from './components/Map'
import Navbar from './components/Navbar'

import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

function App() {
    return ( <Router >
                <Switch>
                <Route exact path = "/" >
                    <Map />
                </Route>
                <Route exact path = "/navbar">
                    <Navbar />    
                </Route> 
                </Switch> 
         </Router>
    );
}

export default App;