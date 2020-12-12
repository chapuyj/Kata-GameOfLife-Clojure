(ns game-of-life.core
  (:gen-class))

(require 'clojure.set)

; rules

(defn tick-for-alive-cell [numberOfAliveNeighbours]
  (case numberOfAliveNeighbours
    2 {:alive true}
    3 {:alive true}
    {:alive false}))

(defn tick-for-dead-cell [numberOfAliveNeighbours]
  (case numberOfAliveNeighbours
    3 {:alive true}
    {:alive false}))

(defn tick-cell [cell numberOfAliveNeighbours]
  (if (cell :alive)
    (tick-for-alive-cell numberOfAliveNeighbours)
    (tick-for-dead-cell numberOfAliveNeighbours)))

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
  {:alive (contains? alive-positions position)})

(defn tick-position [position alive-positions]
  (let [old-state (cell-state position alive-positions)
        alive-neighbours (count-alive-neighbours position alive-positions)
        new-state (tick-cell old-state alive-neighbours)]
    (if (new-state :alive)
      position
      nil)))

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
