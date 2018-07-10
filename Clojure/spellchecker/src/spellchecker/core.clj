(ns spellchecker.core
  (:require [clojure.string :as str])
  (:import (org.apache.commons.lang3 StringUtils)))

(def words
    (set
      (map
        str/trim
        (str/split-lines
          (slurp "resources/words.txt")))))

(defn correct? [word]
  (contains? words word))

(defn distance [word1 word2]
  (StringUtils/getLevenshteinDistance word1 word2))

(defn min-distance [word]
  (apply min-key (partial distance word) words))

(defn -main
  "I'm the spellchecker!"
  [& args]
  (let [word (first args) best_guess (min-distance word)]
    (if (correct? word)
      (println "correct")
      (println "did you mean" best_guess))))
