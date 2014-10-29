# boot-cljs-example

Example project using [the boot build tool][boot] with the [boot-cljs],
[boot-cljs-repl], and [boot-reload] tasks.

## Prepare

Download the boot binary for your system. Then, in a terminal:

```bash
boot -u
```

This will update boot to the latest stable release version. Since boot is
alpha software at the moment, you should do this frequently.

## Build

In a terminal do:

```bash
boot watch speak cljs-repl cljs -usO none reload
```

This builds a pipeline for your project:

* **`watch`** Starts incremental build loop. Project will be rebuilt when source
  files change.

* **`speak`** Audible notification (plays a sound file) for each build iteration,
  notifying of errors or warnings when appropriate.

* **`cljs-repl`** Task to handle starting a CLJS REPL and auto-connecting to the
  browser client.

* **`cljs`** Compiles `.cljs` files to JavaScript.
  * **`-u`** Adds `<script>` tags to `.html` files when optimizations is `none`.
  * **`-s`** Create source maps for compiled JavaScript files.
  * **`-O none`** Use optimizations `none` (no [GClosure][gclosure] compiler pass).

* **`reload`** Starts live-reload websocket server and connects browser client
  to it. Resources (stylesheets, images, HTML, JavaScript) in the page are
  reloaded when they change.

## Start Browser REPL

With the build pipeline humming in the background, connect to the running REPL
server and do:

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
[gclosure]:         https://developers.google.com/closure/compiler/
