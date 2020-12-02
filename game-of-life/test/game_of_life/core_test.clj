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