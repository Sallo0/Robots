package robots.log;

public interface LogChangeListener {
    void onLogChanged();

    void unregister();
}
