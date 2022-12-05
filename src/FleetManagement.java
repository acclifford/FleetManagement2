import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
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

        ArrayList<Boat>fleet = new ArrayList<Boat>();
        String path = "C:\\Users\\Ailis\\Desktop\\CSC120_LAB\\FleetData.csv";
        initFromCSVFile(path, fleet);

        menu(fleet);

    }
    //-----------------------------------------------------------------------

    private static ArrayList<Boat> initFromCSVFile(String path, ArrayList<Boat>fleet) {

        String line = "";
        Boat newBoat = new Boat();

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
        return(fleet);
    }
    //-----------------------------------------------------------------------
    public static Boat createBoat(String[] attributes){

        type theType;

        theType = type.valueOf(attributes[ZERO]);
        String name = attributes[SINGLE];
        int manufacturer = Integer.parseInt(attributes[2]);
        String makeAndModel = attributes[3];
        int length = Integer.parseInt(attributes[4]);
        double purchasePrice = Double.parseDouble(attributes[5]);

        return new Boat (theType,name,manufacturer,makeAndModel,length,purchasePrice);
    }
    //-----------------------------------------------------------------------
    private static void menu(ArrayList<Boat>fleet) {

        char menuSelection;
        int index;
        int index2 = ZERO;
        Boat myBoat = new Boat();
        String boatName;
        double newExpense;

        System.out.println("");
        System.out.println("Welcome to the Fleet Management System");
        System.out.println("---------------------------------------");
        System.out.println("");

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

        double grandTotalPaid = ZERO;
        double grandTotalExpenses = ZERO;
        int expensesIndex;

        for(expensesIndex = ZERO; expensesIndex < fleet.size(); expensesIndex++){
            grandTotalPaid += fleet.get(expensesIndex).getPurchasePrice();
            grandTotalExpenses += fleet.get(expensesIndex).getExpenses();
        }

        int index = ZERO;

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

        String newBoatData;
        Boat newBoat = new Boat();

        System.out.print("Please enter the new boat CSV data : ");
        newBoatData = keyboard.next();
        String[] values = newBoatData.split(",");
        newBoat = createBoat(values);
        fleet.add(newBoat);

        System.out.println("");

    }
    //-----------------------------------------------------------------------
    public static void removeBoat(ArrayList<Boat>fleet) {

        String boatName;
        String lookingForName;
        String wholeBoatName = null;
        int boatCounter = ZERO;
        int index = ZERO;
        int boatIndex = ZERO;
        int stringIndex = ZERO;
        int nameIndex = ZERO;

        keyboard.nextLine();


        System.out.print("Which boat would you like to remove? : ");
        boatName = keyboard.nextLine();
        //--let's play with something
        String[] boatNameArray = boatName.split(" ");
        for(stringIndex = ZERO; stringIndex <boatNameArray.length; stringIndex++){
            boatNameArray[stringIndex] = boatNameArray[stringIndex].toLowerCase();
            String firstLetter = boatNameArray[stringIndex].substring(ZERO, 1).toUpperCase();
            String remainingLetters = boatNameArray[stringIndex].substring(1);
            boatNameArray[stringIndex] = firstLetter + remainingLetters;
        }
        wholeBoatName = boatNameArray[ZERO];
        for(nameIndex = SINGLE; nameIndex <boatNameArray.length; nameIndex++){
            wholeBoatName += " ";
            wholeBoatName += boatNameArray[nameIndex];

        }

        for(index = ZERO; index < fleet.size(); index++){
            lookingForName = fleet.get(index).getName();
            if(!lookingForName.equals(wholeBoatName)){
                boatCounter = boatCounter;
            } else{
                boatCounter++;
            }
        }

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

        System.out.print("Which boat do you want to spend on? : ");
        boatToSpendOn = keyboard.nextLine();

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


        for (index = ZERO; index < fleet.size(); index++) {
            lookingForName = fleet.get(index).getName();
            if (!lookingForName.equals(wholeBoatToSpendOn)) {
                boatCounter = boatCounter;
            } else {
                boatCounter++;
            }
        }

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

            double subtractFromPurchasePrice = fleet.get(boatIndex - 1).getPurchasePrice() - (fleet.get(boatIndex - 1).getExpenses());


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
