(ns app
  (:require [goog.net.XhrIo :as xhr]
            [reagent.core :as r]))

(defn structure []
  [:div.container
   [:h1 "Hello"]
   [:p "This is the app"]])

(def root (.. js/document (getElementById "root")))

(defn connect []
  (xhr/send "http://localhost:9999"
            (fn [resp] (.log js/console "response" resp))))

(defn init []
  (r/render [structure] root))

(.. js/window (addEventListener "DOMContentLoaded" init))
