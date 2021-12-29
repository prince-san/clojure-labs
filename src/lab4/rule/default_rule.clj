(ns lab4.rule.default-rule
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.definition.variable :refer :all])
  (:require [lab4.definition.constant :refer :all])
  (:require [lab4.definition.operation :refer :all]))

(defn default-rule [rule-application]
  (conj
    (list
      [(fn [expr] (and (= 1 (count (args expr))) (or (con? expr) (dis? expr))))
       (fn [expr] (second expr))]
      [(fn [expr] (or (con? expr) (dis? expr) (inv? expr)))
       (fn [expr] (cons (first expr)
                        (map #(rule-application %) (args expr))))])
    [variable? identity]
    [constant? identity]))