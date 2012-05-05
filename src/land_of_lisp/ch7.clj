(ns land-of-lisp.ch7
  (:require [land-of-lisp.core]))

(defn dot-name
  [raw-name]
  (clojure.string/replace (str raw-name) #"[^\w]" "_"))
