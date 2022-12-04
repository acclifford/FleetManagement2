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
    public static final int MAX_BOAT_LENGTH = 100;
    public static final double MAX_PURCHASE_PRICE = 1000000;

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

        theType = type.valueOf(attributes[0]);
        String name = attributes[1];
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
        int index2 = 0;
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

        //--ADD TOTALS

        int index = 0;

        System.out.println(" ");
        System.out.println("Fleet report:");

        for( index = 0; index < fleet.size(); index++) {
            System.out.println(fleet.get(index));

       }

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

        System.out.print("Which boat would you like to remove? : ");
        boatName = keyboard.next();
        boatName = boatName.toLowerCase();
        String firstLetter = boatName.substring(0, 1).toUpperCase();
        String remainingLetters = boatName.substring(1);
        boatName = firstLetter + remainingLetters;

        if (!fleet.contains(boatName)) {
            System.out.println("Cannot find boat " + boatName);
        } else {
            int index = 0;
            do {
                if (!(fleet.get(index).getName().equals(boatName))) {
                    index++;
                }
            } while (!(fleet.get(index).getName().equals(boatName)));
            fleet.remove(index);

            fleet.remove(index);

        }


    }
    //-----------------------------------------------------------------------
}
