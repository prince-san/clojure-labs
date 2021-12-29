(ns lab4.rule.de-morgan
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.rule.default-rule :refer :all])
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.definition.constant :refer :all]))

(def de-morgan
  (let [rule-application #(apply-rules de-morgan %)]
    (conj
      (default-rule rule-application)
      [(fn [expr] (and (inv? expr) (dis? (first (args expr)))))
       (fn [expr] (apply-rules de-morgan (apply con (map #(apply-rules de-morgan (inv %)) (args (first (args expr)))))))]
      [(fn [expr] (and (inv? expr) (con? (first (args expr)))))
       (fn [expr] (apply-rules de-morgan (apply dis (map #(apply-rules de-morgan (inv %)) (args (first (args expr)))))))]
      [(fn [expr] (and (inv? expr) (inv? (first (args expr)))))
       (fn [expr] (apply-rules de-morgan (first (args (first (args expr))))))])))
