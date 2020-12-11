(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest rules

  (testing "Any live cell with fewer than two live neighbours dies, as if by underpopulation."
    (is (->> (mapv (partial next-generation-cell {:alive true}) [0, 1])
             (every? #{{:alive false}}))))

  (testing "Any live cell with two or three live neighbours lives on to the next generation."
    (is (->> (mapv (partial next-generation-cell {:alive true}) [2, 3])
             (every? #{{:alive true}}))))

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation."
    (is (->> (mapv (partial next-generation-cell {:alive true}) [4, 5, 6, 7, 8])
             (every? #{{:alive false}}))))

  (testing "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
    (is (= {:alive true}
           (next-generation-cell {:alive false} 3))))

  ; The missing rule
  (testing "Any dead cell without exactly three live neighbours stays dead."
    (is (->> (mapv (partial next-generation-cell {:alive false}) [0, 1, 2, 4, 5, 6, 7, 8])
             (every? #{{:alive false}})))))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Move to new files (test and code)
; a grid factory

(defn isAlive [cell-string] (= "1" cell-string))

(defn make-cell [line-number [column-number cell]] {:column column-number :line line-number})

(defn make-cell-from-string-line [line-number columns] 
  (let [alive-columns (filter isAlive columns)
        indexed-columns (map-indexed vector alive-columns)
        cells (map #(make-cell line-number %) indexed-columns)]
    cells))

(deftest make-cell-from-string-line-test

  (testing "Create an empty line."
    (is (= (make-cell-from-string-line 7 ["0" "0" "0" "0"])
           `())))
  
  (testing "Create a full line."
    (is (= (make-cell-from-string-line 7 ["1" "1" "1"])
           `({:column 0 :line 7}
             {:column 1 :line 7}
             {:column 2 :line 7})))))



(defn make-indexed-lines [grid-string] 
  (let [lines-string (clojure.string/split grid-string #"\n")
        lines-string-trimmed (mapv clojure.string/trim lines-string)
        grid (mapv #(clojure.string/split % #" ") lines-string-trimmed)]
    (map-indexed vector grid)))

(deftest make-indexed-lines-test

  (testing "Create indexed lines from grid."
    (is (= (make-indexed-lines "0 0 1
                                1 0 1
                                1 1 1")
           `([0 ["0" "0" "1"]] [1 ["1" "0" "1"]] [2 ["1" "1" "1"]])))))



(defn from-string [string]
  (let [indexed-lines (make-indexed-lines string)
        cells (mapv #(make-cell-from-string-line (first %) (second %)) indexed-lines)]
    (set (flatten cells))))

(def empty-grid #{})

(def full-grid #{{:column 0 :line 0}
                 {:column 1 :line 0}
                 {:column 2 :line 0}
                 {:column 0 :line 1}
                 {:column 1 :line 1}
                 {:column 2 :line 1}
                 {:column 0 :line 2}
                 {:column 1 :line 2}
                 {:column 2 :line 2}})

(deftest grid-from-string

  (testing "Create an empty grid."
    (is (= (from-string "0 0 0
                         0 0 0
                         0 0 0")
           empty-grid)))
  
  (testing "Create a full grid."
    (is (= (from-string "1 1 1
                         1 1 1
                         1 1 1")
           full-grid))))
