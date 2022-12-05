import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class FleetManagement {

    public static final Scanner keyboard = new Scanner(System.in);
    public static final int ZERO = 0;
    public static final int SINGLE = 1;

    enum type {POWER, SAILING}

    private ArrayList<Boat> fleet;

    //-----------------------------------------------------------------------

    public static void main(String[] args) {

        //--Here we initialize the ArrayList and specify the file that we are importing for the CSV file
        ArrayList<Boat>fleet = new ArrayList<Boat>();
        String path = "C:\\Users\\Ailis\\Desktop\\CSC120_LAB\\FleetData.csv";
        initFromCSVFile(path, fleet);

        //--This calls the menu and will run through the menu until "exit" is selected
        menu(fleet);

    }
    //-----------------------------------------------------------------------

    private static ArrayList<Boat> initFromCSVFile(String path, ArrayList<Boat>fleet) {

        //--This method takes the file path from the main and the ArrayList and reads the file
        String line = "";
        Boat newBoat = new Boat();

        //--The try-catch block reads the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                newBoat = createBoat(values);
                fleet.add(newBoat);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        //--Returning the fleet ArrayList of Boat objects created
        return(fleet);
    }
    //-----------------------------------------------------------------------
    public static Boat createBoat(String[] attributes){

        //--This method creates a Boat object when called
        type theType;

        //--An array is imported containing the different attributes of the object to be made
        //--Each cell in the array corresponds with an attribute
        theType = type.valueOf(attributes[ZERO]);
        String name = attributes[SINGLE];
        int manufacturer = Integer.parseInt(attributes[2]);
        String makeAndModel = attributes[3];
        int length = Integer.parseInt(attributes[4]);
        double purchasePrice = Double.parseDouble(attributes[5]);

        //--Returning a new Boat object to be put into the fleet ArrayList
        return new Boat (theType,name,manufacturer,makeAndModel,length,purchasePrice);
    }
    //-----------------------------------------------------------------------
    private static void menu(ArrayList<Boat>fleet) {

        //--This method prompts the user for menu selection
        char menuSelection;
        int index;
        int index2 = ZERO;
        Boat myBoat = new Boat();
        String boatName;
        double newExpense;

        //--This is the welcome message - it only appears once
        System.out.println("");
        System.out.println("Welcome to the Fleet Management System");
        System.out.println("---------------------------------------");
        System.out.println("");

        //--Here is the menu selection - this will continue to loop until exit is selected
        do{
            System.out.print("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            menuSelection = Character.toUpperCase(keyboard.next().charAt(0));
            switch(menuSelection){
                case 'P':
                        printFleet(fleet);
                        break;
                case 'A':
                        addBoat(fleet);
                        break;
                case 'R':
                        removeBoat(fleet);
                        break;
                case 'E':
                        boatExpenses(fleet);
                        break;
                case 'X':
                        System.out.println(" ");
                        System.out.println("Exiting the Fleet Management System");
                        break;
                default:
                        System.out.println("Invalid menu option, try again");
            }
        } while(menuSelection != 'X');

    }
    //-----------------------------------------------------------------------
    public static void printFleet(ArrayList<Boat>fleet){

        //--This method prints all the boats in the fleet and the purchase and spending totals
        double grandTotalPaid = ZERO;
        double grandTotalExpenses = ZERO;
        int expensesIndex;

        //--The for loop calculates the total purchase amount and total spending expenses over entire fleet
        for(expensesIndex = ZERO; expensesIndex < fleet.size(); expensesIndex++){
            grandTotalPaid += fleet.get(expensesIndex).getPurchasePrice();
            grandTotalExpenses += fleet.get(expensesIndex).getExpenses();
        }

        int index = ZERO;

        //--Here is where the fleet report is printed on the screen with totals
        System.out.println(" ");
        System.out.println("Fleet report:");

        for( index = ZERO; index < fleet.size(); index++) {
            System.out.println(fleet.get(index));

       }
        System.out.println("    Total                                   " +
                "                     : Paid $ "
                + grandTotalPaid + " : Spent $ " + grandTotalExpenses);

        System.out.println(" ");
    }
    //-----------------------------------------------------------------------
    public static void addBoat(ArrayList<Boat>fleet){

        //--This method adds a boat to the fleet ArrayList
        //--This method reads a new line of use input and sends it to createBoat
        //--Once createBoat creates the Boat object, it comes back here to be put into fleet
        String newBoatData;
        Boat newBoat = new Boat();

        //--Prompting user input for new boat attributes
        System.out.print("Please enter the new boat CSV data : ");
        newBoatData = keyboard.next();
        String[] values = newBoatData.split(",");
        newBoat = createBoat(values);
        fleet.add(newBoat);

        System.out.println("");

    }
    //-----------------------------------------------------------------------
    public static void removeBoat(ArrayList<Boat>fleet) {

        //--This method removes a boat from the fleet ArrayList
        String boatName;
        String lookingForName;
        String wholeBoatName = null;
        int boatCounter = ZERO;
        int index = ZERO;
        int boatIndex = ZERO;
        int stringIndex = ZERO;
        int nameIndex = ZERO;

        keyboard.nextLine();

        //--Here it asks for user input which will then be converted to make the first letter of each word upper case
        System.out.print("Which boat would you like to remove? : ");
        boatName = keyboard.nextLine();

        String[] boatNameArray = boatName.split(" ");
        for(stringIndex = ZERO; stringIndex <boatNameArray.length; stringIndex++){
            boatNameArray[stringIndex] = boatNameArray[stringIndex].toLowerCase();
            String firstLetter = boatNameArray[stringIndex].substring(ZERO, 1).toUpperCase();
            String remainingLetters = boatNameArray[stringIndex].substring(1);
            boatNameArray[stringIndex] = firstLetter + remainingLetters;
        }
        //--If the name of the boat entered is more than one word, this fixes both words to be capitalized at first letter
        wholeBoatName = boatNameArray[ZERO];
        for(nameIndex = SINGLE; nameIndex <boatNameArray.length; nameIndex++){
            wholeBoatName += " ";
            wholeBoatName += boatNameArray[nameIndex];

        }

        //--This for loop helps to find if the name inputted exists in fleet
        for(index = ZERO; index < fleet.size(); index++){
            lookingForName = fleet.get(index).getName();
            if(!lookingForName.equals(wholeBoatName)){
                boatCounter = boatCounter;
            } else{
                boatCounter++;
            }
        }

        //--This if else statement either says boat cannot be found if the name doesn't exist
        //--or it removes the boat from the fleet if the name does exist
        if(boatCounter == ZERO){
            System.out.println("Cannot find boat " + wholeBoatName);
            System.out.println("");
        }
        else{
            do{
                lookingForName = fleet.get(boatIndex).getName();
                boatIndex++;
            }while(!lookingForName.equals(wholeBoatName));

            fleet.remove(boatIndex - SINGLE );

            System.out.println("");
        }
    }
    //-----------------------------------------------------------------------
    public static void boatExpenses(ArrayList<Boat>fleet) {

        //--This method either accepts or denies boat expenses
        String boatToSpendOn;
        String wholeBoatToSpendOn;
        int index;
        int boatCounter = ZERO;
        String lookingForName;
        int boatIndex = ZERO;
        double amountToSpend;
        int stringIndex;
        int nameIndex;

        keyboard.nextLine();

        //--Here the user selects a boat to spend money on
        System.out.print("Which boat do you want to spend on? : ");
        boatToSpendOn = keyboard.nextLine();

        //--The inputted boat name is fixed to start with upper case letters
        String[] boatNameArray = boatToSpendOn.split(" ");
        for(stringIndex = ZERO; stringIndex <boatNameArray.length; stringIndex++) {
            boatNameArray[stringIndex] = boatNameArray[stringIndex].toLowerCase();
            String firstLetter = boatNameArray[stringIndex].substring(ZERO, 1).toUpperCase();
            String remainingLetters = boatNameArray[stringIndex].substring(1);
            boatNameArray[stringIndex] = firstLetter + remainingLetters;
        }
        wholeBoatToSpendOn = boatNameArray[ZERO];
        for(nameIndex = SINGLE; nameIndex <boatNameArray.length; nameIndex++) {
            wholeBoatToSpendOn += " ";
            wholeBoatToSpendOn += boatNameArray[nameIndex];
        }


        //--This for loop helps find if the name exists
        for (index = ZERO; index < fleet.size(); index++) {
            lookingForName = fleet.get(index).getName();
            if (!lookingForName.equals(wholeBoatToSpendOn)) {
                boatCounter = boatCounter;
            } else {
                boatCounter++;
            }
        }

        //--This if else statement either tells the user the boat cannot be found if the name doesn't exist
        //--or prompts the user for an amount of money to spend if the boat does exist
        if (boatCounter == ZERO) {
            System.out.println("Cannot find boat " + wholeBoatToSpendOn);
            System.out.println("");
        } else {
            do {
                lookingForName = fleet.get(boatIndex).getName();
                boatIndex++;
            } while (!lookingForName.equals(wholeBoatToSpendOn));

            System.out.print("How much do you want to spend? : ");
            amountToSpend = keyboard.nextDouble();

            //--This variable helps keep track of how much is left to be spent on the boat in question
            double subtractFromPurchasePrice = fleet.get(boatIndex - 1).getPurchasePrice() - (fleet.get(boatIndex - 1).getExpenses());

            //--This if else statement either accepts or denies the expense based on if there is money left to spend
            if(subtractFromPurchasePrice > amountToSpend){
                fleet.get(boatIndex - 1).setExpenses(amountToSpend);
                System.out.println("Expense authorized, $" + fleet.get(boatIndex - 1).getExpenses() + " spent.");
                System.out.println("");
            }
            else{
                System.out.printf("Expense not permitted, only $%.2f", subtractFromPurchasePrice);
                System.out.println(" left to spend.");
                System.out.println("");
            }

        }
    }
    //-----------------------------------------------------------------------

}
