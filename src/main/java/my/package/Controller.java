import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final Model model;
    private final InteractionModel iModel;
    private final View view;

    private State currentState;

    public Controller(Model model, InteractionModel iModel, View view) {
        this.model = model;
        this.iModel = iModel;
        this.view = view;

        this.currentState = new MovingState(this);
    }

    // Abstract class for defining state
    private abstract static class State{

        protected final Controller controller;

        State(Controller controller){
            this.controller = controller;
        }

        void handleMoved(MouseEvent event){}
        void handlePressed(MouseEvent event) {}
        void handleReleased(MouseEvent event) {}
        void handleDragged(MouseEvent mouseEvent) {}
        void handleKey(KeyEvent event) {}

        void update(){}

    }

    /// EVENT SETUP ////////////////////////////////////////////////////////////////////////////

    public void handleMoved(MouseEvent event){currentState.handleMoved(event);}
    public void handlePressed(MouseEvent mouseEvent) {currentState.handlePressed(mouseEvent);}
    public void handleDragged(MouseEvent mouseEvent) {currentState.handleDragged(mouseEvent);}
    public void handleReleased(MouseEvent mouseEvent) { currentState.handleReleased(mouseEvent); }
    public void tick() { currentState.update(); }
    private void setState(ControllerState newState) { this.currentState = newState; }



    public static class IdleState extends State{

        private final long startTime = System.currentTimeMillis();
        private final long delay = 2000;

        IdleState(Controller controller){
            super(controller);
            startTime = System.currentTimeMillis();
        }

        @Override
        void handleMoved(MouseEvent e){
            controller.setMousePosition(e.getX(), e.getY());
            controller.iModel.updateInteractionState();
        }

        @Override
        public void update(){
            if (System.currentTimeMillis() - startTime > delay){
                controller.setState( new MovingState(controller));
            }
        }

    }

    public static class MovingState extends State {

        MovingState(Controller controller){
            super(controller);
        }

        @Override
        void handleMoved(MouseEvent e){
            controller.model.setMousePosition(e.getX(), e.getY());
            controller.iModel.updateInteractionState();
        }

        @Override
        void handleClicked(MouseEvent e){
            if (controller.iModel.isMouseOverGoose()){
                controller.setState(new IdleState(controller));
            }
        }
        @Override
        public void update(Model model){
            Model m = controller.model;

            double dx = m.getMouseX() - m.getGooseX();
            double dy = m.getMouseY() - m.getGooseY();

            double dist = Math.hypot(dx, dy);
            double speed = 2.0;

            if (dist > 1){
                m.setGoosePosition(
                    m.getGooseX() + (dx / dist) * speed,
                    m.getGooseY() + (dy / dist) * speed
                );
            }
        }

    }





}