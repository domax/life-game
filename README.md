Conway’s Game of Life Exercise
==============================

Goal
----
The goal of this exercise is to calculate the next generation of Conway’s game of life given any initial state. 
Take a look at the [following](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) 
for some background on Conway’s game of life.

Build
-----

To build this project you have to get JDK 8+ and Maven 3+ installed.
Run the following command in project root folder:
    
    mvn clean package
    
Run
---

If project was successfully build, you'll find a `life-game-1.0-SNAPSHOT-jar-with-dependencies.jar` deliverable
in `target` directory.
Then you can run project with the following command:

    cat <<EOF | java -jar target/life-game-1.0-SNAPSHOT-jar-with-dependencies.jar 
    ......O.
    OOO...O.
    ......O.
    ........
    ...OO...
    ...OO...
    
    EOF

Expected result would be the following output:

    .O......
    .O...OOO
    .O......
    ........
    ...OO...
    ...OO...
