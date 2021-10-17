(ns lab2.task1)

(def delta 1/100)
(defn square [x] (* x x))

(let [
      mem-step (memoize (fn [rec f a b]
                          (if (= (- b a) delta)
                            (* 1/2 (- b a) (+ (f a) (f b)))
                            (+
                              (rec rec f a (- b delta))
                              (* 1/2 delta (+ (f (- b delta)) (f b)))))))
      get-antiderivative-mem (fn [f] (fn [x] (mem-step mem-step f 0 x)))
      antiderivative (get-antiderivative-mem square)]
  (time (do
          (antiderivative 1000)
          (antiderivative 1200)
          (antiderivative 1400)
          (antiderivative 1600)
          (antiderivative 1800)
          (antiderivative 2000))))