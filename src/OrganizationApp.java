import java.util.ArrayList;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class OrganizationApp {
    private static Group company = null;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String choice;


        while(true){
            //display menu
            System.out.println("Organization management system");
            System.out.println("------------------------------");
            System.out.println();
            System.out.println("1. Create and print hard coded organization");
            System.out.println("2. Print organization, add person to it and finally print it");
            System.out.println("3. Print organization, remove person from it and finally print it");
            System.out.println("Q. Quit the application");
            System.out.println();

            //ask for user input
            System.out.print("Your choice: ");
            choice = userInput.nextLine();

            // handle input
            //both q and Q are accepted
            if (choice.equalsIgnoreCase("q")){
                break;
            }

            //check scenario 1, 2, and 3
            switch (choice){
                case "1":
                    createPrint();
                    break;
                case "2":
                    createPrint();
                    System.out.println();
                    addPersonPrint();
                    break;
                case "3":
                    createPrint();
                    System.out.println();
                    removePersonPrint();
                    break;
                default:
                    System.out.println("Invalid choice\n");

            }
            System.out.println();
        }

        userInput.close();
    }

    private static void createPrint(){
        company = new Group("Top Management", "Scrooge McDuck");

        // Create departments
        Group marketing = new Group("Marketing", "Donald Duck");
        Group engineering = new Group("Engineering", "Gyro Gearloose");
        Group security = new Group("Security", "Gizmoduck");

        // Add workers to departments
        marketing.add(new Worker("Daisy Duck", "Secretary"));
        marketing.add(new Worker("Gladstone Gander"));

        engineering.add(new Worker("Fenton Crackshell"));
        engineering.add(new Worker("Launchpad McQuack", "Test Pilot"));

        Group juniorEngineers = new Group("Junior Engineers", "Huey Duck");
        juniorEngineers.add(new Worker("Dewey Duck"));
        juniorEngineers.add(new Worker("Louie Duck"));

        engineering.add(juniorEngineers);

        security.add(new Worker("Mrs. Beakley", "Intelligence"));
        security.add(new Worker("Webby Vanderquack", "Trainee"));

        // Add departments to organization
        company.add(marketing);
        company.add(engineering);
        company.add(security);

        //testing print
        company.print(0);
    }

    private static void addPersonPrint(){

        Scanner userInput = new Scanner(System.in);
        System.out.print("Give unit name: ");
        String unitName = userInput.nextLine();

        System.out.print("Give person name: ");
        String personName = userInput.nextLine();

        //add a new person
        Worker newGuy = new Worker(personName);
        Component group = company.findGroupByName(unitName);
        ((Group) group).add(newGuy);
        company.print(0);

    }

    private static void removePersonPrint(){
        Scanner userInput = new Scanner(System.in);
        System.out.print("Give unit name: ");
        String unitName = userInput.nextLine();
        boolean removed = removePersonRecursively(company, unitName);
        company.print(0);

    }


    private static boolean removePersonRecursively(Group group, String personName) {
        // Try to remove from this group
        boolean removed = group.removeByName(personName);
        if (removed) {
            return true;
        }

        // If not found in this group, search in subgroups
        for (Component component : new ArrayList<>(group.getGroupMembers())) {
            if (component instanceof Group) {
                removed = removePersonRecursively((Group) component, personName);
                if (removed) {
                    return true;
                }
            }
        }

        return false;
    }
}