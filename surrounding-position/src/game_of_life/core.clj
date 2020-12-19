(ns game-of-life.core
  (:gen-class))

(require 'clojure.set)
(require '[clojure.core.match :refer [match]])

; Cell rules

(defn alive? [cell] (= cell :alive))
(defn dead? [cell] (= cell :dead))

(defn tick-cell [cell numberOfAliveNeighbours]
  (match [cell numberOfAliveNeighbours]
    [:alive 2] :alive
    [_ 3] :alive
    :else :dead))

; Neighbours

(def neighbours-pattern #{[-1, -1] [0, -1] [1, -1] [-1, 0] [1, 0] [-1, 1] [0, 1] [1, 1]})

(defn relative-position [{column :column line :line} [relative-column, relative-line]]
  {:column (+ column relative-column) :line (+ line relative-line)})

(defn neighbours-for-position [position]
  (let [create-position (partial relative-position position)]
    (->> neighbours-pattern
         (map create-position)
         (set))))

(defn count-alive-neighbours [position alive-positions]
  (->> (neighbours-for-position position)
       (clojure.set/intersection alive-positions)
       (count)))

(defn neighbours-and-position [position]
  (-> (neighbours-for-position position)
      (conj position)))

(defn neighbours-and-positions [positions]
  (->> (map neighbours-and-position positions)
       (map seq)
       (flatten)
       (set)))

; Grid

(defn cell-state [position alive-positions]
  (if (contains? alive-positions position) :alive :dead))

(defn tick-position [position alive-positions]
  (let [old-state (cell-state position alive-positions)
        alive-neighbours (count-alive-neighbours position alive-positions)]
    (tick-cell old-state alive-neighbours)))

(defn keep-still-alive-after-tick [alive-positions positions]
  (filter #(= :alive (tick-position % alive-positions)) positions))

(defn tick-grid [{size :size alive-positions :alive-positions}]
  (->> (neighbours-and-positions alive-positions)
       (keep-still-alive-after-tick alive-positions)
       (set)
       (assoc {:size size} :alive-positions)))

; Main

(defn -main
  "I don't do a whole lot ... yet."
  [& _]
  (println "Hello, World!"))
