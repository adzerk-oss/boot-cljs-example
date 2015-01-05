(ns frontend.session
  (:require [cljs.core.async :as async :refer [<! chan]]
            [reagent.core :as r]))

(def app-state
  (let [events (chan)]
    (r/atom
     {:user   nil
      :data   {}
      :write-events events
      :read-events (async/mult events)})))
