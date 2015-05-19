(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs           "0.0-3269-0"      :scope "test"]
                 [modnakasta/boot-cljs-repl  "0.1.10-SNAPSHOT" :scope "test"]
                 [com.cemerick/piggieback "0.2.0"              :scope "test"]
                 [org.clojure/tools.nrepl "0.2.10"             :scope "test"]
                 [adzerk/boot-reload         "0.2.6"           :scope "test"]
                 [boot-cljs-test/node-runner "0.1.0"           :scope "test"]
                 [org.clojure/clojurescript  "0.0-3269"        :scope "test"]
                 [pandeiro/boot-http "0.6.3-SNAPSHOT"          :scope "test"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[boot-cljs-test.node-runner :refer :all]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.http         :refer [serve]])

(deftask dev []
  (set-env! :source-paths #{"src" "test"})
  (comp (serve :dir "target/")
        (watch)
        (speak)
        (reload :on-jsload 'app.core/main)
        (cljs-repl)
        (cljs-test-node-runner :namespaces '[app.test])
        (cljs :source-map true :optimizations :none)
        (run-cljs-test)))

(deftask build []
  (set-env! :source-paths #{"src"})
  (comp (cljs :optimizations :advanced)))
