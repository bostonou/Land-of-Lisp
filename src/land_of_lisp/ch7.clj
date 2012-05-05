(ns land-of-lisp.ch7
  (:require [land-of-lisp.core]))

(def max-label-len 30)

(defn dot-name
  [name-key]
  (clojure.string/upper-case
    (clojure.string/replace (name name-key) #"[^\w]" "_")))

(defn dot-label
  [label-key label-desc]
  (let [label-str (str (clojure.string/upper-case (name label-key))
                       ": " label-desc)]
        (if (> (count label-str) max-label-len)
          (str (subs label-str 0 max-label-len) "...")
          label-str)))

(defn create-label
  [node]
  (let [[location desc] node
        dot-location (dot-name location)]
    (str dot-location "[label=\"" (dot-label dot-location desc) "\"];")))

;how to map over map, with key and val as params to func
(defn nodes->dot
  [nodes]
  (set (map create-label nodes)))
