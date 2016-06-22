(ns template.dispatch
  (:require
   [sablono.core :as sab :include-macros true]
   [clojure.string :as str]
   [cljs.core.async :refer [chan put! <!]])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]
   [cljs.core.async.macros :refer [go-loop go]]))

(defonce pipe (chan) )

(defn empty-or-nil? [s]
  (or (nil? s) (< (.-length s) 1)))

(defn process-events [channel]
  (go-loop []
    (when-let [[native-event state sender message-type value] (<! channel)]
      (println native-event state sender message-type value)
      (recur))))

(process-events pipe)

(defn emit
  ([native-event state]
   (let [id (.-id (.-target native-event))
         css-class (first (str/split (.-className (.-target native-event)) " "))
         sender (if id (keyword id) (keyword css-class))
         message-type (keyword (.-type native-event))]
     (if (and (empty-or-nil? id) (empty-or-nil? css-class))
       (throw (js/Error. "Cannot Emit without id or class."))
       (emit native-event state sender message-type nil))))
  ([native-event state sender message-type value]
   (put! pipe [native-event state sender message-type value])))
