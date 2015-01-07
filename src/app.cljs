(ns app
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs-utils.core :refer [by-id]]))

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/h1 nil (:text data)))))

(om/root widget {:text "Hello world! from Om"}
         {:target (by-id "container")})

