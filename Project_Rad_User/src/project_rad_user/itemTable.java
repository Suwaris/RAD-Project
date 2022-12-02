
package project_rad_user;


public class itemTable {

    String item_name,address,availability ;   
    private String itemName;


    public void setItem_name(String item_name) {
        this.itemName = item_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getItem_name() {
        return itemName;
    }

    public String getAddress() {
        return address;
    }

    public String getAvailability() {
        return availability;
    }

    public itemTable(String item_code, String item_name, String address, String availability) {
        this.itemName = item_name;
        this.address = address;
        this.availability = availability;
    }

   
}

    

