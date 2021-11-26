(ns lab4.task-v2
  (:require [lab4.definition.variable :refer :all])
  (:require [lab4.definition.constant :refer :all])
  (:require [lab4.definition.operation :refer :all])
  (:require [lab4.rule.util :refer :all])
  (:require [lab4.rule.remove-implication :refer :all])
  (:require [lab4.rule.de-morgan :refer :all])
  (:require [lab4.rule.distributive-rule :refer :all])
  (:require [lab4.rule.associative-rule :refer :all]))


(def idempotence-rule
  (list
    [(fn [expr] (variable? expr))
     (fn [expr] (identity expr))]
    [(fn [expr] (and (con? expr) (not (apply distinct? (args expr)))))
     (fn [expr] (apply con (map #(apply-rules idempotence-rule %) (distinct (args expr)))))]
    [(fn [expr] (identity true))
     (fn [expr] (cons (first expr) (map #(apply-rules idempotence-rule %) (args expr))))]))

(defn to-DNF [expr]
  (->> (apply-rules remove-implication expr)
       (apply-rules de-morgan)
       (apply-rules distributive-rule)
       (apply-rules associative-rules)
       (apply-rules idempotence-rule))
  )

;(println (apply-rules imp-rule (imp (variable :a) (variable :b) (imp (variable :x) (variable :y) (variable :z)))))
;(println (apply-rules imp-rule (imp (imp (variable :a) (variable :b)) (variable :c) (variable :d))))
;(println (apply-rules double-inv-rule (apply-rules de-morgan-rules (inv (dis (variable :b) (variable :c) (con (inv (variable :a)) (variable :x)))))))
;(println (apply-rules double-inv-rule (con (inv (inv (inv (variable :b)))) (inv (inv (inv (inv (variable :a))))))))
;(println (apply-rules distributive-rule (con (variable :a) (dis (variable :b) (variable :c)))))
;(println (apply-rules associative-rules (apply-rules distributive-rule (con (dis (variable :a) (variable :b)) (dis (variable :x) (variable :y))))))
;(println (apply-rules idempotence-rule (con (variable :x) (variable :x) (con (variable :x) (variable :x)))))
(let [expr (inv (dis (imp (variable :x) (variable :y)) (inv (imp (variable :y) (variable :z)))))]
  (println (to-DNF expr)))
;(println (to-DNF (inv (dis (imp (variable :x) (variable :y)) (inv (imp (variable :y) (variable :z)))))))