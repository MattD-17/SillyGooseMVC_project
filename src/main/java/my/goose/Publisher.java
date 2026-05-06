package my.goose;

public interface Publisher {

    void addSubscriber(Subscriber s);
    void removeSubscriber(Subscriber s);
    void notifySubscribers();
}