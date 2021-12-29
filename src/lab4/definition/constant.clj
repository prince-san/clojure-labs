(ns lab4.definition.constant)

(defn constant [val]
  {:pre [(boolean? val)]}
  (if val
    (list ::const ::true)
    (list ::const ::false)))

(defn constant? [expr]
  (= (first expr) ::const))



(defn constant-value [const]
  (second const))

(defn constant-true? [expr]
  {:pre [(constant? expr)]}
  (= (constant-value expr) ::true))

(defn constant-false? [expr]
  {:pre [(constant? expr)]}
  (= (constant-value expr) ::false))