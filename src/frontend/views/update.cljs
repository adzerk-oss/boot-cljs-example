(ns frontend.views.update
  (:require [clojure.string :as s]
            [reagent.core :as r]
            [frontend.views.util :refer [record-in button]]))

(defn post-update [app-state]
  (let [form-state (r/atom {})]
    [:div.post-update
     [:textarea {:placeholder "Post update here..."
                 :on-change #(record-in form-state [:post] %)}]
     [button app-state :post-send-click
      "Post"
      (fn [] @form-state)
      (fn []
        (let [{:keys [post]} @form-state]
          (or (not post) (empty? (s/trim post)))))]]))
