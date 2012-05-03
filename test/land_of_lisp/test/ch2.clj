(ns land-of-lisp.test.ch2
  (:use [land-of-lisp.ch2])
  (:use [clojure.test]))

;need to set the default values
(dosync
  (ref-set big 100)
  (ref-set small 1))

(deftest guessing
  (is (= (guess-my-number) (quot (+ (deref small) (deref big)) 2))))

(deftest smaller-guesses
  (let [first-guess (guess-my-number)
        next-guess (smaller)]
    (is (= next-guess (guess-my-number)))
    (is (> first-guess next-guess))
    (is (= (dec first-guess) (deref big)))))

(deftest bigger-guesses
  (let [first-guess (guess-my-number)
        next-guess (bigger)]
    (is (= next-guess (guess-my-number)))
    (is (< first-guess next-guess))
    (is (= (inc first-guess) (deref small)))))

(deftest start-over-test
  (let [new-guess (start-over)
        guess-again (guess-my-number)]
    (is (= new-guess guess-again))
    (is (= (deref big) 100))
    (is (= (deref small) 1))))
