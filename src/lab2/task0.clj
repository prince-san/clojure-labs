(ns lab2.task0)

(def delta 1/100)
(defn square [x] (* x x))

(defn step-area [f a b]
  (* 1/2 (- b a) (+ (f a) (f b))))

(defn get-antiderivative [f]
  (fn [x]
    (reduce #(+ %1 (step-area f %2 (+ delta %2))) 0 (range 0 x delta))))

(let [antiderivative (get-antiderivative square)]
  (time (do
          (antiderivative 1000)
          (antiderivative 1200)
          (antiderivative 1400)
          (antiderivative 1600)
          (antiderivative 1800)
          (antiderivative 2000))))