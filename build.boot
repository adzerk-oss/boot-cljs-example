(set-env!
 :src-paths    #{"src"}
 :rsc-paths    #{"html"}
 :dependencies '[[adzerk/boot-cljs      "0.0-2371-27" :scope "test"]
                 [adzerk/boot-cljs-repl "0.1.6"       :scope "test"]
                 [adzerk/boot-reload    "0.1.7"       :scope "test"]
                 [pandeiro/boot-http    "0.2.0"       :scope "test"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.http         :refer [serve]])
