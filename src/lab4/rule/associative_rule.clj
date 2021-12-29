(ns lab4.rule.associative-rule
  (:require [lab4.definition.variable :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.rule.default-rule :refer :all]))

(def associative-rules
  (let [rule-application #(apply-rules associative-rules %)]
    (conj
      (default-rule rule-application)
      [(fn [expr] (and (con? expr) (some #(con? %) (args expr))))
       (fn [expr] (let [elem (some #(if (con? %) %) (args expr))]
                    (apply-rules associative-rules (apply con (map rule-application (concat (args elem) (filter #(not (= % elem)) (args expr))))))))]
      [(fn [expr] (and (dis? expr) (some #(dis? %) (args expr))))
       (fn [expr] (let [elem (some #(if (dis? %) %) (args expr))]
                    (apply-rules associative-rules (apply dis (map rule-application (concat (args elem) (filter #(not (= % elem)) (args expr))))))))])))