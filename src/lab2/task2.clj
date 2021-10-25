(ns lab2.task2)

(def delta 1/100)
(defn square [x] (* x x))

(defn step-area [f a b]
  (* 1/2 (- b a) (+ (f a) (f b))))

(defn lazy-area-seq [f]
  (reductions + 0 (map #(step-area f % (+ delta %)) (iterate #(+ delta %) 0))))

(defn get-area [seq-gen f]
  (let [seq (seq-gen f)]
    (fn [x] (nth seq (/ x delta)))))

(defn get-antiderivative-lazy [f]
  (get-area lazy-area-seq f))

(let [antiderivative (get-antiderivative-lazy square)]
  (time (do
          (antiderivative 1000)
          (antiderivative 1200)
          (antiderivative 1400)
          (antiderivative 1600)
          (antiderivative 1800)
          (antiderivative 2000))))