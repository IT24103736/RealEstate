package Model;

public class Property {
    private String propertyID;
    private String address;
    private double price;
    private boolean isForSale;

    //Constructions
    public Property(String propertyID, String address, double price, boolean isForSale) {
        this.propertyID = propertyID;
        this.address = address;
        this.price = price;
        this.isForSale = isForSale;
    }

    //Getters
    public String getPropertyID() {
        return propertyID;
    }
    public String getAddress() {
        return address;
    }
    public double getPrice() {
        return price;
    }
    public boolean isForSale() {
        return isForSale;
    }


    //Setters
    public void setPropertyID(String propertyID){
        this.propertyID = propertyID;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPrice(double price) {
        if (price<0){
            System.out.println("Price cannot be negative");
        }
        this.price = price;
    }
    public void setForSale(boolean isForSale) {
        this.isForSale = isForSale;
    }

}
