(ns lab4.rule.remove-implication
  (:require [lab4.rule.default-rule :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.definition.variable :refer :all])
  (:require [lab4.definition.constant :refer :all]))

(def remove-implication
  (let [rule-application #(apply-rules remove-implication %)]
    (cons
      [(fn [expr] (imp? expr))
       (fn [expr]
         (if (= 1 (count (args expr)))
           (first (args expr))
           (apply-rules remove-implication (apply dis (concat
                                                        (map #(rule-application (inv %)) (butlast (args expr)))
                                                        (list (rule-application (last (args expr)))))))))]
      (default-rule rule-application))))