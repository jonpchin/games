//Jonathan Chin
package scifimud;

import Equipment.Artifact;
import Equipment.Drink;
import Equipment.Food;
import Equipment.Head;
import Equipment.Item;
import Equipment.Pants;
import Equipment.Shoes;
import Equipment.Torso;
import Equipment.Weapons;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static scifimud.ClassCreator.className;

//this class is responsible for managing players inventory and creating all objects and items in the game
public class ObjectCreator {
    
    //total number of items in each text file
    //everytime you add items in the text file you need to update this.
    //Make sure to alphebetize files
    // 2,3,0,0,0,2,0,4
    public static int TOTAL_DRINKS;
    public static int TOTAL_FOOD;
    public static int TOTAL_HEAD;
    public static int TOTAL_ARTIFACTS;
    public static int TOTAL_PANTS;
    public static int TOTAL_SHOES;
    public static int TOTAL_TORSO;
    public static int TOTAL_WEAPONS;
    
    public static ArrayList<String> listofDrinks = new ArrayList<>();
    public static ArrayList<String> listofFood = new ArrayList<>();
    public static ArrayList<String> listofHead = new ArrayList<>();
    public static ArrayList<String> listofObjects = new ArrayList<>();
    public static ArrayList<String> listofPants = new ArrayList<>();
    public static ArrayList<String> listofShoes = new ArrayList<>();
    public static ArrayList<String> listofTorso = new ArrayList<>();
    public static ArrayList<String> listofWeapons = new ArrayList<>();
    
    //inventory is a string read from file and it parses that string to create all the objects and places it in players inventory
    public static ArrayList<Item> createInventory(String inventory, ArrayList<Item> playerInventory) throws IOException{
        
        //if the inventory is empty then we set it to null
        if(inventory.equals("empty")){
            playerInventory = null;     
        }
        else{
            String[] newInventory  = inventory.split(", ");
            int i;
            for(i=0; i<newInventory.length; i++){
                playerInventory = searchInventoryDatabase(newInventory[i], "drinks", TOTAL_DRINKS, listofDrinks, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "food", TOTAL_FOOD, listofFood, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "head", TOTAL_HEAD, listofHead, playerInventory);   
                playerInventory = searchInventoryDatabase(newInventory[i], "artifacts", TOTAL_ARTIFACTS, listofFood, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "pants", TOTAL_PANTS, listofPants, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "shoes", TOTAL_SHOES, listofShoes, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "torso", TOTAL_TORSO, listofTorso, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "weapons", TOTAL_WEAPONS, listofWeapons, playerInventory);     
                }          
            }
        return playerInventory;
            
    }
    //creates all the equipment that the player is currently wearing
    public static void createEquipment(String weapon, String torso, String head, String pants, String shoes){
        //creating an empty object if no match was found
        Item empty = new Item();
        empty.setName("Nothing");
        if(weapon.equals("Nothing")){
            className.setWeapon(empty);
        }
        else{
            searchEquipment(weapon, "weapon", TOTAL_WEAPONS, listofWeapons);
        }
        if(torso.equals("Nothing")){
            className.setTorso(empty);
        }
        else{
            searchEquipment(torso, "torso", TOTAL_TORSO, listofTorso);
        }
        if(head.equals("Nothing")){
            className.setHead(empty);
        }
        else{
            searchEquipment(head, "head", TOTAL_HEAD, listofHead);
        }
        if(pants.equals("Nothing")){
            className.setPants(empty);
        }
        else{
            searchEquipment(pants, "pants", TOTAL_PANTS, listofPants);
        }
        if(shoes.equals("Nothing")){
            className.setShoes(empty);
        }
        else{
            searchEquipment(shoes, "shoes", TOTAL_SHOES, listofShoes);
        }
    }
    //searches the inventory database for the equipment
    public static void searchEquipment(String item, String type, int total, ArrayList<String> arrayList){
        int low=0;
        int high = total-1;
        int mid;

        while(low<= high){
            mid=(high+low)/2;
            //if a partial match is found return the word
            if(arrayList.get(mid).startsWith(item)){
                switch(type){
                    case "head":
                        className.setHead(createHead(arrayList.get(mid)));
                        break;
                    case "pants":
                        className.setPants(createPants(arrayList.get(mid)));
                        break;
                    case "shoes":
                        className.setShoes(createShoes(arrayList.get(mid)));
                        break;
                    case "torso":
                        className.setTorso(createTorso(arrayList.get(mid)));
                        break;
                    case "weapon":
                        className.setWeapon(createWeapon(arrayList.get(mid)));
                        break;
                    default:
                        System.out.println("Error invalid equipment. Please contact admin.");
                        break;
                }      
            }
            //if the word being searched comes before the word in the dictionary
            //then the you want to search an earlier word
            if(item.compareTo((String) arrayList.get(mid))<0){
                high=mid-1;            
            }
            //if the word being searched comes after the word in the dictionary 
            //then you wan tto search for a later word
            else if(item.compareTo((String) arrayList.get(mid))>0){
                low=mid+1;         
            }
        }
    }

