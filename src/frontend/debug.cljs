(ns frontend.debug
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.core.async :as async :refer [<! chan]]))

(defn debug-events [app-state]
  (let [debug-events (chan)]
    (async/tap (:read-events @app-state) debug-events)
    (go-loop []
      (.log js/console "DEBUG-EVENTS" (pr-str (<! debug-events)))
      (recur))))

(defn debug-atom [state]
  (add-watch state (keyword (gensym)) (fn [_ _ _ n] (.log js/console "DEBUG-ATOM" (pr-str n)))))

