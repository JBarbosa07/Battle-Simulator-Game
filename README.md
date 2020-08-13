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
button on the next page. You will see the character list and be given instructions to type the name of the character you wish to examine from the list.
Follow the instructions correctly and a new page should open showing the stats of the selected character in the list. Similarly, you can
also remove a character from the list by pressing the "Delete a character" button from the manage menu and following the instructions provided
* You can trigger my audio component by pressing the "Save your data" button on the first menu.
* You can save the state of my application by pressing the "Save your data" button. The save will be confirmed by the sound effect
* You can reload the state of my application by simply closing and reopening the app. If you'd already pressed the save button before
closing the app, then the characters in the list should still be available to view from "Manage characters" -> "View a character"
after it is reopened


## Phase 4: Task 2
I have implemented the Character class to be robust, specifically making the int-based "SetX" (where X is a stat) methods
robust by throwing an exception if the inputted value is more than the allotted points allowed in the stat pool. The SetHP
method also has an additional exception that is thrown if the hp is set to 0. Each one of these methods have a test in CharacterTest
checking for situations where the exception is called and not called correctly.


## Phase 4: Task 3
**Issues found in code:**

* Duplication found in methods in BattleSimulator and Character when checking if given/inputted values meet the critiera/
need to throw exception
* Duplication found in the BattleSimulator where three methods that allow the user to look through and select menu options
have essentially matching code (using a while loop to keep the program running and then go through a list of scanner input commands)
* Very poor cohesion in BattleSimulator; is essentially running the tasks of menu management, character creation, character management,
and the actual battle simulation all at once
* Similar cohesion problems with BattleSimulatorGUI
* Code involved in combat simulation is very messy and unclear to understand at a glance, could be cleaned up a bit
* Display menu methods titled as 1, 2, 3 and their purpose seems unclear at a glance
* A large amount of duplication in GUI, many buttons/JLabels have the same name and have nearly the exact same purpose all
throughout the methods
* Inconsistency in the EventHandler where actionPerformed checks for matching strings associated with buttons rather than a
designated ActionEvent key
* Inconsistency found in Main- cluttered with GUI set up while the console version only has one line of code instantiatingg a new BattleSimulator


**Solutions:**

* Removed as much duplication of code as I could find, by extracting the matching code to create helper methods, which
could be used in place of the duplicate code
* The similar menu methods were combined into a single menu managing method, that based on a given string key will give a
corresponding menu (e.g. if the key is "manage" then the method displays the manage menu)
* Main was cleaned up to only instantiate a new BattleSimulator and a new BattleSimulator GUI- the GUI set up was put into 
a BattleSimulatorGUI class which instantiated a MainMenuGUI class (where the rest of the GUI code is handled)
* Created three sub menus for BattleSimulator: CharacterCreator, CharacterManager, and CombatSystem, which each handle a
different area of the BattleSimulator. A bidirectional relationship was established between the BattleSimulator and each
sub menu so that they each keep track of the same character list. 
* Refractored the GUI similarly so that the GUI was divided into multiple "Menu" classes that each displayed/handled a different part
of the game (e.g. MainMenu, CreateMenu, ManageMenu, etc.)
* Fixed inconsistency with the actionPerformed/EventHandler method(s) and made them all accept ActionEvent keys
* Display method names were made more specific (e.g. displayMainMenu, displayManageMenu, etc)
* Further methods were extracted from code in CombatSystem to make their purpose more clear (e.g. the code that handled the
actual fight in a method was refractored into a method named "inCombat")


**Notes for UML design diagram:**
* I chose to make a diagram representing the console ui instead of the gui, because I feel it
better represents the idea behind the project and how the game operates. However, the two uis were designed very similarly, so
classes in the UML diagram can roughly be replaced with a gui equivalent to represent the gui (e.g. BattleSimulator = MainMenu GUI,
CharacterCreator = CreateMenuGUI)
* If an association arrow head does not have a number accompanying it then it can assumed that it represents 1
