(ns lab4.rule.distributive-rule
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.rule.default-rule :refer :all]))

(def distributive-rule
  (let [applied-rule #(apply-rules distributive-rule %)]
    (conj
      (default-rule applied-rule)
      [(fn [expr] (and (con? expr) (some #(dis? %) (args expr))))
       (fn [expr] (let [elem (some #(if (dis? %) %) (args expr))]
                    (apply-rules distributive-rule
                                 (apply dis
                                        (map #(applied-rule (apply con % (filter (fn [arg] (not (= arg elem))) (args expr)))) (args elem))))))])))