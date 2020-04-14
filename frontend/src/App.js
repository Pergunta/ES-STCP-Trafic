import React, { useState } from 'react';
import ReactMapGL from 'react-map-gl';


const MAPBOX_TOKEN = 'pk.eyJ1Ijoic2Fwb2tva2FzIiwiYSI6ImNrOTA2bXdhMTB1dGIzZnMycjBlc3JxeXcifQ.Sk-_m4_pM8lUX-wyMZh04g';
export default function App(){
    const [viewport, setViewport] = useState({
        width: "100vw",
        height: "100vh",
        latitude: 41.162216,
        longitude: -8.62517881,
        zoom: 10
      });

      return (
          <div>
              <ReactMapGL  {...viewport} 
              mapboxApiAccessToken = {MAPBOX_TOKEN}
              onViewportChange = {viewport =>{
                  setViewport(viewport)
              }}
              >
                Marks here
              </ReactMapGL>
          </div>
      )
}