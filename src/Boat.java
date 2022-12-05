public class Boat {
    //-----------------------------------------------------------------------
    public enum type{POWER,SAILING};

    FleetManagement.type theType;
    public String name;
    public int manufacturer;
    public String makeAndModel;
    public int length;
    public double purchasePrice;
    public double expenses;

    public Boat(){

        //--This is a default constructor for a Boat object
        //--It sets all the objects to either zero or null as default
        theType = null;
        name = null;
        manufacturer = 0;
        makeAndModel = null;
        length = 0;
        purchasePrice = 0;
        expenses = 0;
    }
    //-----------------------------------------------------------------------
    public Boat(FleetManagement.type theType, String name, int manufacturer, String makeAndModel, int length, double purchasePrice) {

        //--This constructor changes the values of the Boat object upon input of attributes
        //--expenses will stay at zero for now
        this.theType = theType;
        this.name = name;
        this.manufacturer = manufacturer;
        this.makeAndModel = makeAndModel;
        this.length = length;
        this.purchasePrice = purchasePrice;
        expenses = 0;
    }

    //-----------------------------------------------------------------------
    public String toString() {

        //--This String will be printed when toString is called
        return ("    " + theType + " " + getName() + "                                 "
                + getManufacturer() + " " + getMakeAndModel() + "  :  Paid $ "
                + getPurchasePrice() + "  :  Spent $     " + expenses);
    }

    //-----------------------------------------------------------------------
    public String getName() {

        //--if the name is needed, this method can send the name of the boat object
        return this.name;
    }
    //-----------------------------------------------------------------------
    public void setName(String name){

        //--if the name needs to be set, this method can set the name of the object
        this.name = name;
    }
    //-----------------------------------------------------------------------
    public int getManufacturer() {

        //--if the manufacturer is needed, this method can send the manufacturer of the boat object
        return this.manufacturer;
    }

    //-----------------------------------------------------------------------
    public void setManufacturer(int manufacturer){

        //--if the manufacturer needs to be set, this method can set the manufacturer of the object
        this.manufacturer = manufacturer;
    }

    //-----------------------------------------------------------------------
    public String getMakeAndModel() {

        //--if the make and model is needed, this method can send the make and model of the boat object
        return this.makeAndModel;
    }
    //-----------------------------------------------------------------------
    public void setMakeAndModel(String makeAndModel){

        //--if the make and model needs to be set, this method can set the make and model of the object
        this.makeAndModel = makeAndModel;
    }
    //-----------------------------------------------------------------------
    public int getLength() {

        //--if the length is needed, this method can send the length of the boat object
        return this.length;
    }
    //-----------------------------------------------------------------------
    public void setLength(int length){

        //--if the length needs to be set, this method can set the length of the object
        this.length = length;
    }

    //-----------------------------------------------------------------------
    public double getPurchasePrice() {

        //--if the purchase price is needed, this method can send the purchase of the boat object
        return purchasePrice;
    }
    //-----------------------------------------------------------------------
    public void setPurchasePrice(int purchasePrice){

        //--if the purchase price needs to be set, this method can set the purchase price of the object
        this.purchasePrice = purchasePrice;

    }
    //-----------------------------------------------------------------------
    public double getExpenses() {

        //--if the expenses are needed, this method can send the expenses of the boat object
        return this.expenses;
    }
    //-----------------------------------------------------------------------
    public void setExpenses(double amountToSpend){

        //--if the expenses need to be set, this method can set the expenses of the object
        this.expenses += amountToSpend;
    }

    //-----------------------------------------------------------------------
}

