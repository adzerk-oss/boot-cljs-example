(ns app.test
  (:require [cljs.test :refer-macros [deftest testing is]]))

(deftest silly-test
  (is (= (+ 1 1)
         2)))
