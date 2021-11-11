(ns lab3.task2)
(def thread-count 4)
(def batch-size 4)
(defn my-partition [batch-size coll]
  (when (not-empty coll)
    (lazy-seq (cons (take batch-size coll) (my-partition batch-size (drop batch-size coll))))))
(defn parallel-filter [pred coll thread-count batch-size]
  (->> (my-partition (* thread-count batch-size) coll)
       (map #(my-partition batch-size %))
       (mapcat #(->> (map (fn [x] (future (doall (filter pred x)))) %)
                     (doall)))
       (mapcat deref)))
(let [coll (range)]
  (time
    (take 8 (parallel-filter #(do (Thread/sleep 100) (even? %)) coll thread-count batch-size))))