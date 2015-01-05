(ns backend.api)

(def handler
  (fn [req]
    {:status  200
     :headers {"Content-Type" "application/json; charset=utf-8"}
     :body    "{\"ok\": true}"}))
