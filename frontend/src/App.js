import React from 'react';
import logo from './logo.svg';
import './App.css';
import Map from './components/Map'
import Navbar from './components/Navbar'
import Statistics from './components/Statistics'
import BusStops from './components/BusStops'

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
                <Route exact path = "/busStops">
                    <Statistics/>
                </Route>
                <Route exact path = "/statistics">
                    <BusStops/>
                </Route>
                </Switch> 
         </Router>
    );
}

export default App;