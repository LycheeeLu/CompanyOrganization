public class Worker extends Component {
    private String role;

    public Worker(String name) {
        super(name);
        this.role = null;
    }

    public Worker(String name, String role) {
        super(name);
        this.role = role;
    }

    @Override
    public void print(int indentationLevel) {
       //add indent according to level
        String indent = "";
        for (int i = 0; i < indentationLevel; i++) {
            indent += "  ";
        }

       if(role != null && !role.isEmpty()) {
           System.out.println( indent + "Worker: " + getName() + " (" + getRole() + ") ");
       } else {
           System.out.println(indent + "Worker: "+ getName() + " ");
       }

    }

    public String getRole() {
        return role;
    }

}
