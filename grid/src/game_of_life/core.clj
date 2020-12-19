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

; Main

(defn -main
  "I don't do a whole lot ... yet."
  [& _]
  (println "Hello, World!"))
