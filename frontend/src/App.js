import React from 'react';
import logo from './logo.svg';
import './App.css';
import Map from './components/Map'

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
                </Switch> 
         </Router>
    );
}

export default App;