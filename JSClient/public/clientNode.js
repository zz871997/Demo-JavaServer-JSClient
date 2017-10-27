var io = require('socket.io-client');
var socket = io.connect('http://localhost:8080');

socket.on('connect', function () {
    console.log('Connected! \n \t Sending query...');

    socket.emit('myEvent', '\t I am the query', function (data) {
        console.log(data);
    });
});