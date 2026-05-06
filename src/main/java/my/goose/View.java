package my.goose;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class View implements Subscriber {

    private final Model model;
    private final InteractionModel iModel;
    private final Canvas canvas;

    private final Image gooseImage = new Image(getClass().getResource("/goose.png").toExternalForm());

    // private final Image gooseImage = new Image("goose.png");

    public View(Model model, InteractionModel iModel, Canvas canvas){
        this.model = model;
        this.iModel = iModel;
        this.canvas = canvas;

    }

    public void setupEvents(Controller controller){
        canvas.setOnMousePressed(controller::handlePressed);
        canvas.setOnMouseMoved(controller::handleMoved);
        canvas.setOnMouseReleased(controller::handleReleased);
    }

    public void update(){
        draw();
    }

    private void draw(){

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double x = model.getGooseX();
        double y = model.getGooseY();

        if (iModel.isMouseOverGoose()){
            gc.strokeOval(x - 30, y - 30, 60, 60);
        }

        gc.drawImage(gooseImage, x - 25, y - 25, 50, 50);

    }

}