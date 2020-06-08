import React, { Component } from "react";
import CanvasJSReact from "../canvasjs.react";
import Navbar from './Navbar'
// var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;


var dataPoints =[];
class BusStops extends Component {
 
	render() {	
        
		const options = {
			animationEnabled: true,
			theme: "light2",
			title: {
				text: "Buses Traffic"
			},
			axisY: {
				title: "Velocity",
				prefix: "km/h "
			
			},
			axisX: {
				title: "Bus ID",
				prefix: "Bus "
			
			},
			data: [{
				type: "line",
				dataPoints: dataPoints
			}]
		}
		return (
		<div>
            <Navbar/>
			<CanvasJSChart options = {options} 
				onRef={ref => this.chart = ref}
			/>
			{/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
		</div>
		);
	}
	
	componentDidMount(){
		var chart = this.chart;
		fetch('http://localhost:8888/api/speedNode1')
		.then(function(response) {
			return response.json();
		})
		
		.then(function(data) {
			for (var i = 0; i < 50; i++) {

				dataPoints.push({
					x: i,
					y: data[i].y
				});
			}
			
			console.log(data)
			chart.render();
		});
	}
}
 
export default BusStops;