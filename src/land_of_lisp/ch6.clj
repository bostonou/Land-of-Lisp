(ns land-of-lisp.ch6
  (:require [land-of-lisp.core]
            [land-of-lisp.ch5 :as ch5]))

(defn convert-user-cmd
  [user-cmd]
  (let [user-cmds (clojure.string/split user-cmd #"[\s]+")
        func (read-string (first user-cmds))
        quote-param (fn [param]
                      (list 'quote (read-string param)))
        params (map quote-param (rest user-cmds))]
    (conj params func)))

;non-functional UI
(defn game-read
  []
  (convert-user-cmd (read-line)))
