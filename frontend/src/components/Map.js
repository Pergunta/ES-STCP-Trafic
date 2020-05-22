import React, { Component } from 'react';
import maxBounds from './MyOverlay';
import ReactMapGL, { Marker, Popup } from 'react-map-gl';
import 'mapbox-gl/dist/mapbox-gl.css';

var bounds = [
  [41.159925, -8.686844], // Southwest coordinates
  [41.162768, -8.560501] // Northeast coordinates
];

function baseClamp(number, lower, upper) {
  if (number === number) {
    if (upper !== undefined) {
      number = number <= upper ? number : upper;
    }
    if (lower !== undefined) {
      number = number >= lower ? number : lower;
    }
  }
  return number;
}

class Map extends Component {

  token = 'pk.eyJ1Ijoic2Fwb2tva2FzIiwiYSI6ImNrOTA2bXdhMTB1dGIzZnMycjBlc3JxeXcifQ.Sk-_m4_pM8lUX-wyMZh04g';
  
  state = {
    viewport: {
      width: "100vw",
      height: "100vh",
      latitude: 41.162216,
      longitude: -8.62517881,
      zoom: 10
    },
    loading: true,
    data: [],
    selectedBus: null

  };

  componentDidMount() {
    this.interval = setInterval( () =>  fetch("http://192.168.160.103:6080/api/all")
       .then(response => response.json())
       .then(data => this.setState({ data: data, loading: false }))
      , 1000)
 
  }

  componentDidUnmount() {
    
    clearInterval(this.interval)
  }
  render() {
    return (
      /**<div>
         {this.state.data.length}
      </div>*/
      <div >
        <ReactMapGL  {...this.state.viewport}
          maxBounds={bounds}
          mapboxApiAccessToken={this.token}
          onViewportChange={(viewport) => {
            console.log(viewport)
            
            this.setState({
              viewport : {
                ...viewport,
                latitude: baseClamp(viewport.latitude, 41.159925, 41.162768),
                longitude: baseClamp(viewport.longitude, -8.686844, -8.560501)
              }
            });

          }}
        >
          {this.state.data.map((c) => (
            <Marker
              key={c.id}
              latitude={c.lat}
              longitude={c.lon}
            >
              <button
                className="marker-btn"
                onClick={e => {
                  e.preventDefault()
                  /*    console.log(e.target) */
                  this.setState({ selectedBus: c })
                }}
              >
                <img src="bus.png" alt="Bus Icon" />
              </button>
            </Marker>
          ))}

          {this.state.selectedBus ? (
            <Popup
              latitude={this.state.selectedBus.lat}
              longitude={this.state.selectedBus.lon}
              onClose={() => {
                this.setState({ selectedBus: null });
              }}
            >
              <div>
                <p>{this.state.selectedBus.nodeId}</p>
                <p>{this.state.selectedBus.speed}</p>
              </div>
            </Popup>
          ) : null}
        </ReactMapGL>
      </div>

    )
  }



}

export default Map;