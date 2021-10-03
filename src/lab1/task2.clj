(ns lab1.task2)
(defn generate-word-by-alphabet
  [word alphabet]
  (loop [alphabet alphabet res-list '()]
    (if (not-empty alphabet)
      (recur (rest alphabet)
        (if (not= (first alphabet) (first word))
          (cons (cons (first alphabet) word) res-list)
          res-list))
      res-list))
  )
(defn generate-words-by-alphabet
  [words alphabet]
  (loop [words words res-list '()]
    (if (not-empty words)
      (recur (rest words) (concat res-list (generate-word-by-alphabet (first words) alphabet)))
      res-list))
  )
(defn generate-result
  [alphabet n]
  (loop [words '(()) n n]
    (if (> n 0)
      (recur (generate-words-by-alphabet words alphabet) (dec n))
      words))
  )

(println (generate-result '("a" (:b [:c "d"])) 3))
; ((a (:b [:c d]) a) ((:b [:c d]) a (:b [:c d])))