(ns land-of-lisp.core)

(defn ash
  [integer num-bits]
  (if (> num-bits 0)
    (bit-shift-left integer num-bits)
    (bit-shift-right integer (* num-bits -1))))
