(ns land-of-lisp.test.ch6
  (:use [land-of-lisp.ch6])
  (:use [clojure.test]))

(deftest convert-user-cmd-test
  (is (= (convert-user-cmd "walk east") '(walk 'east)))
  (is (= (convert-user-cmd "run south yo") '(run 'south 'yo)))
  (is (= (convert-user-cmd "crawl") '(crawl))))

(deftest pretty-print-test
  (is (= (pretty-print (seq "just a test? try it!") true)
         "Just a test? Try it!"))
  (is (= (pretty-print (seq "i want a tablet, in other words an iPad.") true)
         "I want a tablet, in other words an iPad.")))
