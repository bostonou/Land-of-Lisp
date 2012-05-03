(ns land-of-lisp.ch1
  (:require [land-of-lisp.core]))

(def small (ref 1))

(def big (ref 100))

(defn guess-my-number
  []
  (land-of-lisp.core/ash (+ (deref small) (deref big)) -1))

(defn smaller
  []
  (dosync (ref-set big (dec (guess-my-number))))
  (guess-my-number))

(defn bigger
  []
  (dosync (ref-set small (inc (guess-my-number))))
  (guess-my-number))
