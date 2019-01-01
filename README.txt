=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Array
  	
  	implementation: Cards[][] is a 2D array of cards which stores the card objects that are currently in play.
  					Instantiated as cards field in GameBoard class, populated in the constructor and updated 
  					after a set is found or the board is shuffled.
  	functionality:  appropriate because the board is a 4x3 grid and cards is a 4 x 3 array, so it keeps track
  					of the objects and their position.

  2. Collections
  
  	implementation: selections is a Set that stores the cards currently selected on the board. instantiated as 
  					a TreeSet in the Card class and used in updating the board and checking sets. deck is a 
  					LinkedList that stores the cards in the deck. used in the draw method, which returns the 
  					first card in the deck, and the shuffle method, which shuffles the deck.
  	functionality: TreeSet is appropriate for selections because there are no repeats, it is unordered, and elements 
  					must be added and removed. LinkedList is appropriate because elements are ordered and must be
  					added and removed. deck also makes use of the Collections.shuffle function in the method shuffle

  3. File I/O 
  
  	implementation: this game reads and writes highscores from a file. the file is called highscores.txt, and when
  					the game ends the program reads the highscores in the file, compares the current score, and 
  					updates the document. it also prints the highscores in the endgame window.
  	
  	functionality: it uses a reader and a writer to access the document in the endGame() method in the class Game

  4. JUnit Tests
  
    	implementation: the methods that do not include the GUI are tested in GameTest
    	
  	functionality: isValidCard() and isSet() are tested, including edge cases
  	
  5. Inheritance (decided to explain this because there are few junit tests I could write)
  
    	implementation: card extends JButton and implements ActionListener and Comparable; GameBoard, StatusPanel, and 
    	ControlPanel extend JPanel. 
    	
  	functionality: this is a good design decision because it encapsulates certain aspects of the game and adds additional 
  	functionality in the form of methods to each class. 

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
Card: creates a card object, which is a JButton that can be clicked on in the game, and includes methods that perform
 actions and tests on the cards

Deck: stores the deck of cards used in the game, and includes methods that perform actions and tests on the deck

GameBoard: JPanel that organized card objects in a grid and includes methods that deal with the state of the board

Control Panel: JPanel that includes the shuffle button

StatusPanel: JPanel that includes JLabels for sets found, time elapsed, and cards left, and methods that modify these

Game: runnable class which implements the java swing aspects of the program and includes endGame and startGame methods

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
I think the decision to split up GameBoard, StatusPanel, and ControlPanel was a good decision because it allowed me to 
extend classes from java swing and implement my own functionality. if I were to refactor, I would maybe move some methods
around to make the organization more logical.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
	Java docs, Set website