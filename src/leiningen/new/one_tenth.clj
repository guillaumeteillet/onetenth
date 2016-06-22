(ns leiningen.new.one-tenth
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "one-tenth"))

(defn one-tenth
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' one-tenth project.")
    (->files data
             ["README.md" (render "README.md" data)]
             [".gitignore" (render ".gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/ui.cljs" (render "src/template/ui.cljs" data)]
             ["src/{{sanitized}}/state.cljs" (render "src/template/state.cljs" data)]
             ["src/{{sanitized}}/dispatch.cljs" (render "src/template/dispatch.cljs" data)]
             ["src/{{sanitized}}/core.cljs" (render "src/template/core.cljs" data)]
             ["src/{{sanitized}}/cards/app.cljs" (render "src/template/cards/app.cljs" data)]
             ["resources/public/cards.html" (render "resources/public/cards.html" data)]
             ["resources/public/index.html" (render "resources/public/index.html" data)]
             ["resources/public/css/template_style.css" (render "resources/public/css/template_style.css" data)]
      )))
