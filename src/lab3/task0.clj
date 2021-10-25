(ns lab3.task0)

(let [coll (range)]
  (time
    (take 8 (filter #(do (Thread/sleep 100) (even? %)) coll))))