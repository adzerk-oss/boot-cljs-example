(ns app)

(let [c (.. js/document (createElement "DIV"))]
  (aset c "innerHTML" "<p>i'm dynamically created omfg</p>")
  (.. js/document (getElementById "container") (appendChild c)))
