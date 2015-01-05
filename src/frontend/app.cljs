(ns frontend.app
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :as async :refer [<! chan]]
            [reagent.core :as r]
            [alandipert.storage-atom :refer [local-storage]]
            [frontend.debug :refer [debug-events]]
            [frontend.session :refer [app-state]]
            [frontend.views.userbar :refer [user-status-bar]]
            [frontend.views.update :refer [post-update]]
            [frontend.views.feed :refer [feed]]))

(defn view [app-state]
  [:div.container
   [user-status-bar app-state]
   (when (:user @app-state)
     [post-update app-state])
   (when (:user @app-state)
     [feed app-state])])

(def root (.getElementById js/document "root"))

(defn get-json []
  (http/get "http://localhost:9090" {:with-credentials? false}))

(defn init []
  (debug-events app-state)
  (r/render [view app-state] root)
  (go (async/put! (:write-events @app-state) [:xhr (:body (<! (get-json)))])))

(.addEventListener js/window "DOMContentLoaded" init)
