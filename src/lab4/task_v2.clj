(ns lab4.task-v2
  (:require [lab4.definition.variable :refer :all])
  (:require [lab4.definition.constant :refer :all])
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.rule.remove-implication :refer :all])
  (:require [lab4.rule.de-morgan :refer :all])
  (:require [lab4.rule.distributive-rule :refer :all])
  (:require [lab4.rule.associative-rule :refer :all])
  (:require [lab4.rule.idempotence-rule :refer :all])
  (:require [lab4.rule.constant-rule :refer :all]))

(defn to-DNF [expr]
  (->> (apply-rules remove-implication expr)
       (apply-rules de-morgan)
       (apply-rules distributive-rule)
       (apply-rules associative-rules)
       (apply-rules idempotence-rule)
       (reduce-constants))
  )

(defn cast-to-const [expr & vars]
  (if (variable? expr)
    (let [val (some #(if (same-variable (first %) expr) (second %)) vars)]
      (if (nil? val)
        expr
        val))
    (apply copy-operation expr (map #(apply cast-to-const % vars) (args expr)))))

(defn eval-expr [expr & vars]
  (->> (apply cast-to-const expr vars)
       (to-DNF)))

(println (to-DNF (inv (dis (imp (variable :x) (variable :y)) (inv (imp (variable :y) (variable :z)))))))
(println (eval-expr (inv (dis (imp (variable :x) (variable :y)) (inv (imp (variable :y) (variable :z))))) [(variable :z) (constant true)] [(variable :y) (constant false)]))