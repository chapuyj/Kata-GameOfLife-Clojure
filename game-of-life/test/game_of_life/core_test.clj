(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]
            [game-of-life.grid-from-string :refer :all]))

(deftest rules

  (testing "Any live cell with fewer than two live neighbours dies, as if by underpopulation."
    (is (->> [0, 1]
             (mapv (partial tick-cell {:alive true}))
             (every? #{{:alive false}}))))

  (testing "Any live cell with two or three live neighbours lives on to the next generation."
    (is (->> [2, 3]
             (mapv (partial tick-cell {:alive true}))
             (every? #{{:alive true}}))))

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation."
    (is (->> [4, 5, 6, 7, 8]
             (mapv (partial tick-cell {:alive true}))
             (every? #{{:alive false}}))))

  (testing "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
    (is (= {:alive true}
           (tick-cell {:alive false} 3))))

  ; The missing rule
  (testing "Any dead cell without exactly three live neighbours stays dead."
    (is (->> [0, 1, 2, 4, 5, 6, 7, 8]
             (mapv (partial tick-cell {:alive false}))
             (every? #{{:alive false}})))))


(deftest count-alive-neighbours-test

  (testing "Should be zero with an empty grid."
    (is (= (count-alive-neighbours {:column 1 :line 1} (:alive-positions (from-string "0 0 0
                                                                                       0 0 0
                                                                                       0 0 0")))
           0)))
  
  (testing "Should be 8 with a full grid."
    (is (= (count-alive-neighbours {:column 1 :line 1} (:alive-positions (from-string "1 1 1
                                                                                       1 1 1
                                                                                       1 1 1")))
           8))))

(deftest tick-test
  
  (testing "Block pattern."
    (is (= (tick (from-string "0 0 0 0
                               0 1 1 0
                               0 1 1 0
                               0 0 0 0"))
           (from-string "0 0 0 0
                         0 1 1 0
                         0 1 1 0
                         0 0 0 0"))))
  
  (testing "Blinker pattern."
    (is (= (tick (from-string "0 0 0
                               1 1 1
                               0 0 0"))
           (from-string "0 1 0
                         0 1 0
                         0 1 0")))))