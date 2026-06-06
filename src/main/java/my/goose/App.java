package my.goose;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;



public class App extends Application{


    public void start(Stage stage){


        int sceneX = 1000;
        int sceneY = 1000;


        VBox root = new VBox();
        HBox layerOne = new HBox();

        double canvasX = sceneX * 0.8;
        double canvasY = sceneY * 0.8;

        double infoX = sceneX * 0.2;
        double controlY = sceneY * 0.2; 

        Canvas canvas = new Canvas((int)canvasX, (int)canvasY); // create canvas

        // set up MVC connections
        Model model = new Model();  // holds business logic
        InteractionModel iModel = new InteractionModel(model);  // interactions with the business logic
        View view = new View(model, iModel, canvas);    // show what the model and iModel represent
        Controller controller = new Controller(model, iModel, view);    // decisions of what to do with model and iModel

        // observer pattern
        model.addSubscriber(view);
        iModel.addSubscriber(view);

        model.setSize(canvasX, canvasY);

        // information for the program
        InfoView infoView = new InfoView((int)infoX, sceneY, model, iModel);

        // Controls for user
        ControlView controlView = new ControlView(sceneX, (int) controlY, model, iModel);
        
        model.addSubscriber(infoView);
        iModel.addSubscriber(infoView);

        // view delegates event handling to controller
        view.setupEvents(controller);

        new AnimationTimer(){
            @Override
            public void handle(long now){
                controller.tick();
            }
        }.start();

        layerOne.getChildren().addAll(canvas, infoView.getInfo());
        root.getChildren().addAll(controlView.getView(), layerOne);
        stage.setScene(new Scene(root, sceneX, sceneY));
        stage.show();
    }

}