(defproject game-of-life "0.1.0"
  :description "Game of Life"
  :url "https://github.com/chapuyj/Kata-GameOfLife-Clojure"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.match "1.0.0"]]
  :main ^:skip-aot game-of-life.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
