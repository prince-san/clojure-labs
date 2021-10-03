(ns lab1.task4)
(defn generate-word-by-alphabet
  [word alphabet]
  (map #(conj word %) (filter #(false? (= (first word) %)) alphabet))
  )
(defn generate-words-by-alphabet
  [words alphabet]
  (reduce #(concat %1 (generate-word-by-alphabet %2 alphabet)) '() words)
  )
(defn generate-result
  [alphabet n]
  (nth (iterate #(generate-words-by-alphabet % alphabet) '(())) n)
  )

(println (generate-result '("a" (:b [:c "d"])) 3))
; ((a (:b [:c d]) a) ((:b [:c d]) a (:b [:c d])))

