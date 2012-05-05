(ns land-of-lisp.ch6
  (:require [land-of-lisp.core]
            [land-of-lisp.ch5 :as ch5]))

(def allowed-commands '#{look walk pickup inventory})

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

(defn game-eval
  [cmd]
  (if (contains? allowed-commands (first cmd))
    (eval cmd)
    '(i do not know that command.)))

(defn game-print
  [output]
  output)

(defn game-repl
  []
  (let [cmd (game-read)]
    (when (not (= (first cmd) 'quit))
            (game-print (game-eval cmd))
            (game-repl))))
