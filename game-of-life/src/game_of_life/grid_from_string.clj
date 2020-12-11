(ns game-of-life.grid-from-string)

(defn isAlive? [cell-string] (= "1" cell-string))

(defn make-cell [line-number [column-number cell]] {:column column-number :line line-number})

(defn make-cell-from-string-line [line-number columns]
  (let [alive-columns (filter isAlive? columns)
        indexed-columns (map-indexed vector alive-columns)
        cells (map #(make-cell line-number %) indexed-columns)]
    cells))

(defn make-indexed-lines [grid-string]
  (let [lines-string (clojure.string/split grid-string #"\n")
        lines-string-trimmed (mapv clojure.string/trim lines-string)
        grid (mapv #(clojure.string/split % #" ") lines-string-trimmed)]
    (map-indexed vector grid)))

(defn from-string [string]
  (let [indexed-lines (make-indexed-lines string)
        cells (mapv #(make-cell-from-string-line (first %) (second %)) indexed-lines)]
    (set (flatten cells))))
