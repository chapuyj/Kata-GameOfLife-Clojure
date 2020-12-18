(ns game-of-life.core
  (:gen-class))

(require 'clojure.set)
(require '[clojure.core.match :refer [match]])

; rules


(defn alive? [cell] (= cell :alive))
(defn dead? [cell] (= cell :dead))

(defn tick-cell [cell numberOfAliveNeighbours]
  (match [cell numberOfAliveNeighbours]
    [:alive 2] :alive
    [_ 3] :alive
    :else :dead))

; create grid

(defn create-line [line range]
  (->> range
       (map (fn [column] {:column column :line line}))))

(defn create-square-grid [range]
  (->> range
       (map #(create-line % range))
       (flatten)))

; neighbours

(defn update-add-position [position new-column new-line]
  (-> position
      (update :column + new-column)
      (update :line + new-line)))

(defn neighbours-for-position [{column :column line :line :as position}]
  (->> (create-square-grid (range -1 2))
       (map #(update-add-position % column line))
       (filter #(not= position %))
       (set)))

(defn count-alive-neighbours [position alive-positions]
  (->> (neighbours-for-position position)
       (clojure.set/intersection alive-positions)
       (count)))

; tick

(defn cell-state [position alive-positions]
  (if (contains? alive-positions position) :alive :dead))

(defn tick-position [position alive-positions]
  (let [old-state (cell-state position alive-positions)
        alive-neighbours (count-alive-neighbours position alive-positions)
        new-state (tick-cell old-state alive-neighbours)]
    (if (= new-state :alive) position nil)))

(defn tick [{size :size alive-positions :alive-positions}] 
  (->> (create-square-grid (range 0 size))
       (map #(tick-position % alive-positions))
       (filter some?)
       (set)
       (assoc {:size size} :alive-positions)))

; main

(defn -main
  "I don't do a whole lot ... yet."
  [& _]
  (println "Hello, World!"))
