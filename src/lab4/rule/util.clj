(ns lab4.rule.util)

(defn args [expr]
  (rest expr))

(defn apply-rules [rules expr]
  ((some #(if ((first %) expr)
            (second %))
         rules) expr))