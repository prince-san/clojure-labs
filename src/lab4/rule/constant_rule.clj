(ns lab4.rule.constant-rule
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.rule.default-rule :refer :all])
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.definition.constant :refer :all])
  (:require [lab4.definition.variable :refer :all]))

(def constant-rule
  (let [rule-application #(apply-rules constant-rule %)]
    (conj
      (default-rule rule-application)
      [(fn [expr] (and (inv? expr) (constant? (first (args expr)))))
       (fn [expr] (if (constant-true? (first (args expr))) (constant false) (constant true)))]
      [(fn [expr] (and (con? expr) (some #(constant? %) (args expr))))
       (fn [expr] (let [elem (some #(if (constant? %) %) (args expr))]
                    (if (constant-true? elem)
                      (rule-application
                        (apply con (map rule-application (filter (fn [arg] (not (= arg elem))) (args expr)))))
                      (constant false))))]
      [(fn [expr] (and (dis? expr) (some #(constant? %) (args expr))))
       (fn [expr] (let [elem (some #(if (constant? %) %) (args expr))]
                    (if (constant-false? elem)
                      (rule-application
                        (apply dis (map rule-application (filter (fn [arg] (not (= arg elem))) (args expr)))))
                      (constant true))))]
      [(fn [expr] (and (= 1 (count (args expr))) (or (con? expr) (dis? expr))))
       (fn [expr] (second expr))])))

(defn constant-check [expr]
  (if (variable? expr)
    false
    (if (constant? expr)
      true
      (not-every? false? (map constant-check (args expr))))))

(defn reduce-constants [expr]
  (if (and (constant-check expr) (not (constant? expr)))
    (reduce-constants (apply-rules constant-rule expr))
    expr))
