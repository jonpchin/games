// Jonathan Chin
// 9/11/15
// Simple text based blackjack game with betting chips and highscore.
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define TOTAL 52

typedef struct{
    int value;
    char suit[10];
    char face[10];
}Card;

void init(Card deck[]);
void shuffle(Card deck[]);
int drawDealerCard(Card deck[], int topCard);
int hit(int topCard, Card deck[], int score);
int stand(int score, int dealerCard, int topCard, Card deck[], int *chips, int bet);
void checkHighScores();
void recordHighScore();
void clearHighScore();
int betChips(int chips);
void printDeck(Card deck[]);
void blackjack(int bet, int *chips);
int validateInput();

int main(){

    Card deck[TOTAL];
    //initializes deck
    init(deck);
    char name[30];

    int chips = 1000;
    printf("Welcome to Blackjack!\n");
    //save the name for the highscore
    printf("What is your name?\n");
    fgets(name, 28, stdin);
    printf("Hello %sYour goal is to get as close to 21 without going over.\n", name);
    printf("Type 'h' for hit. Good Luck!\n");

    while(1){
        if(chips==0){
            printf("You ran out of chips. Game Over.\n");
            return 0;
        }
        printf("What would you like to do?\n");
        printf("1. Deal hand\n");
        printf("2. Check high score\n");
        printf("3. Record high score\n");
        printf("4. Clear high score\n");
        printf("5. Quit\n");

        int value;
        //validating user input
        value = validateInput();
        if(value == -1){
            printf("Please enter a valid choice.\n");
        }
        else if(value==1){
            //prevent card counting by shuffling deck and reseting top of card to 52 for every game
            shuffle(deck);
            //prompts user how many chips he wants to bet
            int bet = betChips(chips);
            //index of top card use to keep track of which card to draw out of the deck.
            int topCard = TOTAL-1;
            int score = 0;

            //deals first card to the dealer face up and the second card to the player
            int dealerCard = drawDealerCard(deck, topCard);
            topCard--;
            score = hit(topCard, deck, score);

            while(1){
                char response[3];

                if(score > 21){
                    printf("You scored %d. You busted!\n", score);
                    chips = chips - bet;
                    printf("You lost %d chips. You now have %d chips.\n", bet, chips);
                    break;
                }
                else if(score==21){
                    blackjack(bet, &chips);
                    break;
                }

                printf("You have %d points\n", score);
                printf("What would you like to do? hit or stand? \n");
                //need to implement code to handle incorrect input such as special characters/digits
                scanf("%1s", response);
                //remove trailing stdin in order to be compatible with previous validateInput function
                scanf ("%*[^\n]");
                scanf ("%*c");

                if(strcmp(response, "h")==0){
                    score = hit(topCard, deck, score);
                }
                else{
                    topCard = stand(score, dealerCard, topCard, deck, &chips, bet);
                    break;
                }
                topCard--;
            }
        }
        else if(value==2){
            checkHighScores();
        }
        else if (value==3){
            recordHighScore(chips, name);
        }
        else if(value == 4){
            clearHighScore();
        }
        else if(value == 5){
            printf("Goodbye!\n");
            break;
        }
        else {
            printf("Please enter a valid choice.\n");
        }
    }
    return 0;
}

//initalizes deck to store all 52 cards
void init(Card deck[]){
    int i;
    //intitalize the faces of all cards to NONE.
    for(i=0; i<TOTAL; i++){
        strcpy(deck[i].face, "NONE");
    }
    //k will be used to keep track of the index of each card in the deck
    int k=0;
    //index of cards are from 1 to 52.
    for(i=1; i<=4; i++){
        int j;
        for(j=1; j<=13; j++){
            //intitalizing the suits of Hearts, Diamonds, Clubs and Spades
            switch(i){
                case 1:
                    strcpy(deck[k].suit, "Hearts");
                    break;
                case 2:
                    strcpy(deck[k].suit, "Diamonds");
                    break;
                case 3:
                    strcpy(deck[k].suit, "Clubs");
                    break;
                case 4:
                    strcpy(deck[k].suit, "Spades");
                    break;
                default:
                    break;
            }
            //For Ace when we get a value of 1 we will allow user to choose 11
            //1 will be the default value for ace.
            if(j==1){
                deck[k].value = 1;
                strcpy(deck[k].face, "Ace");
            }
            //card values 2 through 10.
            else if(j<=10){
                deck[k].value = j;
            }
            //card values Jack, Queen, King
            else{
                deck[k].value = 10;
                //faces are Jack, Queen and King
                switch(j){
                    case 11:
                        strcpy(deck[k].face, "Jack");
                        break;
                    case 12:
                        strcpy(deck[k].face, "Queen");
                        break;
                    case 13:
                        strcpy(deck[k].face, "King");
                        break;
                    default:
                        break;
                }
            }
            k++;
        }
    }
}

//shuffles deck of 52 cards.
void shuffle(Card deck[]){
    //seed random generator
    srand(time(NULL));

    int i;
    Card temp;
    //randomly pick two cards and swap them. Repeat 99 times.
    for(i=0; i<100; i++){
        //random number between 0 and 51 for the index of the deck
        int random1 = rand()%TOTAL;
        int random2 = rand()%TOTAL;

        //swapping
        temp=deck[random1];
        deck[random1]=deck[random2];
        deck[random2]=temp;
    }
}

