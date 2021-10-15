(ns lab1.task3)
(defn my-map
  [f coll]
  (reduce #(conj %1 (f %2))
    []
    coll)
  )
(defn my-filter
  [f coll]
  (reduce #(if (f %2) (conj %1 %2) %1)
    []
    coll)
  )

(println (my-map inc (range 10)))
(println (my-filter even? (range 10)))

