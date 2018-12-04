(ns btypes.bcouple
  (:require [btypes.bobject])
  (:require [btypes.bboolean])
  (:import (de.hhu.stups.btypes BObject
                                BBoolean))
  (:gen-class
     :name de.hhu.stups.btypes.BCouple
     :implements [de.hhu.stups.btypes.BObject]
              :constructors {[de.hhu.stups.btypes.BObject de.hhu.stups.btypes.BObject] []}
              :init create-couple
              :state bcouple))


(gen-class 
     :name de.hhu.stups.btypes.BCouple
     :implements [de.hhu.stups.btypes.BObject]
     :constructors {[de.hhu.stups.btypes.BObject de.hhu.stups.btypes.BObject] []}
     :init create-couple
     :state bcouple  
  
  
     :methods [[first [] de.hhu.stups.btypes.BObject]
               [second [] de.hhu.stups.btypes.BObject]])
               ;[equal [de.hhu.stups.btypes.BCouple] de.hhu.stups.btypes.BBoolean]
               ;[unequal [de.hhu.stups.btypes.BCouple] de.hhu.stups.btypes.BBoolean]])

(defn -create-couple [first second]
  [[] (hash-map :first first, :second second)])


(defn -first [this]
  (:first this))

(defn -second [this]
  (:second this))

;(defn -equal [this other])
;  (de.hhu.stups.btypes.BBoolean. (= this other)))

;(defn -unequal [this other]
;  (de.hhu.stups.btypes.BBoolean. (not= this other)))