    //takes all objects in inventory and returns a string of all the objects.
    public static  String getInventory(ArrayList<Item> inventory){
        int i;
        String newInventory = "";
        if(inventory!=null){   
            for(i=0; i<inventory.size(); i++){
                newInventory = newInventory + inventory.get(i).getName() + ", ";
            }
            return newInventory;
        }
        //if the inventory is null this will be returned
        return "empty"; 
    }
    //searches the inventory database looking for piece of equipment based on its name, if the name is found
    //in the database this function will call createObject to create the item or weapon and it will be placed
    //in the players inventory. If this function returns true that means the object was sucessfully created
    //else it returns false.All the textfiles will be alphabetically sorted based on 
    //the name of the object so a binary search can be implemented for log n run time.
    // alphabetizer.flap.tv/ is the website that can sort a list in alphebetical order based on each line
    public static ArrayList<Item> searchInventoryDatabase(String name, String fileName, int total, ArrayList<String> arrayList, ArrayList<Item> playerInventory) throws FileNotFoundException, IOException {
        
        int low=0;
        int high = total-1;
        int mid;

        while(low<= high){
            mid=(high+low)/2;
            //if a partial match is found return the word
            if(arrayList.get(mid).startsWith(name)){
                switch(fileName){
                    case "drinks":
                        playerInventory.add(createDrink(arrayList.get(mid)));
                        break;
                    case "food":
                        playerInventory.add(createFood(arrayList.get(mid)));
                        break;
                    case "head":
                        playerInventory.add(createHead(arrayList.get(mid)));
                        break;
                    case "objects":
                        playerInventory.add(createArtifact(arrayList.get(mid)));
                        break;
                    case "pants":
                        playerInventory.add(createPants(arrayList.get(mid)));
                        break;
                    case "shoes":
                        playerInventory.add(createShoes(arrayList.get(mid)));
                        break;
                    case "torso":
                        playerInventory.add(createTorso(arrayList.get(mid)));
                        break;
                    case "weapons":
                        playerInventory.add(createWeapon(arrayList.get(mid)));
                        break;
                    default:
                        System.out.println("Error invalid equipment category. Please contact admin.");
                        break;
                }      
            }
            //if the word being searched comes before the word in the dictionary
            //then the you want to search an earlier word
            if(name.compareTo((String) arrayList.get(mid))<0){
                high=mid-1;
                
            }
            //if the word being searched comes after the word in the dictionary 
            //then you wan tto search for a later word
            else if(name.compareTo((String) arrayList.get(mid))>0){
                low=mid+1;        
            }
        }
        return playerInventory;
        
    }
    //these functions all create objects/items/weapons/equipment based on the String in one of the files
    //these strings need to be parsed. The item/object is then returned
    public static Item createDrink(String inventory){
        String[] newInventory  = inventory.split(", ");
        Drink drink = new Drink();
        int i = 0;
        drink.setName(newInventory[i++]);
        drink.setHealth(Integer.parseInt(newInventory[i++]));
        drink.setEnergy(Integer.parseInt(newInventory[i++]));
        drink.setSpeed(Integer.parseInt(newInventory[i++]));
        drink.setAttack(Integer.parseInt(newInventory[i++]));
        drink.setDefense(Integer.parseInt(newInventory[i++]));
        drink.setIntelligence(Integer.parseInt(newInventory[i++]));
        drink.setDuration(Integer.parseInt(newInventory[i++]));
        drink.setSpecialEffects(newInventory[i++]);
        drink.setGroup("drink");
        drink.setDescription(newInventory[i]);
  
        return drink;
    }
    public static Item createFood(String inventory){
        String[] newInventory  = inventory.split(", ");
        Food food = new Food();
        int i = 0;
        food.setName(newInventory[i++]);
        food.setHealth(Integer.parseInt(newInventory[i++]));
        food.setEnergy(Integer.parseInt(newInventory[i++]));
        food.setSpeed(Integer.parseInt(newInventory[i++]));
        food.setAttack(Integer.parseInt(newInventory[i++]));
        food.setDefense(Integer.parseInt(newInventory[i++]));
        food.setIntelligence(Integer.parseInt(newInventory[i++]));
        food.setSpecialEffects(newInventory[i++]);
        food.setGroup("food");
        food.setDescription(newInventory[i]);
        
        return food;
    }
    //creates an object or item such as a potion or artifact
    public static Item createArtifact(String inventory){
        String[] newInventory  = inventory.split(", ");
        Artifact artifact = new Artifact();
        int i = 0;
        artifact.setName(newInventory[i++]);
        artifact.setHealth(Integer.parseInt(newInventory[i++]));
        artifact.setEnergy(Integer.parseInt(newInventory[i++]));
        artifact.setSpeed(Integer.parseInt(newInventory[i++]));
        artifact.setAttack(Integer.parseInt(newInventory[i++]));
        artifact.setDefense(Integer.parseInt(newInventory[i++]));
        artifact.setIntelligence(Integer.parseInt(newInventory[i++]));
        artifact.setSpecialEffects(newInventory[i++]);
        artifact.setGroup("artifact");
        artifact.setDescription(newInventory[i]);
        
        return artifact;
    }
    //this is a function that is called to create a weapon
    public static Item createWeapon(String inventory){
        String[] newInventory  = inventory.split(", ");
        Weapons weapon = new Weapons();
        int i = 0;
        weapon.setName(newInventory[i++]);
        weapon.setHealth(Integer.parseInt(newInventory[i++]));
        weapon.setEnergy(Integer.parseInt(newInventory[i++]));
        weapon.setSpeed(Integer.parseInt(newInventory[i++]));
        weapon.setAttack(Integer.parseInt(newInventory[i++]));
        weapon.setDefense(Integer.parseInt(newInventory[i++]));
        weapon.setIntelligence(Integer.parseInt(newInventory[i++]));     
        weapon.setType(newInventory[i++]);    
        weapon.setSpecialEffect(newInventory[i++]);
        weapon.setGroup("weapon");
        weapon.setDescription(newInventory[i]);
 
        return weapon;
    }
    //creates a torso object that a player can wear
    public static Item createTorso(String inventory){
        String[] newInventory  = inventory.split(", ");
        Torso torso = new Torso();
        int i = 0;
        torso.setName(newInventory[i++]);
        torso.setHealth(Integer.parseInt(newInventory[i++]));
        torso.setEnergy(Integer.parseInt(newInventory[i++]));
        torso.setSpeed(Integer.parseInt(newInventory[i++]));
        torso.setAttack(Integer.parseInt(newInventory[i++]));
        torso.setDefense(Integer.parseInt(newInventory[i++]));
        torso.setIntelligence(Integer.parseInt(newInventory[i++]));    
        torso.setSpecialEffect(newInventory[i++]);
        torso.setGroup("torso");
        torso.setDescription(newInventory[i]);
        
        return torso;
    }
    //creates a head object that a player can wear
    public static Item createHead(String inventory){
        String[] newInventory  = inventory.split(", ");
        Head head = new Head();
        int i = 0;
        head.setName(newInventory[i++]);
        head.setHealth(Integer.parseInt(newInventory[i++]));
        head.setEnergy(Integer.parseInt(newInventory[i++]));
        head.setSpeed(Integer.parseInt(newInventory[i++]));
        head.setAttack(Integer.parseInt(newInventory[i++]));
        head.setDefense(Integer.parseInt(newInventory[i++]));
        head.setIntelligence(Integer.parseInt(newInventory[i++]));    
        head.setSpecialEffect(newInventory[i++]);
        head.setGroup("head");
        head.setDescription(newInventory[i]);
        
        return head;
    }
    //creates a shoe object that a player can wear
    public static Item createShoes(String inventory){
        String[] newInventory  = inventory.split(", ");
        Shoes shoes = new Shoes();
        int i = 0;
        shoes.setName(newInventory[i++]);
        shoes.setHealth(Integer.parseInt(newInventory[i++]));
        shoes.setEnergy(Integer.parseInt(newInventory[i++]));
        shoes.setSpeed(Integer.parseInt(newInventory[i++]));
        shoes.setAttack(Integer.parseInt(newInventory[i++]));
        shoes.setDefense(Integer.parseInt(newInventory[i++]));
        shoes.setIntelligence(Integer.parseInt(newInventory[i++]));        
        shoes.setSpecialEffect(newInventory[i++]);
        shoes.setGroup("shoes");
        shoes.setDescription(newInventory[i]);

        return shoes;
    }
    public static Item createPants(String inventory){
        String[] newInventory  = inventory.split(", ");
        Pants pants = new Pants();
        int i = 0;
        pants.setName(newInventory[i++]);
        pants.setHealth(Integer.parseInt(newInventory[i++]));
        pants.setEnergy(Integer.parseInt(newInventory[i++]));
        pants.setSpeed(Integer.parseInt(newInventory[i++]));
        pants.setAttack(Integer.parseInt(newInventory[i++]));
        pants.setDefense(Integer.parseInt(newInventory[i++]));
        pants.setIntelligence(Integer.parseInt(newInventory[i++]));    
        pants.setSpecialEffect(newInventory[i++]);
        pants.setGroup("pants");
        pants.setDescription(newInventory[i]);
        
        return pants;
    }
    
