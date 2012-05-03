(ns land-of-lisp.ch1)

(def small 1)

(def big 100)

(defn guess-my-number
  (land-of-lisp.core/ash (+ small big) -1))
