(ns lab4.definition.variable)

(defn variable [name]
  {:pre [(keyword? name)]}
  (list ::var name))

(defn variable? [expr]
  (= (first expr) ::var))

(defn variable-name [var]
  (second var))

(defn same-variable [var1 var2]
  (= (variable-name var1) (variable-name var2)))