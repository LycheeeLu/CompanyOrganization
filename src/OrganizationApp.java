import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class OrganizationApp {
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
            System.out.println();

            // handle input
            //both q and Q are accepted
            if (choice.equalsIgnoreCase("q")){
                break;
            }

            //check scenario 1, 2, and 3
            switch (choice){
                case "1":
                case "2":
                case "3":
                    System.out.println("Choice " + choice + " not implemented.\n");
                    break;
                default:
                    System.out.println("Invalid choice\n");

            }
        }

        userInput.close();
    }
}