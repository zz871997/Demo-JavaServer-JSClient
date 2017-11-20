/**
 * Created by Tran Quang Khai on 11/7/2017.
 */

var socket   = io.connect('http://localhost:9092');
var userName = 'user' + Math.floor((Math.random()*1000) + 1);

/* Listen Event Connect */
socket.on('connect', function () {
    output('<span class="connect-msg">Client has connected to the java server </span>');
});

/* Listen Chat event */
socket.on('chatevent', function (data) {
    output('<span class="username-msg">' + data.userName + ':</span>' + data.message);
});

/* Listen Disconnect Event */
socket.on('disconnect', function () {
    output('<span class="disconnect-msg"> The Client has disconnect! </span>');
});

/* Now, There are functions - That client send to server */
/* Send Disconnect */
function sendDisconnect() {
    socket.disconnect();
}
/* Send message */
function sendMessage() {
    var message = $('#msg').val();
    $('#msg').val('');

    var jsonObject = {userName: userName, message: message};
    /* Now, server will emit chat event to server - send json to server */
    socket.emit('chatevent', jsonObject);
}

/* Output */
function output(message) {
    var currentTime = "<span class='time'>" + moment().format("HH:mm:ss.SSS")
                                            + "</span>";
    var element     = $("<div>" + currentTime + " " + message + "</div>");
    $('#console').prepend(element);
}

/* Enter is press */
$(document).keydown(function(e){
    if(e.keyCode == 13) {
        $('#send').click();
    }
});

