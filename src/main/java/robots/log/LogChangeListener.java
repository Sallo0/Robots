package robots.log;

public interface LogChangeListener
{
    public void onLogChanged();
    public void unregister();
}
