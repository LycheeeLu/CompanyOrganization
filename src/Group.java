import java.util.ArrayList;
import java.util.List;

public class Group implements OrganizationComponent {
    private String groupName;
    private String bossName;
    private List<OrganizationComponent> groupMembers;

    public Group(String groupName, String bossName) {
        this.groupName = groupName;
        this.bossName = bossName;
        this.groupMembers = new ArrayList<>();
    }

    @Override
    public String getName() {
        return bossName;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<OrganizationComponent> getGroupMembers() {
        return new ArrayList<>(groupMembers);
    }
    //adding members to group
    public void add(OrganizationComponent member) {
        groupMembers.add(member);
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
        System.out.println(indent + "Group: " + getGroupName() + ", boss's name: " + getName() );
        // print all members (workers or groups) with increated indent level
        for (OrganizationComponent component : groupMembers) {
            component.print(indentationLevel + 1);
        }

    }


    //map user input group name to the actual group
    public OrganizationComponent findGroupByName(String userinput) {
        // if input name == the biggest group name
        if (userinput.equals(groupName)){
            return this;
        }
        for (OrganizationComponent component : groupMembers) {
            //only check for groups not workers
            if (component instanceof Group) {
                //now that component is a group, we type cast it into a Group
                // because it originally comes from OrganizationComponent datatype
                Group g = (Group) component;
                //using the function on itself
                OrganizationComponent found = g.findGroupByName(userinput);
                if(found != null) {
                    return found;
                }
            }
        }
        return null;
    }



}
