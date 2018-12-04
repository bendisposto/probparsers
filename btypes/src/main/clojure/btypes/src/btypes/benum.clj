(ns btypes.benum
  (:require [btypes.bobject])
  (:import (de.hhu.stups.btypes BObject))
  (:gen-class
     :name de.hhu.stups.btypes.BEnum
     :implements [de.hhu.stups.btypes.BObject]
              :constructors {[clojure.lang.Keyword] []}
              :init create-enum
              :state benum))

(gen-class 
     :name de.hhu.stups.btypes.BEnum
     :implements [de.hhu.stups.btypes.BObject]
     :constructors {[clojure.lang.Keyword] []}
     :init create-enum
     :state benum  
     :methods [[equal [de.hhu.stups.btypes.BEnum] de.hhu.stups.btypes.BBoolean]
               [unequal [de.hhu.stups.btypes.BEnum] de.hhu.stups.btypes.BBoolean]])

(defn -create-benum [^clojure.lang.Keyword value]
  [[] value])

(defn -equal [this other]
  (de.hhu.stups.btypes.BBoolean. (= this other)))

(defn -unequal [this other]
  (de.hhu.stups.btypes.BBoolean. (not= this other)))



