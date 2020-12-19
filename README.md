# Kata - Game of Life - Clojure
Game of Life kata in clojure.

## Kata Rules

The universe of the Game of Life is an infinite, **two-dimensional orthogonal grid** of square ***cells***, each of which is in one of **two possible states, *live* or *dead***, (or populated and unpopulated, respectively). Every cell interacts with its **eight *neighbours***, which are the cells that are horizontally, vertically, or diagonally adjacent. 

At each step in time, **the following transitions occur**:
- Any live cell with fewer than two live neighbours dies, as if by underpopulation.
- Any live cell with two or three live neighbours lives on to the next generation.
- Any live cell with more than three live neighbours dies, as if by overpopulation.
- Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.


These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
- Any live cell with two or three live neighbours survives.
- Any dead cell with three live neighbours becomes a live cell.
- All other live cells die in the next generation. Similarly, all other dead cells stay dead.

The initial pattern constitutes the seed of the system. The first generation is created by **applying the above rules simultaneously to every cell in the *seed***; births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a ***tick***. Each generation is a pure function of the preceding one. The rules continue to be applied repeatedly to create further generations.

More on [wikipedia](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

## Kata solutions

### [1 - Complete loop, return alive positions](/1-complete-loop-alive-positions)

I started with a solution based on a *light grid*: we just need to remember where cells are alive (couples of column and line). We don't need to have more data to apply next generation or to draw grid.

The grid is a square. The grid is represented by a map with
- a size,
- a set of *positions* where cells are alive.

`{:size 3 :alive-positions #{{:column 1 :line 2}}}`

We can apply `tick` on a grid to get the next generation. A complete loop is done: we are looping all possible cell in the grid, dead or alive, to apply rules and get the new state.

`from-string` is a literate programming to create a grid from a *human string representation*.

### [2 - Surrounding positions](/2-surrounding-positions)

In this second solution, I went further with the light grid. I removed the need to do a complete loop when creating the next generation.

- The grid is a set of *positions* where cells are alive. Because we do not need more informations. `#{{:column 1 :line 2}}}`
- We loop only on living cells and their neighbours, when we apply a `tick-grid`. Because a dead cell without living neighbour stay dead.

### [3 - Full grid](/3-full-grid)

Then, in this third solution, I wanted to use only a grid (as a 2-dimensional array). And to do a complete loop. 

```clojure
[[:dead :alive :dead]
[:dead :alive :dead]
[:dead :alive :dead]]
```

## Usage

Made with [leiningen](https://leiningen.org/).

#### Run

`lein run`

#### Test

`lein test`

#### Live Tests

Automatically run tests when a change occurs. [Read more about it](https://medium.com/@chapuyj/automatically-run-tests-when-a-change-occurs-9bae2140586b).

`live-test.sh`
