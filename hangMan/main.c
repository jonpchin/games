// Jonathan Chin
// 9/14/15
//Hangman Game

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

void searchDict(char word[]);
int validateInput();
void initWord(int length, char storage[]);
char displayWord(char storage[], int length);
void updateWord(char guess, char storage[], char word[]);
int letterInWord(char guess, char storage[], char word[]);
void displayStick(int lives);
void displayAlreadyGuessed(char guess, char already[30], int counter);

int main(){

    printf("Welcome to hangman!\n");

    while(1){
        printf("What would you like to do?\n");
        printf("1. Play Hangman.\n");
        printf("2. Quit.");

        int value = validateInput();

        int gameOver = 0;
        if(value <= 0 || value >= 3){
            printf("Please enter a valid number.\n");
        }
        else{

            while(1){
                if(gameOver==1){
                    break;
                }

                if(value==1){

                    char word[30];
                    //already guessed letters
                    char already[26];
                    char guess;
                    int correct = 0;
                    int lives=7;
                    //keeps track of how many guesses
                    int counter = 0;

                    searchDict(word);
                    int length = strlen(word);
                    char storage[length];
                    initWord(length, storage);

                    int matches;
                    while(1){

                        guess = displayWord(storage, length);
                        displayAlreadyGuessed(guess, already, counter);
                        counter++;
                        matches = letterInWord(guess, storage, word);

                        if( matches > 0){
                            updateWord(guess, storage, word);
                            correct = correct + matches;
                        }
                        else{
                            printf("Incorrect guess.\n");
                            lives--;
                            displayStick(lives);
                        }
                        if(lives==0){
                            printf("You died.\n");
                            printf("The word was %s\n", word);
                            gameOver = 1;
                            break;
                        }

                        if(correct == length){
                            gameOver = 1;
                            printf("You lived!\n");
                            break;
                        }
                    }
                }

                else if(value==2){
                    printf("Goodbye!");
                    return 0;
                }

            }
        }
    }

    return 0;
}

//searches the dictionary for a random word of 6013 total words and returns the word
void searchDict(char word[]){

    FILE *ofp = fopen("dictionary.txt", "r");

    srand(time(NULL));
    int r = rand()%6013;
    int i;

    //goes to the line before the random number in the file that was chosen
    for(i=0; i<r; i++){
        fscanf(ofp, "%*s");
    }

    fscanf(ofp, "%s", word);
    fclose(ofp);

}

//strong input validation function. Only accepts digits.
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

//displays the word the user is suppose to guess and returns the letter the player guessed
char displayWord(char storage[], int length){
    int i;

    for(i=0; i<length; i++){
        printf("%c ", storage[i]);
    }
    printf("\n");
    printf("Guess a letter.\n");
    char letter;
    scanf(" %c", &letter);
    //alternative to fflush(stdin) that is portable
    scanf ("%*[^\n]");
    scanf ("%*c");

    return letter;
}

void initWord(int length, char storage[]){


    int i;
    //intitalizes blanks
    for(i=0; i<length; i++){
        storage[i] = '_';
    }

}

//updates the storage with all the letters of guess that match in word
void updateWord(char guess, char storage[], char word[]){

    int i;
    int length = strlen(word);
    for(i=0; i<length; i++){
        if(guess == word[i]){
            storage[i] = guess;
        }
    }

}
//checks to see how many letters match the guess in the word. Returns the number of matches
int letterInWord(char guess, char storage[], char word[]){

    int i;
    int total = 0;
    int length = strlen(word);
    for(i=0; i<length; i++){
        if(guess == word[i] && storage[i]=='_'){
            total++;
        }
    }

    return total;
}

//displays the stick figure getting hanged as a user enters an incorrect guess
void displayStick(int lives){

    switch(lives){
        case 6:
            printf("   ___\n");
            printf("  |   |\n");
            printf("      |\n");
            printf("      |\n");
            printf("      |\n");
            printf("      |\n");
            printf("      |\n");
            break;
        case 5:
            printf("   ___\n");
            printf("  |   |\n");
            printf("  O   |\n");
            printf("      |\n");
            printf("      |\n");
            printf("      |\n");
            printf("      |\n");
            break;
        case 4:
            printf("   ___\n");
            printf("  |   |\n");
            printf("  O   |\n");
            printf("  |   |\n");
            printf("  |   |\n");
            printf("      |\n");
            printf("      |\n");
            break;
        case 3:
            printf("   ___\n");
            printf("  |   |\n");
            printf("  O   |\n");
            printf("  |/  |\n");
            printf("  |   |\n");
            printf("      |\n");
            printf("      |\n");
            break;
        case 2:
            printf("   ___\n");
            printf("  |   |\n");
            printf("  O   |\n");
            printf(" \\|/  |\n");
            printf("  |   |\n");
            printf("      |\n");
            printf("      |\n");
            break;
        case 1:
            printf("   ___\n");
            printf("  |   |\n");
            printf("  O   |\n");
            printf(" \\|/  |\n");
            printf("  |   |\n");
            printf(" /    |\n");
            printf("      |\n");
            break;
        case 0:
            printf("   ___\n");
            printf("  |   |\n");
            printf("  O   |\n");
            printf(" \\|/  |\n");
            printf("  |   |\n");
            printf(" / \\  |\n");
            printf("      |\n");
            break;
        default:
            break;
    }

}

//displays already guessed letters
void displayAlreadyGuessed(char guess, char already[26], int counter){
    int i;
    //appends newly guessed word to the end of the array
    already[counter] = guess;
    printf("Guessed letters: ");
    for(i=0; i<=counter; i++){
        printf("%c ", already[i]);

    }
    printf("\n");
}
