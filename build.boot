(set-env!
  :src-paths    #{"src"}
  :rsc-paths    #{"html"}
  :dependencies '[[adzerk/boot-cljs      "0.0-2371-20" :scope "test"]
                  [adzerk/boot-cljs-repl "0.1.5"       :scope "test"]
                  [adzerk/boot-reload    "0.1.2"       :scope "test"]])

(require
  '[adzerk.boot-cljs      :refer :all]
  '[adzerk.boot-cljs-repl :refer :all]
  '[adzerk.boot-reload    :refer :all])
