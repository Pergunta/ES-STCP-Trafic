import React from 'react'
import { Component } from "react";
import * as ReactBootStrap from "react-bootstrap"; 
import logo  from "./LogoMakr_6OXV00.png";
import './Navbar.css'
class Navbar extends Component{

    render(){
        return(
            <div>
                
            <ReactBootStrap.Navbar collapseOnSelect expand="lg" bg="primary" variant="dark">
  <ReactBootStrap.Navbar.Brand href="/" > 
  
    <ReactBootStrap.Navbar.Toggle aria-controls="responsive-navbar-nav" />
    
  <ReactBootStrap.Navbar.Collapse id="responsive-navbar-nav">
    <ReactBootStrap.Nav className="mr-auto">
        <img class="image_img" src={logo}/>
      <ReactBootStrap.Nav.Link href="/">Map</ReactBootStrap.Nav.Link>
      <ReactBootStrap.Nav.Link href="/busStops">Buses Stops</ReactBootStrap.Nav.Link>
      <ReactBootStrap.Nav.Link href="/statistics">Statistics</ReactBootStrap.Nav.Link>
      {/* <ReactBootStrap.NavDropdown title="Dropdown" id="collasible-nav-dropdown">
        <ReactBootStrap.NavDropdown.Item href="#action/3.1">Action</ReactBootStrap.NavDropdown.Item>
        <ReactBootStrap.NavDropdown.Item href="#action/3.2">Another action</ReactBootStrap.NavDropdown.Item>
        <ReactBootStrap.NavDropdown.Item href="#action/3.3">Something</ReactBootStrap.NavDropdown.Item>
        <ReactBootStrap.NavDropdown.Divider />
        <ReactBootStrap.NavDropdown.Item href="#action/3.4">Separated link</ReactBootStrap.NavDropdown.Item>
      </ReactBootStrap.NavDropdown> */}
    </ReactBootStrap.Nav>
    {/* <ReactBootStrap.Nav>
      <ReactBootStrap.Nav.Link href="#deets">More deets</ReactBootStrap.Nav.Link>
      <ReactBootStrap.Nav.Link eventKey={2} href="#memes">
        Dank memes
      </ReactBootStrap.Nav.Link>
    </ReactBootStrap.Nav> */}
    
  </ReactBootStrap.Navbar.Collapse>
         
  </ReactBootStrap.Navbar.Brand>
  
</ReactBootStrap.Navbar>
</div>
        )
        
    }

}

export default Navbar;