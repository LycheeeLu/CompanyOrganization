import java.util.ArrayList;
import java.util.Scanner;

public class OrganizationApp {
    private static Group company = null;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String choice;

        while(true){
            //display menu as a specific function
            displayMenu();

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
                    create();
                    print();
                    break;
                case "2":
                    print();
                    addPerson();
                    break;
                case "3":
                    print();
                    removePerson();
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter 1, 2, 3, q or Q.");

            }
            System.out.println();
        }
        userInput.close();
    }

    private static void displayMenu(){
        //display menu
        System.out.println("Organization management system");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("1. Create and print hard coded organization");
        System.out.println("2. Print organization, add person to it and finally print it");
        System.out.println("3. Print organization, remove person from it and finally print it");
        System.out.println("Q. Quit the application");
        System.out.println();
    }

    private static void create(){
        company = new Group("Top Management", "Scrooge McDuck");
        company.add(new Worker("Grandma Duck", "Secretary"));

        // Create departments
        Group marketing = new Group("Marketing", "Donald Duck");
        Group softwareDevelopment = new Group("Software Development", "Daisy Duck");
        Group customerSupport = new Group("Customer Support", "Gladstone Gander");

        // Add workers to departments
        marketing.add(new Worker("Gus Goose"));
        softwareDevelopment.add(new Worker("Huey Duck"));
        softwareDevelopment.add(new Worker("Dewey Duck"));
        softwareDevelopment.add(new Worker("Louie Duck"));
        customerSupport.add(new Worker("Gyro Gearloose"));
        customerSupport.add(new Worker("Magica De Spell"));
        customerSupport.add(new Worker("Launchpad McQuack"));
        // Add departments to organization
        company.add(marketing);
        company.add(softwareDevelopment);
        company.add(customerSupport);

    }

    private static void print(){
        try {
            // check if company has been created or not
            if (company == null) {
                throw new ErrorHandling.OrganizationNotCreatedException("Organization is not created yet. Create it first in step 1.");
            }
            company.print(0);
        } catch(ErrorHandling.OrganizationNotCreatedException e) {
                System.out.println("\nError: "+ e.getMessage());
        }
    }

    private static void addPerson(){
        try{
        if (company == null) {
            return;
        }
        Scanner userInput = new Scanner(System.in);

        System.out.print("\nGive unit name: ");
        String unitName = userInput.nextLine();


        System.out.print("Give person name: ");
        String personName = userInput.nextLine();

        // Validate person name format (First Last with capital letters)
            if (!personName.matches("[A-Z][a-z]+ [A-Z][a-z]+")) {
                throw new ErrorHandling.InvalidNameFormatException("Invalid name format. Please enter a valid name like John Smith");
            }

            //add a new person
        Worker newGuy = new Worker(personName);
        Component group = company.findGroupByName(unitName);

        if (group == null) {
            throw new ErrorHandling.GroupNotFoundException("Organization not found. Give it again");
        }
        ((Group) group).add(newGuy);
        print();
        } catch (ErrorHandling.GroupNotFoundException
                 | ErrorHandling.InvalidNameFormatException e) {
            System.out.println("\nError: "+ e.getMessage());
        }
    }

    private static void removePerson(){
        try {
            // check if option 1 has been selected or not
            if (company == null) {
                return;
            }

            Scanner userInput = new Scanner(System.in);
            System.out.print("\nGive person name: ");
            String personName = userInput.nextLine();

            // Validate person name format (First Last with capital letters)
            if (!personName.matches("[A-Z][a-z]+ [A-Z][a-z]+")) {
                throw new ErrorHandling.InvalidNameFormatException("Invalid name format. Please enter a valid name like John Smith");
            }

            boolean removed = removePersonRecursively(company, personName);

            if(!removed){
                throw new ErrorHandling.PersonNotFoundException("Person not found. Give it again");
            }

            print();
        } catch (ErrorHandling.InvalidNameFormatException
                 | ErrorHandling.PersonNotFoundException e){
            System.out.println("\nError: "+ e.getMessage());
        }
    }

    //helper methods for removePersonPrint(
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