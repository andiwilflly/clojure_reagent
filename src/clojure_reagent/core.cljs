(ns clojure-reagent.core
	(:require [reagent.core :as r]
	          [clojure-reagent.test :as test]))

(def log (.-log js/console))


(def click-count (r/atom 0))
(defn on_click_count_change [key atom old new] (if (< new 3) (log "old:" old "new:" new ) (log "more than 3:" new)))
(add-watch click-count 'what?' on_click_count_change)

(def model1 (r/atom {:some 423 :test 55}))

(defn click_count_change [num] [:span  {:style {:color "blue" :backgroundColor "gray" }} num] )

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
	    [:button  {:on-click #(compare-and-set! click-count 2 42)} "rom 2 to 42!!"]
	    [:hr]
	    [:span "click_count_change: " (click_count_change 42)]
	    [:hr]
	    [:div (if (= @click-count 5) "5 Clicks done..." "Make 5 clicks plz..")]
	    (test/test_fn (* @click-count 2))
	])


;; -------------------------
;; Initialize app

(defn mount-root []
	(r/render [home-page] (.getElementById js/document "app")))

(defn init! []
	(mount-root))
