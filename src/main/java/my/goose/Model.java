package my.goose;

import java.util.ArrayList;
import java.util.List;

public class Model implements Publisher {
    
    private double mouseX, mouseY;
    private double gooseX, gooseY;

    private double canvasSizeX;
    private double canvasSizeY;

    private final List<Subscriber> subscribers = new ArrayList<>();
    private List<Box> boxes = new ArrayList<Box>();

    public void setSize(double x, double y){
        this.canvasSizeX = x;
        this.canvasSizeY = y;
    }

    public List<Double> getSize(){
        List<Double> coords = new ArrayList<>();
        coords.add(canvasSizeX);
        coords.add(canvasSizeY);
        return coords;
    }

    public void addBox(Box box){
        boxes.add(box);
    }

    public List<Box> getBoxes(){
        return this.boxes;
    }

    public void setMousePosition( double x, double y) {
        this.mouseX = x;
        this.mouseY = y;
        notifySubscribers();
    }

    public void setGoosePosition(double x, double y) {
        this.gooseX = x;
        this.gooseY = y;
        notifySubscribers();
    }

    public Boolean checkGooseInBox(){

        if (this.gooseX < 0 || this.gooseX > 800){
            return true;
        }
        else if (this.gooseY < 200 || this.gooseY > 1000){
            return true;
        }
        return false;
    }

    public double getMouseX() { return mouseX; }
    public double getMouseY() { return mouseY; }
    public double getGooseX() { return gooseX; }
    public double getGooseY() { return gooseY; }

    @Override
    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }

    @Override
    public void removeSubscriber(Subscriber s) {
        subscribers.remove(s);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber sub : subscribers) {
            sub.update();
        }
    }

}