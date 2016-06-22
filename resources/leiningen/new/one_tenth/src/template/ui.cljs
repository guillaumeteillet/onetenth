(ns template.ui
  (:require
   [template.dispatch :as dispatch])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]
   [sablono.core :as sab :refer [html]]))

(defn input-swap [state txt id]
  (let [data (.-value (.-target txt))]
    (swap! state assoc-in [:data id] data)
    ))

(defn component [state]
  (sab/html [:div
             [:input {:type "text"
                      :on-change #(input-swap state %1 :input)}]
             [:button {
                       :id "test"
                       :class "maclass"
                       :on-click #(dispatch/emit % state)} "DON'T CLICK"]]))

(defn page-demo [state]
  (sab/html [:div
             [:h1 "App"]
             (component state)]))

(defmulti page
  "Dispatch on state -> page shown"
  (fn [state] (get-in @state [:ui :page-shown])))

(defmethod page :start-demo [state]
  (html [:div {:class "page" :key "start-demo"}
         (page-demo state)]))

(defn app
  "Show or transition between application pages"
  ([state]
   (page state))

  ([state transition-name transistion-timeout]
   (.createElement
    js/React js/React.addons.CSSTransitionGroup
    #js {:transitionName transition-name
         :transistionTimeout transistion-timeout
         :transitionEnterTimeout transistion-timeout
         :transitionLeaveTimeout transistion-timeout
         :transitionAppear true
         :transitionAppearTimeout transistion-timeout}
    (page state))))
