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

(defn node-label
  [node]
  (let [[location desc] node
        dot-location (dot-name location)]
    (str dot-location "[label=\"" (dot-label dot-location desc) "\"];")))

(defn edge-label
  [from edge-desc]
  (let [[to & desc] edge-desc
        dot-from (dot-name from)
        dot-to (dot-name to)]
    (str dot-from "[label=\"" (dot-label dot-to (vec desc)) "\"];")))

(defn edge-labels
  [edge]
  (let [[from edge-desc] edge]
    (map (partial edge-label from) edge-desc)))

(defn nodes->dot
  [nodes]
  (set (map node-label nodes)))

(defn edges->dot
  [edges]
  (set (flatten (map edge-labels edges))))
