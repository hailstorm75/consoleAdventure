# Adventure console game

> Programming thesis project in Java

## Story

The main protagonist of the game is a pre-school pupil who found himself living through a nightmare.

The morning started like any other, with the young kid waking up in his room. As usual, he headed to the kitchen to greet his parents. On the way there, he stumbled upon a note laying on the floor of the corridor leading to the kitchen. 

The note was barely readable. The only words that could comprehensible text ordered to head straight into the study room. Deciding to ignore the notes command until having a snack, the boy continued marching towards the kitchen, only to find it shut with three padlocks.

He cried for help for his parents, no-one answered. It was dead quiet. There was no other choice but to do what the paper note asked. 

Finally, inside the study room, a cold sweat struck the boy, as he’d discovered that the place was brightly lit; by a door leading into a neon-blue tunnel. He recalled his parents arguing about renovating, but not to this extent?!

The young lad gathered all his courage, carefully entered the tunnel, and paced into the unknown. To no spectators surprise, the entrance passage behind him closed, leaving the brave boy only one choice of direction.

But what awaits ahead? – Some may wonder. Most horrible monsters, demons that haunt the boy from his school. They come from the dark realm of pre-school math to torment children all-over the world. 

## Map

![alt text](https://github.com/hailstorm75/consoleAdventure/blob/main/skripta_Adventura/Room%20diagram.png "Room Diagram")

## Script

The following is a set of commands and outputs that lead to a successful game ending.

There are both steps and battles within steps.
Some item discoveries are random.
Battles steps are random.

### Introduction

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You wake up in your room, bright light from the sun is shing inside. Today is going to be a great day!**

**Just need to grab a snack, pack the books and head to school.**

**Hopefully, there won't be a math test today, those stick.**

**From here you can go to: corridor**

### Step 1

> go to the corridor

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You are in the corridor that joins multiple rooms of the house together.**

**You notice a paper-note lying on the floor.**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 2

> examine the note

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You pick up the paper-note and read what it says:**

**"ERTkdfgkhUI\*#5fsGO TO?<85Tudy =r00m///8"**

**You put paper-note back to where you've found it**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 3

> head to kitchen

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**It seems that the kitchen is locked by three mysterious padlocks, each of a different color - blue, green, yellow**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 4

> enter study

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**It seems that the study is locked**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 5

> go to the living room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**The living room is silent with nobody around. You notice a key on the coffee table.**

**From here you can go to: corridor**

### Step 6

> take the key

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You take key and put in inside your pocket**

**From here you can go to: corridor**

### Step 7

> go to corridor

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You are in the corridor that joins multiple rooms of the house together.**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 8

> unlock study

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You've unlocked the study room**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 9

> enter study

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.**

**Each of a different color - blue, green, yellow.**

**Only the blue one had a handle drawn, while the others were missing it.**

**From here you can go to: blue room, green room, yellow room, corridor**

### Step 10

> enter blue room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**Upon entering you hear the door slam shut behind your back**

**You are inside the mysterious blue room. Numbers are written on every wall.**

**From here you can go to: study, square room, mystery room**

### Step 11

> enter mystery room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**The door to the mystery room is locked. Probably needs a key.**

**From here you can go to: study, square room, mystery room**

### Step 12

> enter square room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You are in a room full of square shapes floating in the air, some are combined into boxes, three to be exact**

**Each box is of a different size - small, medium and large**

**From here you can go to: blue room**

### Step 13

> inspect small box

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You take a look at the small box. Inside you find a chocolate bar**

**From here you can go to: blue room**

### Step 14

> take chocolate from small box

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You take chocolate and put in inside your pocket**

**From here you can go to: blue room**

### Step 15

> eat chocolate

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You take chocolate and put in inside your pocket**

**From here you can go to: blue room**

### Step 16

> inspect the large box

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You take a look at the large box. Inside you find a mysterious key**

**From here you can go to: blue room**

### Step 17

> inspect the large box

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You take a look at the large box. Inside you find a mysterious key**

**From here you can go to: blue room**

### Step 18

> take the mysterious key

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You take mysterious key and put in inside your pocket**

**From here you can go to: blue room**

### Step 19

> enter blue room

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You are inside the mysterious blue room. Numbers are written on every wall.**

**From here you can go to: study, square room, mystery room**

### Step 20

> unlock the mystery room

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You've unlocked the mystery room**

**From here you can go to: study, square room, mystery room**

### Step 21

> go to the mystery room

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You are inside the mystery room. Darkness. Nothing to see.**

**You feel something watching you**

**Suddenly, you are hit by a sharp plus sign**

**A monster appears!**

#### Battle Start

*THE FOLLOWING SECTION IS NOT YET DEFINED*

*IT SHALL TAKE 3 SUCCESSFUL HITS TO WIN THE BATTLE*

#### Battle End

**You hear the blue room exit unlock**

**On the ground, among the ashes of the monster you find a blue key and a green charcoal**

**From here you can go to: blue room**

### Step 22

> take blue key

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You take blue key and put in inside your pocket**

**From here you can go to: blue room**

### Step 23

> take green charcoal

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You take green charcoal and put in inside your pocket**

**From here you can go to: blue room**

### Step 24

> enter blue room

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You are inside the mysterious blue room. Numbers are written on every wall.**

**From here you can go to: study, square room, mystery room**

### Step 25

> go to the study

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.**

**From here you can go to: blue room, green room, yellow room, corridor**

### Step 26

> unlock the green room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You've unlocked the green room**

**From here you can go to: blue room, green room, yellow room, corridor**

### Step 27

> enter green room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You are inside the mysterious green room. Letters are written on every wall.**

**From here you can go to: study, numbers room, circles room, triangles room**

### Step 28

> enter circles room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You are inside the circles room. Everything is nauseatingly round.**

**You notice a round paper-note lying on the round floor.**

**From here you can go to: green room, mystery room**

### Step 29

> examine the note

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You pick up the paper-note and read what it says:**

**"Bring totems"**

**You put paper-note back to where you've found it**

**From here you can go to: green room, mystery room**

### Step 30

> enter green room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You are inside the mysterious green room. Letters are written on every wall.**

**From here you can go to: study, numbers room, circles room, triangles room**

### Step 31

> enter triangles room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You are inside the triangles green room. The walls and ceiling are made out of spiky triangles.**

**In the center of the room you notice a miniature statue of a wise man**

**From here you can go to: green room**

### Step 32

> take statue

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You take the statue and put in inside your pocket**

**The room starts rumbling and the spiky walls slowly coming closer and closer. There isn't much time**

**From here you can go to: green room**

### Step 33

> go to green room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**The triangle room shuts behind you. Thankfully you got out in one piece.**

**You are inside the mysterious green room. Letters are written on every wall.**

**From here you can go to: study, numbers room, circles room**

### Step 34

> go to numbers room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You are inside the numbers room. You hear voices reciting some sequence of numbers**

**In the middle of the room you find a golden ruler**

**From here you can go to: green room**

### Step 35

> take ruler

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You take the ruler and put in inside your pocket**

**The voices stop. The room is in complete silence**

**From here you can go to: green room**

### Step 36

> go to the green room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**The numbers root shuts behind you.**

**You are inside the mysterious green room. Letters are written on every wall.**

**From here you can go to: study, circles room**

### Step 37

> enter circles room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You are inside the circles room. Everything is nauseatingly round.**

**From here you can go to: green room, mystery room**

### Step 38

> drop statue

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You place the statue on the floor**

**From here you can go to: green room, mystery room**

### Step 39

> drop ruler

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You place the ruler on the floor**

**The mystery rooms door opens in front of you**

**From here you can go to: green room, mystery room**

### Step 40

> enter mystery room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 1

**You are inside the mystery room. Darkness. Nothing to see.**

**You feel something watching you**

**A monster appears!**

#### Battle Start

*THE FOLLOWING SECTION IS NOT YET DEFINED*

*IT SHALL TAKE 4 SUCCESSFUL HITS TO WIN THE BATTLE*

#### Battle End

**You hear the green room exit unlock**

**On the ground, among the ashes of the monster you find a green key and a yellow charcoal**

**From here you can go to: circle room**

### Step 41

> take green key

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You take green key and put in inside your pocket**

**From here you can go to: blue room**

### Step 42

> take yellow charcoal

HP: <3<3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You take yellow charcoal and put in inside your pocket**

**From here you can go to: blue room**

### Step 43

> enter circles room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You are inside the circles room. Everything is nauseatingly round.**

**From here you can go to: green room, mystery room**

### Step 44

> go to the green room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You are inside the mysterious green room. Letters are written on every wall.**

**From here you can go to: study, circles room**

### Step 45

> go to the study

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You are in the study. Books are scattered all over the place and where once stood a mighty bookshelf now is a wall with three silhouettes of doors.**

**From here you can go to: blue room, green room, yellow room, corridor**

### Step 46

> unlock yellow room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You've unlocked the green room**

**From here you can go to: blue room, green room, yellow room, corridor**

### Step 47

> enter yellow room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You are inside the mysterious yellow room.**

**From here you can go to: yellow room, prison**

### Step 48

> enter prison

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You are inside the prison.**

**From here you can go to: yellow room, mystery room**

### Step 49

> enter mystery room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 2

**You are inside the mystery room**

**There stands a dark silhouette of a man**

**He speaks: "You dare challenge me, the mighty Pythagoras, puny child?"**

**From here you can go to: yellow room, mystery room**

#### Battle Start

*THE FOLLOWING SECTION IS NOT YET DEFINED*

*IT SHALL TAKE 5 SUCCESSFUL HITS TO WIN THE BATTLE*

#### Battle End

**You hear the yellow room exit unlock**

**On the ground, among the ashes of the monster you find a yellow key**

**From here you can go to: prison**

### Step 50

> take yellow key

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You take yellow key and put in inside your pocket**

**From here you can go to: prison**

### Step 51

> go to prison

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You are inside the prison.**

**From here you can go to: yellow room**

### Step 52

> go to yellow room

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**From here you can go to: study, prison**

### Step 53

> go to study

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**From here you can go to: blue room, green room, yellow room, corridor**

### Step 54

> go to corridor

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 3

**You are in the corridor that joins multiple rooms of the house together.**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 55

> unlock kitchen

HP: <3<3<3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Inventory: 0

**You've unlocked the kitchen**

**From here you can go to: bedroom, kitchen, study, living room**

### Step 56

> enter kitchen

**You wake up**

**Congratulations! You've won the game.**
