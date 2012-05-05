(ns land-of-lisp.test.ch7
  (:use [land-of-lisp.ch7])
  (:use [clojure.test]))

(testing "Node Data"
  (deftest dot-name-test
    (is (= (dot-name 'living-room) "living_room"))
    (is (= (dot-name 'foo!) "foo_"))
    (is (= (dot-name '24) "24")))

  (deftest dot-label-test
    (is (= (dot-label '(garden (you are here)))
           "(garden (you are here))"))
    (is (= (dot-label '(Long text max of 30 chars goes here then dots))
           "(Long text max of 30 chars goe..."))))
