(ns land-of-lisp.test.core
  (:use [land-of-lisp.core])
  (:use [clojure.test]))

(testing "Shift operator"
  (is (= (ash 11 1) 22))
  (is (= (ash 11 -1) 5))
  (is (= (ash 16 1) 32))
  (is (= (ash 16 0) 16))
  (is (= (ash 16 -1) 8)))
