(ns template.core
  (:require
   [sablono.core :as sab :include-macros true]
   [clojure.string :as str]
   [template.dispatch :as dispatch]
   [template.ui :as ui]
   [template.state :as state]
   [template.cards.app :as app]
   [cljs.core.async :refer [chan put! <!]]))

(enable-console-print!)

(defn render-app [dom-node]
  "Return an atom watcher fn
  to render the root React app component to dom-node."
  (fn [_ a o n]
    (.render js/ReactDOM (ui/app a "apptransition" 200) dom-node)
    ;update (non-React) Highcharts charts.
    ;(line-chart/update-line-chart a o n)
    ))

(defn main []
  "Conditionally start the app based on whether the #main-app-area
  node is on the page."
  (if-let [node (.getElementById js/document "main-app-area")]
    (do
      (println "App Init.")
      (add-watch state/app-state :render
                     (render-app node))
      ; Initialize application state
      ;(dispatch/emit nil state/app-state nil :init-app nil)
      (println "App Startup."))))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html
