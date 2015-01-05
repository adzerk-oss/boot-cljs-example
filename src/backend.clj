(ns backend
  (:require [backend.api :as api]
            [ring.middleware.cors :as cors]))

(def system
  (-> api/handler
    (cors/wrap-cors
     :access-control-allow-origin  #".+"
     :access-control-allow-methods [:get :put :post :delete])))
