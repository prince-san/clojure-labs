(ns lab3.task1)
(defn divide-coll [coll thread-count]
  (let [size (quot (count coll) thread-count)]
   (loop [n thread-count coll coll res-coll '()]
    (if (> n 1)
      (recur (dec n) (drop size coll) (concat res-coll (list (take size coll))))
      (concat res-coll (list coll))))))

(defn parallel-filter [pred coll thread-count]
  (->>
    (divide-coll coll thread-count)
    (map #(future (doall (filter pred %))))
    (doall)
    (mapcat deref)))
(println (divide-coll (range 10) 7))
;(time (println (parallel-filter #(do (Thread/sleep 100) (even? %)) (range 16) 4)))

(let [coll (range 16)]
  (time
      (doall (parallel-filter #(do (Thread/sleep 100) (even? %)) coll 4))))

;(println (parallel-filter even? (range 100) 4))

;(let [coll (doall (range 1000000))]
;  (time (parallel-filter even? coll 4)))
;
;(time (parallel-filter even? (range 1000000) 2))
;(time (parallel-filter even? (range 1000000) 2))
;(time (parallel-filter even? (range 1000000) 2))

