<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="classroom.title"/></title>
    <meta name="heading" content="<fmt:message key='tutorialList.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
    <script src="http://staging.tokbox.com/v0.91/js/TB.min.js"></script>
 <script type="text/javascript">
    var apiKey = '7323381';
    var sessionId = '2_MX4xMjMyMDgxfn4yMDExLTExLTA5IDAyOjU1OjM0LjcwMDY3MiswMDowMH4wLjUzMzQ0MzMxNTIyM34';
    var token = 'devtoken';
     
    var session;
    var publisher;
    var subscribers = {};

    // Un-comment either of the following to set automatic logging and exception handling.
    // See the exceptionHandler() method below.
    // TB.setLogLevel(TB.DEBUG);
    // TB.addEventListener("exception", exceptionHandler);

    if (TB.checkSystemRequirements() != TB.HAS_REQUIREMENTS) {
      alert("You don't have the minimum requirements to run this application."
          + "Please upgrade to the latest version of Flash.");
    } else {
      session = TB.initSession(sessionId);  // Initialize session

      // Add event listeners to the session
      session.addEventListener('sessionConnected', sessionConnectedHandler);
      session.addEventListener('sessionDisconnected', sessionDisconnectedHandler);
      session.addEventListener('connectionCreated', connectionCreatedHandler);
      session.addEventListener('connectionDestroyed', connectionDestroyedHandler);
      session.addEventListener('streamCreated', streamCreatedHandler);
      session.addEventListener('streamDestroyed', streamDestroyedHandler);
    }

    //--------------------------------------
    //  LINK CLICK HANDLERS
    //--------------------------------------

    /*
    If testing the app from the desktop, be sure to check the Flash Player Global Security setting
    to allow the page from communicating with SWF content loaded from the web. For more information,
    see http://www.tokbox.com/opentok/build/tutorials/helloworld.html#localTest
    */
    function connect() {
      session.connect(apiKey, token);
      hide('push-to-talk');
    }

    function disconnect() {
      session.disconnect();
      hide('disconnectLink');
      hide('publishLink');
      hide('unpublishLink');
    }

    // Called when user wants to start publishing to the session
    function startPublishing() {
      if (!publisher) {
        var parentDiv = document.getElementById("myCamera");
        var publisherDiv = document.createElement('div'); // Create a div for the publisher to replace
        publisherDiv.setAttribute('id', 'opentok_publisher');
        parentDiv.appendChild(publisherDiv);
        publisher = session.publish(publisherDiv.id); // Pass the replacement div id to the publish method
        show('unpublishLink');
        hide('publishLink');
        show('push-to-talk');
      }
    }

    function stopPublishing() {
      if (publisher) {
        session.unpublish(publisher);
      }
      publisher = null;

      show('publishLink');
      hide('unpublishLink');
      hide('push-to-talk');
    }

    //--------------------------------------
    //  OPENTOK EVENT HANDLERS
    //--------------------------------------

    function sessionConnectedHandler(event) {
      // Subscribe to all streams currently in the Session
      for (var i = 0; i < event.streams.length; i++) {
        addStream(event.streams[i]);
      }
      show('disconnectLink');
      show('publishLink');
      hide('connectLink');
    }

    function streamCreatedHandler(event) {
      // Subscribe to the newly created streams
      for (var i = 0; i < event.streams.length; i++) {
        addStream(event.streams[i]);
      }
    }

    function streamDestroyedHandler(event) {
      // This signals that a stream was destroyed. Any Subscribers will automatically be removed.
      // This default behaviour can be prevented using event.preventDefault()
    }

    function sessionDisconnectedHandler(event) {
      // This signals that the user was disconnected from the Session. Any subscribers and publishers
      // will automatically be removed. This default behaviour can be prevented using event.preventDefault()
      publisher = null;

      show('connectLink');
      hide('disconnectLink');
      hide('publishLink');
      hide('unpublishLink');
    }

    function connectionDestroyedHandler(event) {
      // This signals that connections were destroyed
    }

    function connectionCreatedHandler(event) {
      // This signals new connections have been created.
    }

    /*
    If you un-comment the call to TB.addEventListener("exception", exceptionHandler) above, OpenTok calls the
    exceptionHandler() method when exception events occur. You can modify this method to further process exception events.
    If you un-comment the call to TB.setLogLevel(), above, OpenTok automatically displays exception event messages.
    */
    function exceptionHandler(event) {
      alert("Exception: " + event.code + "::" + event.message);
    }

    //--------------------------------------
    //  HELPER METHODS
    //--------------------------------------

    function addStream(stream) {
      // Check if this is the stream that I am publishing, and if so do not publish.
      if (stream.connection.connectionId == session.connection.connectionId) {
        return;
      }
      var subscriberDiv = document.createElement('div'); // Create a div for the subscriber to replace
      subscriberDiv.setAttribute('id', stream.streamId); // Give the replacement div the id of the stream as its id.
      document.getElementById("subscribers").appendChild(subscriberDiv);
      subscribers[stream.streamId] = session.subscribe(stream, subscriberDiv.id);
    }

    function show(id) {
      document.getElementById(id).style.display = 'block';
    }

    function hide(id) {
      document.getElementById(id).style.display = 'none';
    }

function startTalking() {
  for (i = 0; i < session.subscribers.length; i++) {
        subscribers[i].subscribeToAudio(false);
    }
    publisher.publishAudio(true);
    document.getElementById("push-to-talk").onmouseup = stopTalking;
    document.getElementById("push-to-talk").value = "Release to mute";
}

function stopTalking() {
    publisher.publishAudio(false);
    for (i = 0; i < subscribers.length; i++) {
        subscribers[i].subscribeToAudio(true);
    }
    document.getElementById("push-to-talk").onmousedown = startTalking;
    document.getElementById("push-to-talk").value = "Push to talk";
}

  </script>
</head>

<div class="main-content">
  <div class="main-content">
    <h2><fmt:message key="classroom.title"/></h2><p>
    <div id="opentok_console"></div>
    <div id="links">
          <input type="button" value="Connect" id ="connectLink" onClick="javascript:connect()" />
          <input type="button" value="Leave" id ="disconnectLink" onClick="javascript:disconnect()" />
          <input type="button" value="Start Publishing" id ="publishLink" onClick="javascript:startPublishing()" />
          <input type="button" value="Stop Publishing" id ="unpublishLink" onClick="javascript:stopPublishing()" />
      <input type="button" value="Push to talk" id ="push-to-talk" onmousedown="startTalking()"/>
    </div>
    <div id="myCamera" class="publisherContainer"></div>
    <div id="subscribers"></div>
  </div>
</div>
