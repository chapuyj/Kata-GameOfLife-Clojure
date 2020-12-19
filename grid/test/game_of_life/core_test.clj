(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]
            [game-of-life.grid-from-string :refer :all]))

(deftest rules

  (testing "Any live cell with fewer than two live neighbours dies, as if by underpopulation."
    (is (->> [0, 1]
             (mapv (partial tick-cell :alive))
             (every? dead?))))

  (testing "Any live cell with two or three live neighbours lives on to the next generation."
    (is (->> [2, 3]
             (mapv (partial tick-cell :alive))
             (every? alive?))))

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation."
    (is (->> [4, 5, 6, 7, 8]
             (mapv (partial tick-cell :alive))
             (every? dead?))))

  (testing "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
    (is (= :alive
           (tick-cell :dead 3))))

  ; The missing rule
  (testing "Any dead cell without exactly three live neighbours stays dead."
    (is (->> [0, 1, 2, 4, 5, 6, 7, 8]
             (mapv (partial tick-cell :dead))
             (every? dead?)))))