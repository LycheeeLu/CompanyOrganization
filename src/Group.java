import java.util.ArrayList;
import java.util.List;

public class Group extends Component {
    private String bossName;
    private List<Component> groupMembers;

    public Group(String name, String bossName) {
        super(name);
        this.bossName = bossName;
        this.groupMembers = new ArrayList<>();
    }


    public String getBossName() {
        return bossName;
    }

    public List<Component> getGroupMembers() {
        return new ArrayList<>(groupMembers);
    }

    //adding members to group
    public void add(Component member) {
        groupMembers.add(member);
    }

    //removing members from group
    public boolean remove(Component member) {
        return groupMembers.remove(member);
    }

    //remove members by name from group
    public boolean removeByName(String name) {
        for (Component component : new ArrayList<>(groupMembers)) {
            if (component instanceof Worker && component.getName().equals(name)) {
                return groupMembers.remove(component);
            }
        }
        return false;
    }

    //find group members by name
    public Component findGroupByName(String userinput) {
        // if input name == the biggest group name
        if (userinput.equals(getName())) {
            return this;
        }
        for (Component component : groupMembers) {
            //only check for groups not workers
            if (component instanceof Group) {
                //now that component is a group, we type cast it into a Group
                // because it originally comes from OrganizationComponent datatype
                Group g = (Group) component;
                //using the function on itself
                Component found = g.findGroupByName(userinput);
                if(found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    @Override
    public void print(int indentationLevel) {
        // add indent
        String indent = "";
        for (int i = 0; i < indentationLevel; i++) {
            indent += "  ";
        }
        // print group name and boss name
        System.out.println();
        System.out.println(indent + "Group: " + getName() + ", boss's name: " + getBossName() );
        // print all members (workers or groups) with increased indent level
        for (Component component : groupMembers) {
            component.print(indentationLevel + 1);
        }

    }






}
