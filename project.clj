(defproject wadlviz "1.0.0-SNAPSHOT"
  :description "A wadl file visualization tool based on GraphViz"
  :dependencies [[org.clojure/clojure "1.3.0"]
		 [org.clojure/data.xml "0.0.3"]
		 [org.clojure/data.zip "0.1.0"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]]
  :main wadlviz.core)