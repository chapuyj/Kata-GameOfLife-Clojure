(ns game-of-life.grid-from-string-test
  (:require [clojure.test :refer :all]
            [game-of-life.grid-from-string :refer :all]))

(deftest grid-from-string

  (testing "Create an empty grid from literate representation."
    (is (= (from-string "0 0 0
                         0 0 0
                         0 0 0")
           [[:dead :dead :dead]
            [:dead :dead :dead]
            [:dead :dead :dead]])))
  
  (testing "Create an grid with vertical line from literate representation."
    (is (= (from-string "0 1 0
                         0 1 0
                         0 1 0")
           [[:dead :alive :dead]
            [:dead :alive :dead]
            [:dead :alive :dead]])))
  
  (testing "Create a full grid from literate representation."
    (is (= (from-string "1 1 1
                         1 1 1
                         1 1 1")
           [[:alive :alive :alive]
            [:alive :alive :alive]
            [:alive :alive :alive]]))))
