//Joanthan Chin
package Equipment;

/**
 *
 * @author jonc
 */
//superclass that will be used as the parameter for the players arraylist inventory
public class Item {
    
    private String name;
    private String description;
    //groups will be used to identify if an item is a weapon, torso, drink so the player can wear it on the correct 
    //equipment slot
    private String group;
  
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void displayProperties(){
        
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
