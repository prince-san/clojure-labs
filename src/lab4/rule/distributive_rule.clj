(ns lab4.rule.distributive-rule
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.rule.default-rule :refer :all]))

(def distributive-rule
  (let [rule-application #(apply-rules distributive-rule %)]
    (conj
      (default-rule rule-application)
      [(fn [expr] (and (con? expr) (some #(dis? %) (args expr))))
       (fn [expr] (let [elem (some #(if (dis? %) %) (args expr))]
                    (rule-application
                                 (apply dis
                                        (map #(rule-application (apply con % (filter (fn [arg] (not (= arg elem))) (args expr)))) (args elem))))))])))