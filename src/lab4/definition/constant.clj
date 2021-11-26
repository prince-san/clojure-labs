(ns lab4.definition.constant)

(defn constant [val]
  {:pre [(boolean? val)]}
  (list ::const val))

(defn constant? [expr]
  (= (first expr) ::const))

(defn constant-value [const]
  (second const))
