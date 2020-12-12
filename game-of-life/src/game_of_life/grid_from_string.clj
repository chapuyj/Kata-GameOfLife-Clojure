(ns game-of-life.grid-from-string)

(defn make-position [line-number [column-number]] {:column column-number :line line-number})

(defn make-positions-from-string-line [line-number columns]
  (->> (map-indexed vector columns)
       (filter #(= "1" (second %)))
       (map #(make-position line-number %))))

(defn make-indexed-lines [grid-string]
  (->> (clojure.string/split grid-string #"\n")
       (mapv clojure.string/trim)
       (mapv #(clojure.string/split % #" "))
       (map-indexed vector)))

(defn from-string [string]
  (let [indexed-lines (make-indexed-lines string)
        cells (mapv #(make-positions-from-string-line (first %) (second %)) indexed-lines)
        flattenCells (set (flatten cells))]
    {:size (count indexed-lines) :alive-positions flattenCells}))
