(ns land-of-lisp.test.ch1
  (:use [land-of-lisp.ch1])
  (:use [clojure.test]))

(deftest guess-my-number-game
  (testing "Guess number"
    (is (= (guess-my-number) (quot (+ small big) 2)))))
