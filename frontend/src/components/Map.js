import React, { Component}  from 'react';
import maxBounds from './MyOverlay';
import ReactMapGL, {Marker , Popup} from 'react-map-gl';
import 'mapbox-gl/dist/mapbox-gl.css';


class Map extends Component{
    
     token = 'pk.eyJ1Ijoic2Fwb2tva2FzIiwiYSI6ImNrOTA2bXdhMTB1dGIzZnMycjBlc3JxeXcifQ.Sk-_m4_pM8lUX-wyMZh04g' ;
     
      state = {
        viewport: {
          width: "100vw",
          height: "100vh",
          latitude: 41.162216,
          longitude: -8.62517881,
          zoom: 10
        },
        loading : true,
        data : [],
        selectedBus : null
      };

    componentDidMount(){
        fetch('http://localhost:8080/api/all')
        .then(response => response.json())
        .then(data => this.setState({ data : data, loading:false}));  
        
    }
  
    render(){        
        return (
            /**<div>
               {this.state.data.length}
            </div>*/
              <div >
                <ReactMapGL  {...this.state.viewport} 
                mapboxApiAccessToken = {this.token}
                onViewportChange={(viewport) =>{

                  this.setState( {
                    viewport: { ...this.state.viewport, ...viewport }
                  } );

                  this.setState({viewport})
 
                }}
                >
                  {this.state.data.map((c) => (
                    <Marker 
                      key = {c.id}
                      latitude ={c.lat} 
                      longitude = {c.lon} 
                    >
                    <button 
                      className = "marker-btn"
                      onClick = {e => {
                        e.preventDefault()
                     /*    console.log(e.target) */
                        this.setState({selectedBus: c})}} 
                    >
                      <img src ="bus.png" alt="Bus Icon"/>
                    </button>
                    </Marker>
                  ))}

                 {this.state.selectedBus ? (
                 <Popup 
                  latitude = {this.state.selectedBus.lat}
                  longitude = {this.state.selectedBus.lon}
                  onClose = {() =>{
                    this.setState({selectedBus : null});  
                  }}
                 > 
                   <div>
                      <p>{this.state.selectedBus.nodeId}</p>
                      <p>{this.state.selectedBus.speed}</p>
                   </div>
                 </Popup>   
                ):null } 
                </ReactMapGL>
            </div>
        
        )
    }
}

export default Map;