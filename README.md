# Battle Simulator Game

## Project Summary

**What does this application do?**

This application simulates a game where players design simple characters that can be pitted against each other in combat,
or sent to fight a preset boss. Players receive a limited amount of points that can be allocated to stats like *HP*,
*ATK*, and *DEF*. They can also give their characters attributes like *name* and *battle quote*. Stats and
attributes can be viewed by players freely from the starting menu, and players also have the option of deleting
an existing character. The actual combat consists of an RPG like system where the two combatants take turns attacking each 
other. Unlike most RPGs the battle sequence is automatic and players cannot choose actions per turn, the opponents simply
deal their attack on their turn in a back and forth fashion until one of them runs out of *HP* and is defeated.


**Who will use it?**

This application is designed to entertain people who enjoy playing games and creating their own characters, and want a
bit of a challenge (this is accomplished by having the player figure out what combination of stats will be necessary to
defeat the preset boss).


**Why did I choose this project?**

I myself enjoy playing games and entertain the idea of designing my own games, so I chose this
project in order to practice skills such as character creation and designing a combat system from scratch on Java.

NOTE: I referenced the TellerApp for the implementation of the Scanner code and file saving function in the ui. I 
also utilized Gson for the implementation of code, using the following link as a reference:
https://www.java67.com/2016/10/3-ways-to-convert-string-to-json-object-in-java.html


## User Stories
* As a user, I want to be able to create my own character(s) with stats and attributes and add it to a list of characters
* As a user, I want to be able to select a character from the list and view their stats/attributes
* As a user, I want to be able to select a character from the list and delete them from it
* As a user, I want to be able to select two of my characters from my list of characters and have them engage in combat
* As a user, I want to be able to select one of my characters from the list and have them challenge the boss
* As a user, I want to be able to save my characters/list of characters to file
* As a user, I want to be able to reload my list of characters whenever I return to the game

## Instructions for Grader
* You can generate the first required event by pressing the "Create a new character" button and following the resulting
instructions on the next screen(s). By the time the process is finished, a character should now be added to the character list.
* You can generate the second required event by pressing the "Manage characters" button and pressing the "View a character"
button on the next page. You will then be given instructions to type the name of the character you wish to examine from the list.
Follow the instructions correctly and a new page should open showing the stats of the selected character in the list. Similarly, you can
also remove a character from the list by pressing the "Delete a character" button from the manage menu and following the instructions provided
* You can trigger my audio component by pressing the "Save your data" button on the first menu.
* You can save the state of my application by pressing the "Save your data" button. The save will be confirmed by the sound effect
* You can reload the state of my application by simply closing and reopening the app. If you'd already pressed the save button before
closing the app, then the characters in the list should still be available to view from "Manage characters" -> "View a character"
after it is reopened
