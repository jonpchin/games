/*
 * Jonathan Chin
 * 9/16/15
 */
package scifimud;

import PlayerInformation.EncryptString;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SciFiMUD {

    /**
     * @param args the command line arguments
     * @throws java.net.URISyntaxException
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.GeneralSecurityException
     */
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException, UnsupportedEncodingException, GeneralSecurityException, IOException  {
        // Need to add splash screen login
        System.out.println("Welcome to Digital Wasteland.");
        //reads in name and pass into a text file
        String name;
        Scanner stdin = new Scanner(System.in);     
        while(true){
            System.out.println("What is your name?");
            name = stdin.nextLine();
            //makes sure name has no spaces, numbers, or special charcters and
            //length of name is between 3 to 12 charaters. 
            Pattern pattern = Pattern.compile("[A-Za-z]{3,12}");
            Matcher matcher = pattern.matcher(name);
            if(matcher.matches()){ 
                break;
            }
            else{
                System.out.println("No spaces, numbers, special characters and name must be between length of 3 to 12 characters.");
            }
        }
        //checks to see if the name exists
        if(checkName(name)){
 
            String password;
            System.out.println("What is your password?");
            password = stdin.nextLine();
            if(checkPassword(name, password) == 1){
                System.out.println("Welcome " + name);
                ConnectPlayer player = new ConnectPlayer();
                //connecting player
                player.connect(name);
            }
            else{
                //if failed then user is disconnected to prevent bruteforce hack attemps
                System.out.println("Name or password is incorrect.");
                System.exit(0);
            }
        }
        else{
            System.out.println("That is a good name.");
            //makes user enter password twice to confirm password is correct
            System.out.println("Please enter a password.");
            String password;
            
            while(true){

                password = stdin.nextLine();
                String confirmPassword;
                System.out.println("Please enter your password to confirm again.");
                confirmPassword = stdin.nextLine();
                if(password.equals(confirmPassword)){
                    System.out.printf("Success your passwords matched.");
                    break;
                }
                else{
                    System.out.println("Password did not match. Please enter it again.");
                }
            } 
            System.out.println("Please pick your class.");
            System.out.println("The classes are Cyborg, Nanomedic, Cyber Security Architect, , Time Traveler, and BlackHandRogue");
            System.out.println("Type the abbreviations for the class which are cyborg, nano, cyber, time, black.");
            String classChosen = "dummy";
            //makes sure the player picks a valid class using the abbreviations
            //note only Java version 7 or higher has strings for switch cases. Update Java version if its not working
            int correct = 0;
            while(correct==0){
                classChosen = stdin.nextLine();
                switch(classChosen){
                    case "cyborg":
                        classChosen = "Cyborg";
                        correct = 1;
                        break;
                    case "nano":
                        classChosen = "NanoMedic";
                        correct = 1;
                        break;
                    case "cyber":
                        classChosen = "Cyber Security Architect";
                        correct = 1;
                        break;
                    case "time":
                        classChosen = "Time Traveler";
                        correct = 1;
                        break;
                    case "black":
                        classChosen = "Blackhand Rogue";
                        correct = 1;
                        break;
                    default:
                        System.out.println("Please type in the abbreviation correctly for the class you want");
                        break;
                }
            }
            System.out.println("Saving data please wait...");
            //saving player data to a file
            saveNewPlayer(name, password, classChosen);
            System.out.println("Successfully saved!");
            
            ConnectPlayer player = new ConnectPlayer();
            //connecting player
            player.connect(name);        
        }  
    }
    
    //checks to see if a text file with the name exists
    static boolean checkName(String name) throws URISyntaxException, FileNotFoundException{
        
        //relative file path
        name = "src/PlayerInformation/" + name +".txt";
        File file = new File(name);
        return file.exists() && !file.isDirectory();       
    }
    
    //saves name of user, pass, class, and stats into a file. This is function is only for saving
    //a player who is new for the first time
    static void saveNewPlayer(String name, String password, String className) throws URISyntaxException, FileNotFoundException, UnsupportedEncodingException, GeneralSecurityException{
        
        try (PrintWriter writer = new PrintWriter("src/PlayerInformation/" + name + ".txt", "UTF-8")) {
            //name
            writer.println(name);
      
            //encrypting password
            password = EncryptString.encrypt(password);
            writer.println(password);
            //class 
            writer.println(className);
            //level of player
            writer.printf("%d\n", 1);
            //experience of player
            writer.printf("%d\n", 0);
           
            //map location of player
            writer.println("The Pit");
            //map coordinates where player is located
            writer.printf("%d %d %d\n", 50, 50, 3);
            //amount of bitcoins player is carrying
            writer.printf("%d\n", 0);
            //players health status, ex: healthy, blind, sick, etc. 
            //multiple affliction status will be separated by commas
            writer.println("Normal");
            //players inventory, all items are separated by a comma and will
            //use a strtok to break up the items
            writer.println("Empty");
            
            //players weapon equipment
            writer.println("Nothing");
            //players head equipment
            writer.println("Nothing");
            //players torso equipment
            writer.println("Nothing");
            //players pants equipments
            writer.println("Nothing");
            //players shoe equipment
            writer.println("Nothing");
            
            writer.close();
        }
    }
    
    //checking if players password matches
    static int checkPassword(String name, String password) throws FileNotFoundException, IOException, GeneralSecurityException{
        try(BufferedReader br = new BufferedReader(new FileReader("src/PlayerInformation/" + name + ".txt"))){
            
            String nameInFile = br.readLine();
            String passwordInFile = br.readLine();
            passwordInFile = EncryptString.decrypt(passwordInFile);
            
            if( nameInFile.equals(name) && passwordInFile.equals(password)){
                br.close();
                return 1;
            }              
        }   
        return 0;    
    }  
}
