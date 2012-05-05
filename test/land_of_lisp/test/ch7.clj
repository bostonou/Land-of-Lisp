(ns land-of-lisp.test.ch7
  (:use [land-of-lisp.ch7])
  (:use [clojure.test]))

(testing "Node IDs"
  (deftest dot-name-test
    (is (= (dot-name 'living-room) "living_room"))
    (is (= (dot-name 'foo!) "foo_"))
    (is (= (dot-name '24) "24"))))
