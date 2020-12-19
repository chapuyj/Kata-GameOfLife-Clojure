(ns game-of-life.grid-from-string)

(defn state [string]
  (case string
    "1" :alive
    :dead))

(defn states [strings]
  (mapv state strings))

(defn from-string [string]
  (->> (clojure.string/split string #"\n")
       (map clojure.string/trim)
       (map #(clojure.string/split % #" "))
       (mapv states)))
