(set-env!
 :source-paths   #{"src"}
 :resource-paths #{"html" "less"}
 :dependencies   '[[adzerk/boot-cljs      "0.0-2411-3" :scope "test"]
                   [adzerk/boot-cljs-repl "0.1.7"      :scope "test"]
                   [adzerk/boot-reload    "0.2.0"      :scope "test"]
                   [pandeiro/boot-http    "0.4.1"      :scope "test"]

                   [ring-cors             "0.1.6"]
                   [reagent               "0.5.0-alpha"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]]
 '[deraen.boot-less      :refer [less]])

(deftask dev []
  (comp (serve :dir "target")
        (watch)
        (speak)
        (reload)
        (cljs-repl)
        (less)
        (cljs :source-map    true
              :unified-mode  true
              :optimizations :advanced)))
