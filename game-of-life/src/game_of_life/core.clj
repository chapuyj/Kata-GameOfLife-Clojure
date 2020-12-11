(ns game-of-life.core
  (:gen-class))

; rules

(defn next-generation-for-alive-cell [numberOfAliveNeighbours]
  (case numberOfAliveNeighbours
    2 {:alive true}
    3 {:alive true}
    {:alive false}))

(defn next-generation-for-dead-cell [numberOfAliveNeighbours]
  (case numberOfAliveNeighbours
    3 {:alive true}
    {:alive false}))

(defn next-generation-cell [cell numberOfAliveNeighbours]
  (if (cell :alive)
    (next-generation-for-alive-cell numberOfAliveNeighbours)
    (next-generation-for-dead-cell numberOfAliveNeighbours)))

(defn count-alive-neighbours [position alive-positions]
  0)

; main

(defn -main
  "I don't do a whole lot ... yet."
  [& _]
  (println "Hello, World!"))
