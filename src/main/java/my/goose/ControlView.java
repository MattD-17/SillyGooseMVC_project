package my.goose;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;


public class ControlView implements Subscriber{

    Model model;
    InteractionModel iModel;
    HBox controlBox;

    Button moveGooseButton;


    public ControlView(int sceneX, int sceneY, Model model, InteractionModel iModel){

        this.model = model;
        this.iModel = iModel;
        controlBox = new HBox();
        controlBox.setPrefSize(sceneX, sceneY);
        controlBox.setStyle("-fx-background-color: blue;");

        moveGooseButton = new Button();
        double buttonX = sceneX * 0.1;
        double buttonY = sceneY;
        moveGooseButton.setPrefSize(buttonX, buttonY);
        moveGooseButton.setText("Randomly Move Goose");

        controlBox.getChildren().add(moveGooseButton);


        List<Double> canvasSize = model.getSize();
        double x = canvasSize.get(0);
        double y = canvasSize.get(1);

        moveGooseButton.setOnAction(event -> {

            double randomX = (Math.random() % x) * x;
            double randomY = (Math.random() % y) * y;

            System.out.println("Coords: " + randomX + " " + randomY);

            if (randomX > 800.0){
                randomX = 800.0;
            }
            if (randomY < 200.0){
                randomY = 200.0;
            }
                model.setGoosePosition(randomX, randomY);
        });

    }

    @Override
    public void update(){
        draw();
    }

    public HBox getView(){
        return controlBox;
    }

    public void draw(){
    }

}