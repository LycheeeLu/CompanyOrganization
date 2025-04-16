public class Worker implements OrganizationComponent {
    private String name;
    private String role;

    public Worker(String name, String role) {
        this.name = name;
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

    @Override
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

}
