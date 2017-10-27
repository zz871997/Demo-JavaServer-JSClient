var http = require('http');
console.log('CREATING HTTP SERVER');

var server = http.createServer(function (req, res) {

});

var io = require('socket.io').listen(server);

io.sockets.on('connection', function (socket) {
    console.log('Client connected...');

    // Disconnect listener
    socket.on('disconnect', function () {
        console.log('Client disconnected.')
    });

    // My Event
    socket.on('myEvent', function (data, fn) {
        console.log(data);
        console.log('\tSending answer ...');
        fn('\t I am answer');
    });
});

server.listen(8080);