(ns game-of-life.grid-from-string-test
  (:require [clojure.test :refer :all]
            [game-of-life.grid-from-string :refer :all]))

; Behavior tests

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

; Technical help tests

(deftest make-cell-from-string-line-test

  (testing "Create an empty line."
    (is (= (make-cell-from-string-line 7 ["0" "0" "0" "0"])
           `())))

  (testing "Create a full line."
    (is (= (make-cell-from-string-line 7 ["1" "1" "1"])
           `({:column 0 :line 7}
             {:column 1 :line 7}
             {:column 2 :line 7})))))

(deftest make-indexed-lines-test

  (testing "Create indexed lines from grid."
    (is (= (make-indexed-lines "0 0 1
                                1 0 1
                                1 1 1")
           `([0 ["0" "0" "1"]] [1 ["1" "0" "1"]] [2 ["1" "1" "1"]])))))