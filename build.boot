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
  '[clojure.java.io       :as io]
  '[boot.util             :refer [info]]
  '[adzerk.boot-cljs      :refer [cljs main-files]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload    :refer [reload]]
  '[crisptrutski.boot-cljs-test  :refer [exit! test-cljs]]
  '[pandeiro.boot-http    :refer [serve]])

(def test-suffix "-test")

(defn inject-test-ns
  [reqs]
  (loop [original reqs
         injected reqs]
    (if (empty? original)
      injected
      (recur (rest original)
             (let [ns (first original)
                   test-ns (str ns test-suffix)]
               (if (contains? reqs test-ns)
                 injected
                 (conj injected (symbol test-ns))))))))

(defn add-test-ns-to-spec
  [new-fs-dir spec-edn]
  (let [in-file (tmp-file spec-edn)
        out-file (io/file new-fs-dir (tmp-path spec-edn))
        spec (read-string (slurp in-file))
        decorated (update-in spec [:require] inject-test-ns)]
    (info "Injecting test namespaces into %s: %s\n"
          (.getName in-file)
          (:require decorated))
    (spit out-file (pr-str decorated))))

(deftask testing
  [i ids IDS #{str} ""]
  (with-pre-wrap fileset
    (merge-env! :source-paths #{"test"})
    (if-let [build-spec-edns (seq (main-files fileset ids))]
      (let [tmp-dir (tmp-dir!)]
        (doall (map #(add-test-ns-to-spec tmp-dir %) build-spec-edns))
        (commit! (add-resource fileset tmp-dir)))
      fileset)))

(deftask auto-test []
  (comp (testing :ids #{"main"})
        (watch)
        (speak)
        (test-cljs)))

(deftask dev []
  (comp (testing :ids #{"main"})
        (serve)
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
