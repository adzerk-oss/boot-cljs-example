(ns app
  (:require [goog.net.XhrIo :as xhr]))

(defonce c (.. js/document (createElement "DIV")))

(aset c "innerHTML" "<p>i'm dynamically created!</p>")
(.. js/document (getElementById "container") (appendChild c))

(.log js/console "ok?")

(defn connect []
  (xhr/send "http://localhost:9999" (fn [resp] (.log js/console "response" resp))))

(.log js/console "some")
