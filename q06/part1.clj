;; This is a naive solution
(def initial-school [1 1 1 2 1 1 2 1 1 1 5 1 1 1 1 1 1 1 1 1 1 2 1 1 1 1 1 4 1 1 1 1 3 1 1 3 1 1 1 4 1 5 1 3 1 1 1 1 1 5 1 1 1 1 1 5 5 2 5 1 1 2 1 1 1 1 3 4 1 1 1 1 1 1 1 1 1 1 1 1 2 1 1 1 1 5 4 1 1 1 1 1 5 1 2 4 1 1 1 1 1 3 3 2 1 1 4 1 1 5 5 1 1 1 1 1 2 5 1 4 1 1 1 1 1 1 2 1 1 5 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 4 3 1 1 3 1 3 1 4 1 5 4 1 1 2 1 1 5 1 1 1 1 1 5 1 1 1 1 1 1 1 1 1 4 1 1 4 1 1 1 1 1 1 1 5 4 1 2 1 1 1 1 1 1 1 1 1 1 1 3 1 1 1 1 1 1 1 1 1 1 4 1 1 1 2 1 4 1 1 1 1 1 1 1 1 1 4 2 1 2 1 1 4 1 1 1 1 1 1 3 1 1 1 1 1 1 1 1 3 2 1 4 1 5 1 1 1 4 5 1 1 1 1 1 1 5 1 1 5 1 2 1 1 2 4 1 1 2 1 5 5 3])

(defn count-zeroes [coll]
    (->> coll
        (filter zero?)
        count))

(defn generate-offspring [school]
    (let [n-newborns (count-zeroes school)]
        (into [] (concat school (take n-newborns (repeat 9))))))

(defn run-internal-clock [clock]
    (if (= clock 0)
        6
        (dec clock)))

(defn process-school [school]
    (->> school
        generate-offspring
        (map run-internal-clock)))

(defn process-school-for [n-days school]
    (if (zero? n-days)
        school
        (recur (dec n-days) (process-school school))))

(count (process-school-for 80 initial-school))
