(ns frontend.views.util
  (:require [cljs.core.async :as async]))

(defn button
  ([app-state k label]
   (button app-state k label nil))
  ([app-state k label data]
   (button app-state k label data nil))
  ([app-state k label data disabled?]
   [:button {:disabled (if (fn? disabled?) (disabled?))
             :on-click (fn [_]
                         (async/put! (:write-events @app-state)
                                     [k {:label label, :data (if (fn? data)
                                                               (data)
                                                               data)}]))}
    label]))

(defn record-in [state ks ev]
  (swap! state assoc-in ks (.-value (.-target ev))))

(defn input [{:keys [state path type placeholder autofocus? class-name]}]
  [:input {:type        (or type "text")
           :class-name  (name class-name)
           :placeholder (name placeholder)
           :autofocus   (if autofocus? "autofocus")
           :on-change   #(record-in state path %)}])
