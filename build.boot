(set-env!
 :src-paths    #{"src"}
 :rsc-paths    #{"html"}
 :dependencies '[[adzerk/boot-cljs      "0.0-2371-20" :scope "test"]
                 [adzerk/boot-cljs-repl "0.1.5"       :scope "test"]
                 [adzerk/boot-reload    "0.1.3"       :scope "test"]])

(require
 '[adzerk.boot-cljs      :refer :all]
 '[adzerk.boot-cljs-repl :refer :all]
 '[adzerk.boot-reload    :refer :all]
 '[boot.pod              :as pod]
 '[boot.util             :as util]
 '[boot.core             :as core])

(deftask serve
  "Start a web server on 127.0.0.1 and serve a directory.

   If no directory is specified the current one is used.  Listens on
   port 3000 by default."
  [d dir  PATH str "The directory to serve."
   p port PORT int "The port to listen on."]
  (let [worker (future (pod/make-pod {:dependencies '[[lein-simpleton "1.3.0"]]}))
        dir    (or dir  ".")
        port   (or port 3000)]
    (core/cleanup
     (util/info "Stopping HTTP server...")
     (pod/eval-in @worker (.stop server 0)))
    (with-pre-wrap
      (pod/eval-in @worker
                   (require '[leiningen.simpleton :as ls])
                   (def server (ls/new-server ~port "/" (ls/fs-handler ~dir))))
      (util/info "<< started web server on http://127.0.0.1:%d (serving: %s) >>\n" port dir))))
