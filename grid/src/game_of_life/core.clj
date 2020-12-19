(ns game-of-life.core
  (:require [clojure.core.match :refer [match]])
  (:gen-class))

; Cell rules

(defn alive? [cell] (= cell :alive))
(defn dead? [cell] (= cell :dead))

(defn tick-cell [cell numberOfAliveNeighbours]
  (match [cell numberOfAliveNeighbours]
    [:alive 2] :alive
    [_ 3] :alive
    :else :dead))

; Cell

(defn get-cell [{column :column line :line} grid]
  (-> grid
      (get line)
      (get column)))

; Neighbours

(def neighbours-pattern #{[-1, -1] [0, -1] [1, -1] [-1, 0] [1, 0] [-1, 1] [0, 1] [1, 1]})

(defn relative-position [{column :column line :line} [relative-column, relative-line]]
  {:column (+ column relative-column) :line (+ line relative-line)})

(defn neighbours-at-position [position grid] 
  (->> neighbours-pattern
       (map #(relative-position position %))
       (map #(get-cell % grid))))

(defn count-alive-neighbours [position grid] 
  (->> (neighbours-at-position position grid)
       (filter alive?)
       (count)))

; Main

(defn -main
  "I don't do a whole lot ... yet."
  [& _]
  (println "Hello, World!"))
