
package project_rad_user;


public class selected_ItemTable {

    private String selected_address,seleced_itemName;

    public String getSelected_address() {
        return selected_address;
    }

    public String getSeleced_itemName() {
        return seleced_itemName;
    }

    public void setSelected_address(String selected_address) {
        this.selected_address = selected_address;
    }

    public void setSeleced_itemName(String seleced_itemName) {
        this.seleced_itemName = seleced_itemName;
    }
    
    
    

    public selected_ItemTable(String seleced_itemName, String selected_address) {
        this.selected_address = selected_address;
        this.seleced_itemName = seleced_itemName;
        
        System.out.println(seleced_itemName);
    }
    
    
}
