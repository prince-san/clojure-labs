(ns lab2.task1)

(def delta 1/100)
(defn square [x] (* x x))

(defn step-area [f a b]
  (* 1/2 (- b a) (+ (f a) (f b))))

(defn rec-step-area [rec f a b]
  (if (= (- b a) delta)
    (step-area f a b)
    (+ (rec rec f a (- b delta)) (step-area f (- b delta) b))))

(defn get-antiderivative-mem [f]
  (let [mem-step (memoize rec-step-area)]
    (fn [x] (mem-step mem-step f 0 x))))

(let [antiderivative (get-antiderivative-mem square)]
  (time (do
          (antiderivative 1000)
          (antiderivative 1200)
          (antiderivative 1400)
          (antiderivative 1600)
          (antiderivative 1800)
          (antiderivative 2000))))