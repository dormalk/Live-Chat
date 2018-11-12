# Live Chat
  Middle assignment in Java course - Live Chat

## Design Patterns
Implements of kind of design patterns:
- Consumer Producer - Interface
- Singelton - Server
- Proxy - Mediate between server and client
- Observer - Class that holds all the users and notify when user get massage

## Components
  ### ClientGui
  Impliments of client side view
  ### ServerApplication
  Impliment of Server side
    - Create socket connection
    - Provide TCP connection to other users on same socket
  #### MessageBoard
    Holds list of consumets
      - Hendle reciving and sending massages to users
  ### StringProducer
    Interface provide producer methods
  ## StringConsumer
    Interface provide consumer methods
      - Inform() send notification to producer
      - consume() ask for service from producer
  ## ConnectionProxy
    Insulation layer that promise the server and the client will not corrupted 
  ## ClientDescriptor 
    Holds user name
      - When user send message - his name attached to the message
  ## System Sketch
  ![system diagram](https://i.imagesup.co/images2/0__05be9ac41d2a53.jpg)
  
  ## Project status
    Done
  
