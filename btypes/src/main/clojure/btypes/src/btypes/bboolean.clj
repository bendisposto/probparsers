(ns btypes.bboolean
  (:require [btypes.bobject])
  (:import (de.hhu.stups.btypes BObject))
  (:gen-class
     :name de.hhu.stups.btypes.BBoolean
     :implements [de.hhu.stups.btypes.BObject]
              :constructors {[boolean] []}
              :init create-boolean
              :state bboolean))
  

(gen-class 
     :name de.hhu.stups.btypes.BBoolean
     :implements [de.hhu.stups.btypes.BObject]
     :constructors {[boolean] []}
     :init create-boolean
     :state bboolean  
  
  
     :methods [[or [de.hhu.stups.btypes.BBoolean] de.hhu.stups.btypes.BBoolean]
               [and [de.hhu.stups.btypes.BBoolean] de.hhu.stups.btypes.BBoolean]
               [equivalent [de.hhu.stups.btypes.BBoolean] de.hhu.stups.btypes.BBoolean]
               [implies [de.hhu.stups.btypes.BBoolean] de.hhu.stups.btypes.BBoolean]
               ;[equal [de.hhu.stups.btypes.BBoolean] de.hhu.stups.btypes.BBoolean]
               ;[unequal [de.hhu.stups.btypes.BBoolean] de.hhu.stups.btypes.BBoolean]
               [booleanValue [] boolean]])

(defn -create-boolean [^Boolean value]
  [[] value])


(defn -or [^de.hhu.stups.btypes.BBoolean this ^de.hhu.stups.btypes.BBoolean other]
  (de.hhu.stups.btypes.BBoolean. (or (.-bboolean this) (.-bboolean other))))

(defn -and [^de.hhu.stups.btypes.BBoolean this ^de.hhu.stups.btypes.BBoolean other]
  (de.hhu.stups.btypes.BBoolean. (and (.-bboolean this) (.-bboolean other))))

(defn -equivalent [^de.hhu.stups.btypes.BBoolean this ^de.hhu.stups.btypes.BBoolean other]
  (de.hhu.stups.btypes.BBoolean. (= (.-bboolean this) (.-bboolean other))))

(defn -implies [^de.hhu.stups.btypes.BBoolean this ^de.hhu.stups.btypes.BBoolean other]
  (de.hhu.stups.btypes.BBoolean. (or (not (.-bboolean this)) (.-bboolean other))))

(defn -booleanValue [^de.hhu.stups.btypes.BBoolean this]
  (.-bboolean this))

;(defn -equal [this other]
;  (de.hhu.stups.btypes.BBoolean. (= this other)))

;(defn -unequal [this other]
;  (de.hhu.stups.btypes.BBoolean. (not= this other)))




