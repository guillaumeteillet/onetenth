(ns template.state
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]
   [sablono.core :as sab :refer [html]]))

(defonce app-state (atom {}))
