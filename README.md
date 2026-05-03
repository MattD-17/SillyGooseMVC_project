This project aims to utilize the JavaFX library to demonstrate an understanding of the Model-View-Controller architecture.

In this project, the first milestone will be to have a silly goose gif chase the mouse and when the mouse is clicked, the goose will wait for a few seconds. The following classes will be in place:




Controller:
    - use an abstract class called State
        - Create classes that use the abstract State class ie., Idle, Dragging, Moving

Model: 
    - Store the mouse position and the silly goose png position
    - the model will use a publisher model where it will publish the information it stores.

View:
    - Use the graphics context of the canvas to display everything
    - the view will implement a subscriber interface to utilize the model's information to reduce coupling.

Interaction Model:
    - if mouse is on the goose, highlight the goose
        - this is where the overlap logic will exist
    - the interaction model will also be a publisher for any subscribers.