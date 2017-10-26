var net = require('net');
var client = net.connect(8888, 'localhost');
client.write('Hello from node.js');
client.end();