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
        System.out.println(indent + "Group: " + getGroupName() + ", boss's name: " + getName() );
        // print all members (workers or groups) with increated indent level
        for (OrganizationComponent component : groupMembers) {
            component.print(indentationLevel + 1);
        }
    }

}
