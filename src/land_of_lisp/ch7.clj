(ns land-of-lisp.ch7
  (:require [land-of-lisp.core]
            [clojure.java.io :as io]
            [clojure.java.shell :as shell]))

(def max-label-len 30)

(def nodes {:living-room '[you are in the living room. a wizard is snoring loudly on the couch.]
            :garden '[you are in a beautiful garden. there is a well in front of you.]
            :attic '[you are in the attic. there is a giant welding torch in the corner.]})

(def edges {:living-room '[[:garden west door] [:attic upstairs ladder]]
            :garden '[[:living-room east door]]
            :attic '[[:living-room downstairs ladder]]})

(defn dot-name
  [name-key]
  (clojure.string/upper-case
    (clojure.string/replace (name name-key) #"[^\w]" "_")))

(defn dot-label
  [desc]
  (let [desc-str (str desc)]
    (if (> (count desc-str) max-label-len)
      (str (subs desc-str 0 max-label-len) "...")
      desc-str)))

(defn node-label
  [node]
  (let [[location desc] node
        dot-location (dot-name location)]
    (str dot-location "[label=\"" dot-location ": " (dot-label desc) "\"];")))

(defn edge-label
  [from edge-desc]
  (let [[to & desc] edge-desc
        dot-from (dot-name from)
        dot-to (dot-name to)]
    (str dot-from "->" dot-to "[label=\"" (dot-label (vec desc)) "\"];")))

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

(defn graph->dot
  [nodes edges]
  (println "digraph{")
  (println (clojure.string/join "\n" (nodes->dot nodes)))
  (println (clojure.string/join "\n" (edges->dot edges)))
  (println "}"))

;non-functional
(defn dot->png
  [fname thunk]
  (with-open [wrtr (io/writer fname)]
    (.write wrtr (with-out-str (thunk))))
  (shell/sh (str "dot -Tpng -O " fname)))

(defn graph->png
  [fname nodes edges]
  (dot->png fname (partial #'graph->dot nodes edges)))

