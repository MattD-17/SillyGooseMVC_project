package my.goose;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class InfoView implements Subscriber{

    private Model model;
    private InteractionModel iModel;

    VBox infoBox;
    Label goosePositionLabel;

    public InfoView(int sceneX, int sceneY, Model model, InteractionModel iModel){

        this.model = model;
        this.iModel = iModel;
        

        infoBox = new VBox();
        infoBox.setPrefSize(sceneX, sceneY);
        infoBox.setStyle("-fx-background-color: red;");

        goosePositionLabel = new Label("Current goose position:\n x:" + model.getGooseX() + "\n y: " + model.getGooseY());
        infoBox.getChildren().add(goosePositionLabel);

    }

    @Override
    public void update(){
        draw();
    }

    public VBox getInfo(){
        return infoBox;
    }

    public void draw(){
        goosePositionLabel.setText("Current goose position:\n x:" + model.getGooseX() + "\n y: " + model.getGooseY());
    }



}

