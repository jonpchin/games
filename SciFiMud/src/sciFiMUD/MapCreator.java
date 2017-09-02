//Jonathan Chin

package scifimud;

import rooms.CentralHub;
import rooms.Factory;
import rooms.Secret;
import rooms.Sewers;
import rooms.ThePit;
import rooms.TrainStation;
import rooms.Wastelands;
import static scifimud.ClassCreator.className;
import static scifimud.ConnectPlayer.factoryMap;
import static scifimud.ConnectPlayer.hubMap;
import static scifimud.ConnectPlayer.mainMap;
import static scifimud.ConnectPlayer.secretMap;
import static scifimud.ConnectPlayer.sewerMap;
import static scifimud.ConnectPlayer.trainMap;
import static scifimud.ConnectPlayer.wastelandsMap;

public class MapCreator {
    
    //x, y, and z coordinates for all the maps
    static final int ThePitX = 100;
    static final int ThePitY = 120;
    static final int ThePitZ = 5;
    
    static final int CentralHubX = 50;
    static final int CentralHubY = 200;
    static final int CentralHubZ = 3;
    
    static final int FactoryX = 150;
    static final int FactoryY = 80;
    static final int FactoryZ = 40;
    
    static final int SewersX = 200;
    static final int SewersY = 230;
    static final int SewersZ = 10;
    
    static final int TrainStationX = 200;
    static final int TrainStationY = 10;
    static final int TrainStationZ = 10;
    
    static final int WastelandsX = 300;
    static final int WastelandsY = 350;
    static final int WastelandsZ = 20;
    
    static final int SecretX = 5;
    static final int SecretY = 5;
    static final int SecretZ = 5;
    
    public static void initializeMap(String location){
        
        int i;
        int j;
        int k;
        
        //later on we will inititalize map rooms individually.
        //For now we mass intitalize rooms to get a basic working map
        switch(location){
            case "The Pit":
                mainMap = new ThePit[ThePitX][ThePitY][ThePitZ];
                className.setArea(mainMap);
                for(i=0; i<ThePitX; i++){
                    for(j=0; j<ThePitY; j++){
                        for(k=0; k<ThePitZ; k++){
                            mainMap[i][j][k] = new ThePit("The Pit", "You look in horror as dead bodies lay everywhere. Something horrific must have happened.", (100*i)+(10*j)+k, i, j, k );                  
                        }
                    }      
                }
                System.out.println("You have entered The Pit. Dead bodies of fallen heros lay everywhere. Will you join them or will you survive?");
                
                break;
            case "Central Hub":
                hubMap = new CentralHub[CentralHubX][CentralHubY][CentralHubZ];       
                className.setArea(hubMap);
                
                for(i=0; i<CentralHubX; i++){
                    for(j=0; j<CentralHubY; j++){
                        for(k=0; k<CentralHubZ; k++){
                            hubMap[i][j][k] = new CentralHub();           
                        }
                    }  
                }
                System.out.println("You have entered The Hub. The center of information and exchange.");
                break;
                
            case "Factory":
                factoryMap = new Factory[FactoryX][FactoryY][FactoryZ];
                className.setArea(factoryMap);
                
                for(i=0; i<FactoryX; i++){
                    for(j=0; j<FactoryY; j++){
                        for(k=0; k<FactoryZ; k++){
                            factoryMap[i][j][k] = new Factory();       
                        }
                    }     
                }
                System.out.println("Levers and cranks churn as you enter the factory.");
                break;
                
            case "Sewers":
                sewerMap = new Sewers[SewersX][SewersY][SewersZ];
                className.setArea(sewerMap);
                
                for(i=0; i<SewersX; i++){
                    for(j=0; j<SewersY; j++){
                        for(k=0; k<SewersZ; k++){
                            sewerMap[i][j][k] = new Sewers();       
                        }
                    }   
                }
                System.out.println("This place really reeks! Lets get out of here before you pass out!");
                break;
                
            case "TrainStation":
                trainMap = new TrainStation[TrainStationX][TrainStationY][TrainStationZ];
                className.setArea(trainMap);
                
                for(i=0; i<TrainStationX; i++){
                    for(j=0; j<TrainStationY; j++){
                        for(k=0; k<TrainStationZ; k++){
                            trainMap[i][j][k] = new TrainStation();                    
                        }
                    }          
                }
                System.out.println("An old underground train station still remains here.");
            case "Wastelands":
                wastelandsMap = new Wastelands[WastelandsX][WastelandsY][WastelandsZ];
                className.setArea(wastelandsMap);
                
                for(i=0; i<WastelandsX; i++){
                    for(j=0; j<WastelandsY; j++){
                        for(k=0; k<WastelandsZ; k++){
                            wastelandsMap[i][j][k] = new Wastelands();                   
                        }
                    }               
                }
                System.out.println("You are either very brave or very foolish for venturing out here.");
                break;
                
            case "Secret":
                secretMap = new Secret[SecretX][SecretX][SecretX];
                className.setArea(secretMap);
                
                for(i=0; i<SecretX; i++){
                    for(j=0; j<SecretY; j++){
                        for(k=0; k<SecretZ; k++){
                            secretMap[i][j][k] = new Secret();
                        }
                    }          
                }
                System.out.println("Only a few have ever discovered this place.");
                break;        
                
            default:
                System.out.println("Error intitalizing map. Invalid map name please contact admin.");
                System.exit(0);
                break;
        }
    }
}
