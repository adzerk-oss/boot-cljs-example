# boot-cljs-example [![Build Status][badge]][build]

Example project using [the boot build tool][boot] with the [boot-cljs],
[boot-cljs-repl], and [boot-reload] tasks.

## Prepare

[Install boot][installboot].  Then, in a terminal:

```bash
boot -u
```

This will update boot to the latest stable release version. Since boot is
pre-release software at the moment, you should do this frequently.

## Build

In a terminal do:

```bash
boot serve -d target/ watch speak reload cljs-repl cljs -usO none
```

This builds a pipeline for your project:

* **`serve`** Starts a local web server.  This task comes from the
  [pandeiro/boot-http](https://github.com/pandeiro/boot-http) library.
  * **`-d`** Use `target/` as the document root

* **`watch`** Starts incremental build loop. Project will be rebuilt when source
  files change.

* **`speak`** Audible notification (plays a sound file) for each build iteration,
  notifying of errors or warnings when appropriate.

* **`reload`** Starts live-reload websocket server and connects browser client
  to it. Resources (stylesheets, images, HTML, JavaScript) in the page are
  reloaded when they change.

* **`cljs`** Compiles ClojureScript namespaces to JavaScript.
  * **`-u`** Operate in "unified mode" - automate script inclusion
  * **`-s`** Create source maps for compiled JavaScript files.
  * **`-O none`** Use optimizations `none` (no [GClosure][gclosure] compiler pass).

You can view the generated content by opening
[http://localhost:3000/index.html](http://localhost:3000/index.html)
in your browser.

> *OutOfMemoryError Troubleshooting*
>
> boot provides a tool called _pods_ that make it possible for multiple
> independent Clojure classpaths to exist in the same JVM.  Task authors
> can use Maven dependencies without worrying about
> shadowing or otherwise interfering with the dependencies in other pods.
>
> One downside of pods is that their use results in higher-than-usual
> memory consumption by the JVM, particularly
> [PermGen](http://stackoverflow.com/questions/88235/dealing-with-java-lang-outofmemoryerror-permgen-space-error).
>
> If you are using Java 7, you may see errors related to PermGen.  You
> can consult the
> [JVM Options](https://github.com/boot-clj/boot/wiki/JVM-Options) wiki
> page for settings that can help.
>
> You may also consider upgrading to Java 8, as it
> [resolves many PermGen-related issues](http://www.infoq.com/news/2013/03/java-8-permgen-metaspace).

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

Reload the page in your browser.  Your REPL is now connected to the page.

## License

Copyright © 2014 Adzerk

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[badge]:            https://travis-ci.org/adzerk/boot-cljs-example.png?branch=master
[build]:            https://travis-ci.org/adzerk/boot-cljs-example
[boot]:             https://github.com/boot-clj/boot
[cider]:            https://github.com/clojure-emacs/cider
[boot-cljs]:        https://github.com/adzerk/boot-cljs
[boot-cljs-repl]:   https://github.com/adzerk/boot-cljs-repl
[boot-reload]:      https://github.com/adzerk/boot-reload
[installboot]:      https://github.com/boot-clj/boot#install
[gclosure]:         https://developers.google.com/closure/compiler/
