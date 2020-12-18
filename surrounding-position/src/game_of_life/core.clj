(ns game-of-life.core
  (:gen-class))

(require 'clojure.set)
(require '[clojure.core.match :refer [match]])

;;; Helpers

; Create grid

(defn create-line [line range]
  (->> range
       (map (fn [column] {:column column :line line}))))

(defn create-square-grid [range]
  (->> range
       (map #(create-line % range))
       (flatten)))

; Position

(defn relative-position [position relative-column relative-line]
  (-> position
      (update :column + relative-column)
      (update :line + relative-line)))

;;;

; Cell rules

(defn alive? [cell] (= cell :alive))
(defn dead? [cell] (= cell :dead))

(defn tick-cell [cell numberOfAliveNeighbours]
  (match [cell numberOfAliveNeighbours]
    [:alive 2] :alive
    [_ 3] :alive
    :else :dead))

; Neighbours

(defn list-position-with-neighbours [{column :column line :line}]
  (->> (create-square-grid (range -1 2))
       (map #(relative-position % column line))))

(defn neighbours-for-position [position]
  (->> (list-position-with-neighbours position)
       (filter #(not= position %))
       (set)))

(defn count-alive-neighbours [position alive-positions]
  (->> (neighbours-for-position position)
       (clojure.set/intersection alive-positions)
       (count)))

; Grid

(defn list-surrounding-positions [alive-positions]
  (->> (map list-position-with-neighbours alive-positions)
       (flatten)
       (set)))

(defn cell-state [position alive-positions]
  (if (contains? alive-positions position) :alive :dead))

(defn tick-position [position alive-positions]
  (let [old-state (cell-state position alive-positions)
        alive-neighbours (count-alive-neighbours position alive-positions)]
    (tick-cell old-state alive-neighbours)))

(defn keep-still-alive-after-tick [alive-positions positions]
  (filter #(= :alive (tick-position % alive-positions)) positions))

(defn tick-grid [{size :size alive-positions :alive-positions}]
  (->> (list-surrounding-positions alive-positions)
       (keep-still-alive-after-tick alive-positions)
       (set)
       (assoc {:size size} :alive-positions)))

; main

(defn -main
  "I don't do a whole lot ... yet."
  [& _]
  (println "Hello, World!"))
