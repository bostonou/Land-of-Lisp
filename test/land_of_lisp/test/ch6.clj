(ns land-of-lisp.test.ch6
  (:use [land-of-lisp.ch6])
  (:use [clojure.test]))

(deftest convert-user-cmd-test
  (is (= (convert-user-cmd "walk east") '(walk 'east)))
  (is (= (convert-user-cmd "run south yo") '(run 'south 'yo)))
  (is (= (convert-user-cmd "crawl") '(crawl))))
