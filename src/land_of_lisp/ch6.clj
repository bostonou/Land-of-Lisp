(ns land-of-lisp.ch6
  (:require [land-of-lisp.core])
  (:use [land-of-lisp.ch5]))

(def allowed-commands '#{look walk pickup inventory})

(defn convert-user-cmd
  [user-cmd]
  (let [user-cmds (clojure.string/split user-cmd #"[\s]+")
        func (read-string (first user-cmds))
        quote-param (fn [param]
                      (list 'quote (read-string param)))
        params (map quote-param (rest user-cmds))]
    (conj params func)))

(defn pretty-print
  [text-chars cap]
  (let [[first-char & rest-chars] text-chars]
    (cond
      (nil? first-char) ""
      (= first-char \space) (str first-char (pretty-print rest-chars cap))
      (contains? #{\? \! \.} first-char)
        (str first-char (pretty-print rest-chars true))
      cap (str (clojure.string/capitalize (str first-char))
               (pretty-print rest-chars false))
      true (str first-char (pretty-print rest-chars false)))))

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
  (println (pretty-print (seq (clojure.string/join " " output)) true)))

(defn game-repl
  []
  (let [cmd (game-read)]
    (if (= (first cmd) 'quit)
      (game-print '(thanks for playing!))
      (do
        (game-print (game-eval cmd))
        (flush) ;force message to print before returning to repl
        (game-repl)))))
