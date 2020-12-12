(ns game-of-life.grid-from-string-test
  (:require [clojure.test :refer :all]
            [game-of-life.grid-from-string :refer :all]))

; Behavior tests

(def full-grid-3 {:size 3 :alive-positions #{{:column 0 :line 0}
                                             {:column 1 :line 0}
                                             {:column 2 :line 0}
                                             {:column 0 :line 1}
                                             {:column 1 :line 1}
                                             {:column 2 :line 1}
                                             {:column 0 :line 2}
                                             {:column 1 :line 2}
                                             {:column 2 :line 2}}})

(deftest grid-from-string

  (testing "Create an empty grid from literate representation."
    (is (= (from-string "0 0 0
                         0 0 0
                         0 0 0")
           {:size 3 :alive-positions #{}})))
  
  (testing "Create an grid with vertical line from literate representation."
    (is (= (from-string "0 1 0
                         0 1 0
                         0 1 0")
           {:size 3 :alive-positions #{{:column 1 :line 0}
                                       {:column 1 :line 1}
                                       {:column 1 :line 2}}})))
  
  (testing "Create a full grid from literate representation."
    (is (= (from-string "1 1 1
                         1 1 1
                         1 1 1")
           {:size 3 :alive-positions #{{:column 0 :line 0} {:column 1 :line 0} {:column 2 :line 0}
                                       {:column 0 :line 1} {:column 1 :line 1} {:column 2 :line 1}
                                       {:column 0 :line 2} {:column 1 :line 2} {:column 2 :line 2}}}))))

; Technical help tests

(deftest make-cell-from-string-line-test

  (testing "Create an empty line."
    (is (= (make-cell-from-string-line 7 ["0" "0" "0" "0"])
           `())))
  
  (testing "Create a partial line."
    (is (= (make-cell-from-string-line 7 ["0" "1" "0" "0"])
           `({:column 1 :line 7}))))

  (testing "Create a full line."
    (is (= (make-cell-from-string-line 7 ["1" "1" "1"])
           `({:column 0 :line 7} {:column 1 :line 7} {:column 2 :line 7})))))

(deftest make-indexed-lines-test

  (testing "Create indexed lines from a grid in literate representation."
    (is (= (make-indexed-lines "0 0 1
                                1 0 1
                                1 1 1")
           `([0 ["0" "0" "1"]] [1 ["1" "0" "1"]] [2 ["1" "1" "1"]])))))