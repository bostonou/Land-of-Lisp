(ns land-of-lisp.test.ch5
  (:use [land-of-lisp.ch5])
  (:use [clojure.test]))

(deftest describe-location-test
  (is (= (describe-location :living-room nodes) (nodes :living-room)))
  (is (= (describe-location :garden nodes) (nodes :garden)))
  (is (= (describe-location :attic nodes) (nodes :attic))))

(deftest describe-path-test
  (is (= (describe-path '(garden west door))
         '(there is a door going west from here.)))
  (is (= (describe-path '(living-room downstairs ladder))
         '(there is a ladder going downstairs from here.))))

(deftest describe-paths-test
  (is (= (describe-paths '((garden west door)
                           (living-room downstairs ladder)))
         '(there is a door going west from here. there is a ladder going downstairs from here.))))
