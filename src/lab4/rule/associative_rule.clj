(ns lab4.rule.associative-rule
  (:require [lab4.definition.variable :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.definition.operation :refer :all]))

(def associative-rules
  (list
    [(fn [expr] (and (con? expr) (some #(con? %) (args expr))))
     (fn [expr] (let [elem (some #(if (con? %) %) (args expr))]
                  (apply-rules associative-rules (apply con (map #(apply-rules associative-rules %) (concat (args elem) (filter #(not (= % elem)) (args expr))))))))]
    [(fn [expr] (and (dis? expr) (some #(dis? %) (args expr))))
     (fn [expr] (let [elem (some #(if (dis? %) %) (args expr))]
                  (apply-rules associative-rules (apply dis (map #(apply-rules associative-rules %) (concat (args elem) (filter #(not (= % elem)) (args expr))))))))]))