(ns game-of-life.core
  (:gen-class))

(require 'clojure.set)

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

; neighbours

(defn neighbours-on-line [line]
  (->> (range -1 2)
       (map (fn [column] {:column column :line line}))))

(defn neighbours-grid []
  (->> (range -1 2)
       (map neighbours-on-line)
       (flatten)))

(defn neighbours-for-position [{column :column line :line :as position}]
  (->> (neighbours-grid)
       (map #(update % :column + column))
       (map #(update % :line + line))
       (filter #(not= position %))))

(defn count-alive-neighbours [position alive-positions]
  (->> (neighbours-for-position position)
       (clojure.set/intersection alive-positions)
       (count)))

; main

(defn -main
  "I don't do a whole lot ... yet."
  [& _]
  (println "Hello, World!"))
