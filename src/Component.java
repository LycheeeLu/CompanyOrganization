public abstract class Component {
    private String name;
    public Component(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract void print(int indentationLevel);
}
