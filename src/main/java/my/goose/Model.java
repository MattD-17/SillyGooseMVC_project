package my.goose;

import java.util.ArrayList;
import java.util.List;

public class Model implements Publisher {
    
    private double mouseX, mouseY;
    private double gooseX, gooseY;

    private final List<Subscriber> subscribers = new ArrayList<>();

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