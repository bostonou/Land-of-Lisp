(ns land-of-lisp.ch7
  (:require [land-of-lisp.core]))

(def max-label-len 30)

(defn dot-name
  [raw-name]
  (clojure.string/replace (str raw-name) #"[^\w]" "_"))

(defn dot-label
  [exp]
  (let [s (str exp)]
    (if (> (count s) max-label-len)
      (str (subs s 0 max-label-len) "...")
      s)))
