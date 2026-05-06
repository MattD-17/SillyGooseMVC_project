package my.goose;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{


    public void start(Stage stage){
        Canvas canvas = new Canvas(800, 600); // create canvas

        // set up MVC connections
        Model model = new Model();  // holds business logic
        InteractionModel iModel = new InteractionModel(model);  // interactions with the business logic
        View view = new View(model, iModel, canvas);    // show what the model and iModel represent
        Controller controller = new Controller(model, iModel, view);    // decisions of what to do with model and iModel

        // observer pattern
        model.addSubscriber(view);
        iModel.addSubscriber(view);

        // view delegates event handling to controller
        view.setupEvents(controller);

        new AnimationTimer(){
            @Override
            public void handle(long now){
                controller.tick();
            }
        }.start();

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
    }

}