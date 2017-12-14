(ns clojure-reagent.core
	(:require [reagent.core :as r]
	          [clojure-reagent.test :as test]))

(def log (.-log js/console))

(log (/ 5 2))

(def click-count (r/atom 0))

;; -------------------------
;; Views
(defn on_click [e] (log "pressed!"))


(defn text_div [children]
	[:div "[Text div " children "]"])


(defn home-page []
	[:div
		[:h2 "Welcome to Reagent"]
		[:p "paragraph"]
        [text_div [:span {:style {:color "red"}} "span inside text_div"]]
	    [:button  {:on-click on_click} "Press me.."]
	    [:hr]
	    [:h4 "click count: " @click-count]
	    [:button  {:on-click #(swap! click-count inc)} "Increment"]
	    [:button  {:on-click #(swap! click-count dec)} "Ddecrement"]

	])


;; -------------------------
;; Initialize app

(defn mount-root []
	(r/render [home-page] (.getElementById js/document "app")))

(defn init! []
	(mount-root))
