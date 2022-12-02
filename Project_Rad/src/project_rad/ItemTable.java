
package project_rad;


public class ItemTable {
    String item_code;
    String item_name,address,availability;    


    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
    

    public String getItem_code() {
        return item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getAddress() {
        return address;
    }

    public String getAvailability() {
        return availability;
    }
   
    public ItemTable(String item_code, String item_name, String address, String availability) {
        this.item_code = item_code;
        this.item_name = item_name;
        this.address = address;
        this.availability = availability;
    }
}
