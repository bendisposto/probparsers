(ns btypes.bset
  (:require [clojure.set :as s])
  (:require [btypes.bobject])
  (:require [btypes.binteger])
  (:require [btypes.bboolean])
  (:require [btypes.bcouple])
  (:import (de.hhu.stups.btypes BObject
                                BInteger
                                BBoolean
                                BCouple))
  (:gen-class
     :name de.hhu.stups.btypes.BSet
     :implements [de.hhu.stups.btypes.BObject java.util.Set]
              :constructors {[java.util.Set] []
                             [] []
                             [de.hhu.stups.btypes.BObject] []
                             ^:varargs ["[Lde.hhu.stups.btypes.BObject;"] []}
              :init create-bset
              :state bset))


(gen-class 
     :name de.hhu.stups.btypes.BSet
     :implements [de.hhu.stups.btypes.BObject java.util.Set]
     :constructors {[java.util.Set] []
                    [] []
                    [de.hhu.stups.btypes.BObject] []
                    ^:varargs ["[Lde.hhu.stups.btypes.BObject;"] []}
                    
     :init create-set
     :state bset  
  
  
     :methods [[intersect [de.hhu.stups.btypes.BSet] de.hhu.stups.btypes.BSet]
               [complement [de.hhu.stups.btypes.BSet] de.hhu.stups.btypes.BSet]
               [union [de.hhu.stups.btypes.BSet] de.hhu.stups.btypes.BSet]
               [card [] de.hhu.stups.btypes.BInteger]
               [elementOf [de.hhu.stups.btypes.BObject] de.hhu.stups.btypes.BBoolean]
               ;[equal [] de.hhu.stups.btypes.BBoolean]
               ;[unequal [] de.hhu.stups.btypes.BBoolean]
               [functionCall [de.hhu.stups.btypes.BObject] de.hhu.stups.btypes.BObject]
               [relationImage [de.hhu.stups.btypes.BSet] de.hhu.stups.btypes.BSet]
               ^:static [range [de.hhu.stups.btypes.BInteger de.hhu.stups.btypes.BInteger]
                               de.hhu.stups.btypes.BSet]])

(defn -create-set
  ([] [[] #{}])
  ([elements]
   [[] (cond 
         (set? elements) elements 
         (seq? elements) (seq elements)
          :else #{elements})])
  ([a & elements]
   [[] (conj (set elements) a)]))


(defn -newStorage [_]
  #{})

(defn -equals [this other]
  (and (= (class this) (class other))
       (= (.-bset this) (.bset other))))

(defmacro delegate [& symbols]
  (let [symbols (map #(if (seq? %) % (list %)) symbols)]
    (apply list 'do
           (for [[sym & args] symbols
                 :let [dash-sym (symbol (str "-" (name sym)))]]
             `(defn ~dash-sym [this# ~@args]
                (. (.-bset this#) ~sym ~@args))))))

(defmacro unsupported [& symbols]
  (let [symbols (map #(if (seq? %) % (list %)) symbols)]
    (apply list 'do
           (for [[sym & args] symbols
                 :let [dash-sym (symbol (str "-" (name sym)))]]
             `(defn ~dash-sym [_# ~@args] (throw (UnsupportedOperationException.)))))))

(delegate iterator toString size isEmpty hashCode (contains e) toArray (toArray a) (containsAll c))
(unsupported add remove clear removeAll (addAll c) (retainAll c))

(defn -intersect [^de.hhu.stups.btypes.BSet this ^de.hhu.stups.btypes.BSet other]
  (de.hhu.stups.btypes.BSet. (s/intersection (.-bset this) (.-bset other))))

(defn -complement [^de.hhu.stups.btypes.BSet this ^de.hhu.stups.btypes.BSet other]
  (de.hhu.stups.btypes.BSet. (s/difference (.-bset this) (.-bset other))))

(defn -union [^de.hhu.stups.btypes.BSet this ^de.hhu.stups.btypes.BSet other]
  (de.hhu.stups.btypes.BSet. (s/union (.-bset this) (.-bset other))))

(defn ^{:static true} -range [^de.hhu.stups.btypes.BInteger from ^de.hhu.stups.btypes.BInteger to]
  (de.hhu.stups.btypes.BSet. (set (map #(de.hhu.stups.btypes.BInteger. %) (range from (inc to))))))

(defn -relationImage [this domain] 
  (throw (Exception. "not yet implemented")))

(defn -card [^de.hhu.stups.btypes.BSet this]
  (de.hhu.stups.btypes.BInteger. (str (count (.-bset this)))))

(defn -elementOf [^de.hhu.stups.btypes.BSet this ^de.hhu.stups.btypes.BObject obj]
  (de.hhu.stups.btypes.BBoolean. (contains? (.-bset this) obj)))


;(defn -equal [this obj]
;  (de.hhu.stups.btypes.BBoolean. (= this obj)))

;(defn -unequal [this obj]
;  (de.hhu.stups.btypes.BBoolean. (not= this obj)))

;(de.hhu.stups.btypes.BSet/range (de.hhu.stups.btypes.BInteger. "1") (de.hhu.stups.btypes.BInteger. "2"))

