package my.goose;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel implements Publisher {

    private boolean mouseOverGoose = false;

    private final Model model;
    private final List<Subscriber> subscribers = new ArrayList<>();

    public InteractionModel(Model model){
        this.model = model;
    }

    public void updateInteractionState(){
        double mx = model.getMouseX();
        double my = model.getMouseY();
        double gx = model.getGooseX();
        double gy = model.getGooseY();

        double radius = 50;
        boolean goToNewState = Math.hypot(mx - gx, my - gy) < radius;

        if (goToNewState != mouseOverGoose){
            mouseOverGoose = goToNewState;
            notifySubscribers();
        }
    }

    public boolean isMouseOverGoose(){
        return mouseOverGoose;
    }

    @Override
    public void notifySubscribers(){
        for (Subscriber sub : subscribers){
            sub.update();
        }
    }

    @Override
    public void addSubscriber(Subscriber s){
        subscribers.add(s);
    }

    @Override
    public void removeSubscriber(Subscriber s){
        subscribers.remove(s);
    }


}