(set-env!
 :src-paths    #{"src"}
 :rsc-paths    #{"html"}
 :dependencies '[[adzerk/boot-cljs      "0.0-2371-27" :scope "test"]
                 [adzerk/boot-cljs-repl "0.1.6"       :scope "test"]
                 [adzerk/boot-reload    "0.1.7"       :scope "test"]])

(require
 '[adzerk.boot-cljs      :refer :all]
 '[adzerk.boot-cljs-repl :refer :all]
 '[adzerk.boot-reload    :refer :all]
 '[boot.pod              :as pod]
 '[boot.util             :as util]
 '[boot.core             :as core])

(deftask serve
  "Start a web server on localhost and serve a directory.

   If no directory is specified the current one is used.  Listens on
   port 3000 by default."
  [d dir  PATH str "The directory to serve."
   p port PORT int "The port to listen on."
   b block     bool "Whether or not to block; default is false."]
  (let [worker (pod/make-pod {:dependencies '[[ring/ring-jetty-adapter "1.3.1"]
                                              [compojure "1.2.1"]]})
        dir    (or dir ".")
        port   (or port 3000)]
    (core/cleanup
     (util/info "<< stopping Jetty... >>")
     (pod/eval-in worker (.stop server)))
    (let [task (with-pre-wrap
                 (pod/eval-in worker
                              (require '[ring.adapter.jetty :refer [run-jetty]]
                                       '[compojure.handler  :refer [site]]
                                       '[compojure.route    :refer [files]])
                              (def server (run-jetty (files "/" {:root ~dir}) {:port ~port :join? false})))
                 (util/info "<< started web server on http://localhost:%d (serving: %s) >>\n" port dir))]
      (if block
        (comp task (wait))
        task))))

;;; boot serve # start and stop
;;; boot serve -b # start and wait
