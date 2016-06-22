(ns template.cards.app
  (:require
   [template.ui :as ui])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]
   [sablono.core :as sab :refer [html]]))

(defcard app
  (fn [state] (ui/app state))
  {:ui {:page-shown :start-demo}} ; initial state
  {:frame true         ;; whether to enclose the card in a padded frame
   :heading true       ;; whether to add a heading panel to the card
   :padding true       ;; whether to have padding around the body of the card
   :hidden false        ;; whether to diplay the card or not
   :inspect-data true  ;; whether to display the data in the card atom
   :watch-atom true    ;; whether to watch the atom and render on change
   :history true       ;; whether to record a change history of the atom
   :classname "app"})

(defcard component
  (fn [state] (ui/component state))
  {} ; initial state
  {:frame true         ;; whether to enclose the card in a padded frame
   :heading true       ;; whether to add a heading panel to the card
   :padding true       ;; whether to have padding around the body of the card
   :hidden false        ;; whether to diplay the card or not
   :inspect-data true  ;; whether to display the data in the card atom
   :watch-atom true    ;; whether to watch the atom and render on change
   :history true       ;; whether to record a change history of the atom
   :classname "app"})
