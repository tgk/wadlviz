(ns wadlviz.core
  (:import (java.io ByteArrayInputStream))
  (:require [clojure.xml :as xml])
  (:require [clojure.zip :as zip])
  (:require [clojure.data.zip.xml :as zf])
  (:require [clojure.pprint :as pprint]))

(defn get-struct-map [xml]
  (if-not (empty? xml)
    (let [stream (ByteArrayInputStream. (.getBytes (.trim xml)))]
      (xml/parse stream))))

(defn traverse [path res]
  (let [path (str path "/" (first (zf/xml-> res (zf/attr :path))))]
    (doseq [method (zf/xml-> res :method)
	    method-name (zf/xml-> method (zf/attr :name))]
      (let [method-names (zf/xml-> method :request :param (zf/attr :name))]
	(println path method-name method-names)))
    (doseq [child-res (zf/xml-> res :resource)]
      (traverse path child-res))))

(defn print-methods [filename]
  (let [xml (slurp filename)
	xml-map (get-struct-map xml)
	xml-zip (zip/xml-zip xml-map)
	resources (zf/xml-> xml-zip :resources :resource)]
    (doseq [res resources]
      (traverse "" res))))

(defn -main [& args]
  (doseq [filename args]
    (print-methods filename)))