//for testing purposes to see if the deck is shuffled
void printDeck(Card deck[]){
    int i;
    for(i=0; i<TOTAL; i++){
        //if a card does not have a face then print its value instead
        if(strcmp(deck[i].face, "NONE")== 0){
            printf("%d of %s\n", deck[i].value, deck[i].suit);
        }
        else{
           printf("%s of %s\n", deck[i].face, deck[i].suit);
        }
    }
}

//read highscores.txt
void checkHighScores(){
    FILE *ofp = fopen("highscores.txt", "r");
    char line[100];

    while(fgets(line, 98, ofp) != NULL){
        printf("%s", line);
    }
    fclose(ofp);
}

//write to file for the highscores.
void recordHighScore(int score, char name[30]){

    FILE *ofp = fopen("highscores.txt", "a");
    fprintf(ofp, "%d %s", score, name);
    printf("Your score of %d has been recorded.\n", score);
    fclose(ofp);
}

//clears the contents of the highscores.txt
void clearHighScore(){
    fclose(fopen("highscores.txt", "w"));
    printf("Highscore board has been cleared!\n");
}

//draws the top card of the deck. Returns the score of the player
int hit(int topCard, Card deck[], int score){

    int response;

    if(strcmp(deck[topCard].face, "NONE")== 0){
        printf("You hit and draw a %d of %s\n", deck[topCard].value, deck[topCard].suit);
    }
    else{
        printf("You hit and draw a %s of %s\n", deck[topCard].face, deck[topCard].suit);
        //prompts user for the value of Ace when it is drawn from the deck
        if(strcmp(deck[topCard].face, "Ace")==0){
            printf("Would you like the Ace to count as 1 or 11? Type '1' or '11'\n");
            response = validateInput();

            //checks if user enter correct response for Ace. If not then the Ace will be 1 by default.
            //Still nee to add error handling for strings
            if(response == 1 || response == 11 ){
                score = score + response;
                return score;
            }
        }
    }
    score = score + deck[topCard].value;
    return score;
}

//the dealerCard drawn at the start of the game will be used for the dealer's hand
//the return value is the index of the card on the top of the deck
//the dealer will keeping drawing cards until he busts or he gets a score equal to or higher then the player
//if the player has 15 or less points the dealer will keeping hitting until he is strictly higher then the player
//or busts.
//this may seem unfair to the player but the casino needs to make profit by skewing te odds in their favor!
int stand(int score, int dealerCard, int topCard, Card deck[], int *chips, int bet){
    int dealerScore = dealerCard;
    if(score <= 15){
        while(dealerScore<score){
            dealerScore = dealerScore + deck[topCard].value;
            topCard--;
        }
    }
    else{
        while(dealerScore<=score){
            dealerScore = dealerScore + deck[topCard].value;
            topCard--;
        }
    }
    if(dealerScore>21){
        printf("You stood and the dealer busted! You won!\n");
        *chips = *chips + bet;
        printf("You won %d chips! You now have %d chips.\n", bet, *chips);
    }
    else{
        printf("You stood and the dealer scored %d and you scored %d. You lost.\n", dealerScore, score);
        *chips = *chips - bet;
        printf("You lost %d chips. You now have %d chips.\n", bet, *chips);
    }
    return topCard;
}

//the first card drawn by the dealer during the start of the game. This card is faced up so the player can see it.
//the returned value is the value of the card the dealer draws
int drawDealerCard(Card deck[], int topCard){

    if(strcmp(deck[topCard].face, "NONE")== 0){
        printf("The dealer draws a %d of %s\n", deck[topCard].value, deck[topCard].suit);
    }
    else{
        printf("The dealer draws a %s of %s\n", deck[topCard].face, deck[topCard].suit);
    }
    return deck[topCard].value;
}

//if a player scores 21, then player automatically wins and the dealer cannot draw his cards. The player also
//wins 2 times his bet. Usually its 2.5 times the bet but the casino is greedy!
void blackjack(int bet, int *chips){
    printf("You scored 21! You got blackjack!\n");
    *chips = *chips + (bet*2);
    printf("You won %d chips! You now have %d chips.\n", bet*2, *chips);
}

//This function makes sure a valid amount of chips are entered by user.
int betChips(int chips){

    int value;
    printf("You have %d chips. How many chips would you like to bet?\n", chips);
    //getchar is used here to have the stdin pause when calling fgets.

    while(1){

        value=validateInput();
        //if the user bets more then zero chips and he has more or equal chips to the amount he bet the proceed with bet
        if(value>0 && value <= chips){
            return value;
        }
        else{
            printf("Please enter a valid amount of chips.\n");
        }
    }
}

//powerful function to validate input
int validateInput(){
    char bet[10];
    int temp;
    int value;
    char ignore;

    fgets(bet, 8, stdin);
    temp = sscanf(bet, "%d %c", &value, &ignore);
    if(temp==1){
        return value;
    }
    else{
        return -1;
    }
}
