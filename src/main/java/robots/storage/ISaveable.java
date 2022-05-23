package robots.storage;

public interface ISaveable {
    void setParams(WindowState params);

    WindowState windowParams();
}
