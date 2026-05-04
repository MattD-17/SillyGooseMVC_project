import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class View implements Subscriber {

    private final Model model;
    private final InteractionModel iModel;
    private final Canvas canvas;

    // private final Image gooseImage = new Image("goose.png");

    public View(Model model, InteractionModel iModel, Canvas canvas){
        this.model = model;
        this.iModel = iModel;
        this.canvas = canvas;

    }

}