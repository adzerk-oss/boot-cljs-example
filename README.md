# boot-cljs-example

Example project using [the boot build tool][boot] with the [boot-cljs],
[boot-cljs-repl], and [boot-reload] tasks.

## Prepare

[Install boot][installboot].  Then, in a terminal:

```bash
boot -u
```

This will update boot to the latest stable release version. Since boot is
pre-alpha software at the moment, you should do this frequently.

## Build

In a terminal do:

```bash
boot serve -d target/ watch speak cljs-repl cljs -usO none reload
```

This builds a pipeline for your project:

* **`serve`** Starts a local web server.  This task is not from a
  library - it is defined in `build.boot` and uses
  [lein-simpleton](https://github.com/tailrecursion/lein-simpleton)
  underneath.
  * **`-d`** Use `target/` as the document root

* **`watch`** Starts incremental build loop. Project will be rebuilt when source
  files change.

* **`speak`** Audible notification (plays a sound file) for each build iteration,
  notifying of errors or warnings when appropriate.

* **`cljs-repl`** Starts REPL and websocket servers. The browser client will 
  connect to the websocket when the CLJS REPL is started (see below).

* **`cljs`** Compiles ClojureScript namespaces to JavaScript.
  * **`-u`** Add `<script>` tags to HTML files when optimizations is `none`.
  * **`-s`** Create source maps for compiled JavaScript files.
  * **`-O none`** Use optimizations `none` (no [GClosure][gclosure] compiler pass).

* **`reload`** Starts live-reload websocket server and connects browser client
  to it. Resources (stylesheets, images, HTML, JavaScript) in the page are
  reloaded when they change.

You can view the generated content by opening
[http://localhost:3000/index.html](http://localhost:3000/index.html)
in your browser.

## Start Browser REPL

With the build pipeline humming in the background, you can connect to the running nREPL
server with either your IDE or at the command line in a new terminal:

```bash
boot repl --client
```

Then, you can start a CLJS REPL:

```clojure
boot.user=> (start-repl)
```

The page will automatically reload and connect to the CLJS REPL websocket.

## License

Copyright © 2014 Adzerk

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[boot]:             https://github.com/boot-clj/boot
[cider]:            https://github.com/clojure-emacs/cider
[boot-cljs]:        https://github.com/adzerk/boot-cljs
[boot-cljs-repl]:   https://github.com/adzerk/boot-cljs-repl
[boot-reload]:      https://github.com/adzerk/boot-reload
[installboot]:      https://github.com/boot-clj/boot#install
[gclosure]:         https://developers.google.com/closure/compiler/
