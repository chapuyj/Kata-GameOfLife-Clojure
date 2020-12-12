(ns game-of-life.grid-from-string)

(defn make-cell [line-number [column-number cell]] {:column column-number :line line-number})

(defn make-cell-from-string-line [line-number columns]
  (let [indexed-columns (map-indexed vector columns)
        alive-columns (filter #(= "1" (second %)) indexed-columns)
        cells (map #(make-cell line-number %) alive-columns)]
    cells))

(defn make-indexed-lines [grid-string]
  (let [lines-string (clojure.string/split grid-string #"\n")
        lines-string-trimmed (mapv clojure.string/trim lines-string)
        grid (mapv #(clojure.string/split % #" ") lines-string-trimmed)]
    (map-indexed vector grid)))

(defn from-string [string]
  (let [indexed-lines (make-indexed-lines string)
        cells (mapv #(make-cell-from-string-line (first %) (second %)) indexed-lines)
        flattenCells (set (flatten cells))]
    {:size (count indexed-lines) :alive-positions flattenCells}))
