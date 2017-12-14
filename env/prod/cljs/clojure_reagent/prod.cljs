(ns clojure-reagent.prod
  (:require
    [clojure-reagent.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
