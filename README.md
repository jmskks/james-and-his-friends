# James and his friend

"I am currently modifying all the codes that I made two years ago. The codes will look better with more efficiency.
If you would like to see my new codes, please check my another branch and you should be able to see it! (It has not been done yet, though)
If you want to play the game, run "RUN.bat" file in master branch!
Thank you very much!"








Hello,
This is a text-based game that I built on my own in August 2018, after I took my Java I at community college in Spring 2018.
One thing I want to point out about this game is that the conversation between characters in the game is Korean, but it can still
be played without understanding the conversation. It is, however, required to understand the conversation to be enjoyable.
As you might have found in the code, the code is not perfect as I was a beginner Java programmer at the time I build this game. This is the reason why
I recently decided to modify the codes for better efficiency. 

While working on this project, I learned a lot of important skills, such as how to debug more efficiently, how to think from the seller’s point of view for customers,
such as which features to add to make it more enjoyable for my friends, how character’s attributes (such as “HP” and “ATT”) should be calculated to balance the level of difficulty, and what stories should be added.

Here a detail of my code that used in the game:

•	Important concepts that are used in the code:
* i)	Switch: choosing actions with user’s input (integer).
* ii)	Inheritance: Character (parent), Friends and Enemies (children).
* iii)Overriding: Methods named same are coded differently depending on Friends class or Enemies class.
* iv)	Overloading: Methods named same are chosen depending on its arguments and parameters. 

•	Implementation of key features:
* i)	Choosing characters (HashMap) - User’s input (key) and corresponding characters (value).
* ii)	Writing into files (Array) - Saves into files (how far I have played by checking Boolean, which characters I chose and how much money or how many of HP/MP potions I have..).
* iii)	Reading files - Loads a saved game or texts of game stories about how I met the characters.
* iv)	Acquiring money or HP/MP potions from defeated enemy.
* v)	Level-up system - once user characters reach certain “EXP”, the character levels up and user can increase the character’s attribute.
* vi)	Actions of a user or enemy are dependent on probability with Random Generator:

1.  Flipping a coin at the beginning of a fight to decide who attacks first between a user and a group of enemies.
1.  Attacking an enemy, escaping a fight, or blocking an enemy’s attack.
1.  Occurrence of a critical attack by user characters or enemies.
1.  Lotto chance: when a character levels up, a user gets to roll a dice. If the result is the same number as a number I have set, the user gets an extra point to increase the character’s attribute. 

After finishing building the game, I distributed to my friends and they loved it. It is a very important experience for me that helped me to move on to the next step of Computer Science field.
I hope you have enjoyed reading this. Thank you very much for visiting my github!