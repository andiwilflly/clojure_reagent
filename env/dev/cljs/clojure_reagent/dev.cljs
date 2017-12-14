(ns ^:figwheel-no-load clojure-reagent.dev
  (:require
    [clojure-reagent.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
