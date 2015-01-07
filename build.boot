(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs      "0.0-2411-3" :scope "test"]
                 [adzerk/boot-cljs-repl "0.1.7"      :scope "test"]
                 [adzerk/boot-reload    "0.2.0"      :scope "test"]
                 [pandeiro/boot-http    "0.3.0"      :scope "test"]
                 [org.danielsz/cljs-utils "0.1.0-SNAPSHOT"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [om "0.8.0-rc1"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.http         :refer [serve]])
