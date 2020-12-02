(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest rules

  ; I need a way to fuzz, to have one test per rule.

  ; First rule

  (testing "Any live cell with fewer than two live neighbours dies, as if by underpopulation. (0)"
    (is (= {:alive false} 
           (next-generation-cell {:alive true} 0))))

  (testing "Any live cell with fewer than two live neighbours dies, as if by underpopulation. (1)"
    (is (= {:alive false} 
           (next-generation-cell {:alive true} 1))))
           
  ; Second rule

  (testing "Any live cell with two or three live neighbours lives on to the next generation. (2)"
    (is (= {:alive true} 
           (next-generation-cell {:alive true} 2))))

  (testing "Any live cell with two or three live neighbours lives on to the next generation. (3)"
    (is (= {:alive true} 
           (next-generation-cell {:alive true} 3))))

  ; Third rule

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation. (4)"
    (is (= {:alive false} 
           (next-generation-cell {:alive true} 4))))

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation. (5)"
    (is (= {:alive false} 
           (next-generation-cell {:alive true} 5))))

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation. (6)"
    (is (= {:alive false} 
           (next-generation-cell {:alive true} 6))))

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation. (7)"
    (is (= {:alive false} 
           (next-generation-cell {:alive true} 7))))

  (testing "Any live cell with more than three live neighbours dies, as if by overpopulation. (8)"
    (is (= {:alive false} 
           (next-generation-cell {:alive true} 8))))
           
  ; Fourth rule

  (testing "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
    (is (= {:alive true} 
           (next-generation-cell {:alive false} 3))))

  ; The missing rule
           
  (testing "Any dead cell without exactly three live neighbours stays dead. (0)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 0))))   

  (testing "Any dead cell without exactly three live neighbours stays dead. (1)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 1))))

  (testing "Any dead cell without exactly three live neighbours stays dead. (2)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 2))))  

  (testing "Any dead cell without exactly three live neighbours stays dead. (4)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 4))))

  (testing "Any dead cell without exactly three live neighbours stays dead. (5)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 5))))   

  (testing "Any dead cell without exactly three live neighbours stays dead. (6)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 6))))

  (testing "Any dead cell without exactly three live neighbours stays dead. (7)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 7))))   

  (testing "Any dead cell without exactly three live neighbours stays dead. (8)"
    (is (= {:alive false} 
           (next-generation-cell {:alive false} 8)))))
