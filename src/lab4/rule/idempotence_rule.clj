(ns lab4.rule.idempotence-rule
  (:require [lab4.definition.variable :refer :all])
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.rule.default-rule :refer :all]))

(def idempotence-rule
  (let [rule-application #(apply-rules idempotence-rule %)]
    (conj
      (default-rule rule-application)
      [(fn [expr] (and (con? expr) (not (apply distinct? (args expr)))))
       (fn [expr] (apply con (map rule-application (distinct (args expr)))))]
      [(fn [expr] (and (dis expr) (not (apply distinct? (args expr)))))
       (fn [expr] (apply dis (map rule-application (distinct (args expr)))))])))
