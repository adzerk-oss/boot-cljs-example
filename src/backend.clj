(ns backend
  (:require [ring.middleware.cors :as cors]))

(def api
  (fn [req]
    {:status  200
     :headers {"Content-Type" "application/json; charset=utf-8"}
     :body    "{\"ok\": true}"}))

(-> api
  (cors/wrap-cors :access-control-allow-origin #".+"
                  :access-control-allow-methods [:get :put :post :delete]))
