import React from 'react'
import { Component } from "react";
import busStops  from "./busStops.json";
import Navbar from './Navbar' 
import * as ReactBootStrap from "react-bootstrap"; 
import Table from 'react-bootstrap/Table'
class Statistics extends Component{
    constructor(){
        super();
        this.state={
            loading: true,
            data : []
        };
    }
    componentDidMount(){
        fetch('')
        .then(response => response.json())
        .then(data => this.setState({ data : data, loading:false}));  
        
    }

    render(){
        return(
        <div>
            <Navbar/>
            <h1 style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>Buses Stops</h1>
            <br></br>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                        <Table style={{marginTop:'-15px',width:"80%", alignSelf:"center"}}>
                            <tr>
                                <th>id</th>
                                <th>origem</th>
                                <th>destino</th>
                                <th>linha</th>
                                <th>paragens</th>
                                <th>zona</th>
                            </tr>
                            {busStops.map(c=>
                                <tr>
                                    <th>{c.id}</th>
                                    <th>{c.origem}</th>
                                    <th>{c.destino}</th>
                                    <th>{c.linha}</th>
                                    <th>{c.paragens}</th>
                                    <th>{c.zona}</th>
                                </tr>
                            )}
                        </Table>
                    </div>
        </div>
        )
        
    }

}

export default Statistics;