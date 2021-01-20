# Adventure console game

Programming thesis project in Java.

> **Version**: 1.0
> 
> **Author**: Denis Akopyan

## Description

A simple text-based game that mainly focuses on presenting the text-based game engine rather than delivering an extraordinary story experience.

List of features:

 - stylized output
    - the console output text can be stylized and printed in a typist mode
 - complex command input
    - the player can input a given command multiple way, such that it feel like talking with the game instead of enter only one strictly defined command
 - item containers
    - items can be held inside containers
    - containers can be locked with keys
 - rooms
    - items and item containers can be held inside rooms
    - rooms can be locked with keys
    - rooms can display a special message upon first entry
    - rooms can be locked until a condition is met
 - time-limited input
    - user-input can be timed-out after a given period
 - trap rooms
    - trap rooms require the user to quickly enter a command until certain death
 - boos fights
    - boos events require the user to quickly enter a command until loss of health

## Instructions

Type what you wish to do and press enter (more than one way to enter a command is supported).

The following command types are available:

### Unlock
Unlocks a room or item container if the required key is present in the players pocket.

Examples:
 - `unlock room`
 - `open room`

### Go to
The player will enter the specified room.

Examples:
 - `go to the kitchen`
 - `head to kitchen`
 - `enter the kitchen`
 - `move to kitchen`

### Where
Examines the current player location.

Examples:
 - `where`
 - `where am I`
 - `location`
 - `look around`

### Drop
Takes the given item from the pocket and drops it on the floor of the current room.

Example:
 - `drop key`

### Help
Displays help for the game, or a command.

### Eat
Consumes a given item.

Examples:
 - `consume the apple`
 - `eat the apple`
 - `devour apple`

### End
End the game.

### Examine
Examines a given item.

Example:
 - `examine key`
 - `inspect key`
 - `look at key`

### Carry
Puts the given item into the pocket.

Examples:
 - `take key`
 - `take key from box`

## Story

The main protagonist of the game is a pre-school pupil who found himself living through a nightmare.

The morning started like any other, with the young kid waking up in his room. As usual, he headed to the kitchen to greet his parents. On the way there, he stumbled upon a note lying on the floor.

The note was barely readable. The only words that could comprehensible text ordered to head straight into the study room. Deciding to ignore the notes command until having a snack, the boy continued marching towards the kitchen, only to find it shut with three padlocks.

He cried for help for his parents, no-one answered. It was dead quiet. There was no other choice but to do what the paper note asked.

Finally, inside the study room, a cold sweat struck the boy, as he’d discovered that the place was brightly lit; by a door leading into a neon-blue tunnel. He recalled his parents arguing about renovating, but not to this extent?!

The young lad gathered all his courage, carefully entered the tunnel, and paced into the unknown. Unsurprisingly, the entrance passage behind him closed, leaving the brave boy only one choice of direction.

But what awaits ahead? – Some may wonder. Most horrible monsters and demons - that haunt the boy from his school. They come from the dark realm of pre-school math to torment children all-over the world.