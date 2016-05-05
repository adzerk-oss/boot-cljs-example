(set-env!
 :resource-paths #{"src" "html"}
 :dependencies '[[adzerk/boot-cljs            "1.7.228-1"      :scope "test"]
                 [adzerk/boot-cljs-repl       "0.3.0"          :scope "test"]
                 [adzerk/boot-reload          "0.4.5"          :scope "test"]
                 [pandeiro/boot-http          "0.7.1-SNAPSHOT" :scope "test"]
                 [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT" :scope "test"]
                 [org.clojure/clojure         "1.7.0"]
                 [org.clojure/clojurescript   "1.7.228"]
                 [com.cemerick/piggieback     "0.2.1"          :scope "test"]
                 [weasel                      "0.7.0"          :scope "test"]
                 [org.clojure/tools.nrepl     "0.2.12"         :scope "test"]])

(require
  '[adzerk.boot-cljs      :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload    :refer [reload]]
  '[crisptrutski.boot-cljs-test  :refer [exit! test-cljs]]
  '[pandeiro.boot-http    :refer [serve]])

(deftask testing []
  (merge-env! :resource-paths #{"test"})
  identity)

(deftask auto-test []
  (comp (testing)
        (watch)
        (speak)
        (test-cljs)))

(deftask dev []
  (comp (serve :dir "target/")
        (watch)
        (speak)
        (reload :on-jsload 'app.core/main)
        (cljs-repl)
        (cljs :source-map true :optimizations :none)))

(deftask test []
  (comp (testing)
        (test-cljs)
        (exit!)))

(deftask build []
  (cljs :optimizations :advanced))