    //this function can be used to reach each text file in equipment package
    //and stores all entries into the corresponding arraylist
    public static void readEquipmentFile(String file, int total, ArrayList<String> arrayList) throws FileNotFoundException{
        
        try(Scanner br = new Scanner(new FileReader("src/Equipment/" + file + ".txt"))){
            
            int i;
            String current_word;
            
            for(i=0; i<total; i++){
                arrayList.add(null);
            }

            for(i=0; i<total; i++){
                current_word= br.nextLine();
                arrayList.set(i, current_word);
            }
            br.close();
        }     
    }
    //counts all lines in text file and assigns their global static total in Equipment textfiles
    public static void countAllFiles() throws FileNotFoundException{
        TOTAL_DRINKS = countLines("drinks");
        TOTAL_FOOD = countLines("food");
        TOTAL_HEAD = countLines("head");
        TOTAL_ARTIFACTS = countLines("artifacts");
        TOTAL_PANTS = countLines("pants");
        TOTAL_SHOES = countLines("shoes");
        TOTAL_TORSO = countLines("torso");
        TOTAL_WEAPONS = countLines("weapons");
    }
    
    public static void testFileCount(){
        System.out.println("Testing file count of all items");
        System.out.println(TOTAL_DRINKS);
        System.out.println(TOTAL_FOOD);
        System.out.println(TOTAL_HEAD);
        System.out.println(TOTAL_ARTIFACTS);
        System.out.println(TOTAL_SHOES);
        System.out.println(TOTAL_TORSO);
        System.out.println(TOTAL_WEAPONS);   
    }
    //counts line based on text file name
    public static int countLines(String file) throws FileNotFoundException{
        //total number of item types such as artifacts, drinks, food, head, pants, shoes, torso, etc
        int count =0;
        
        try(Scanner br = new Scanner(new FileReader("src/Equipment/" + file + ".txt"))){

             while (br.hasNextLine()) {
                count++;
                br.nextLine();
            }
            br.close();
        }
        return count;
    } 
}
