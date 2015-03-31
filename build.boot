(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs      "0.0-2814-3" :scope "test"]
                 [adzerk/boot-cljs-repl "0.1.9"      :scope "test"]
                 [adzerk/boot-reload    "0.2.6"      :scope "test"]
                 [boot-cljs-test/node-runner "0.1.0" :scope "test"]
                 [org.clojure/clojurescript "0.0-3123"  :scope "test"]
                 [pandeiro/boot-http    "0.3.0"      :scope "test"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[boot-cljs-test.node-runner :refer :all]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.http         :refer [serve]])

(deftask dev []
  (comp (serve :dir "target/")
        (watch)
        (speak)
        (reload)
        (cljs-repl)
        (cljs-test-node-runner :namespaces '[app.test])
        (cljs :source-map true :optimizations :none)
        (run-cljs-test)))

(deftask build []
  (comp (cljs :optimizations :advanced)))
