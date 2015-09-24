/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var wsUri = "ws://" + document.location.host + document.location.pathname + "guperendpoint";
var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt) { onError(evt) };

websocket.onopen = function(evt) { onOpen(evt) };

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}


function writeToScreen(message) {
    var output = document.getElementById("output");
    output.innerHTML += message + "<br>";
}


websocket.onmessage = function(evt) { onMessage(evt) };

var message = document.getElementById("message");
var messages = document.getElementById("messages");

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
                
function onMessage(evt) {
    console.log("received: " + evt.data);
    //drawImageText(evt.data);
    var m = JSON.parse(evt.data);
    writeToScreen(m.text);
}