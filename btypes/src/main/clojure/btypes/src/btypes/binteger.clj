(ns btypes.binteger
  (:require [btypes.bobject])
  (:require [btypes.bboolean])
  (:import (de.hhu.stups.btypes BObject
                                BBoolean))
  (:gen-class
              :name de.hhu.stups.btypes.BInteger
              :implements [de.hhu.stups.btypes.BObject]
              :extends java.lang.Number
              :constructors {[java.lang.String] []
                             [java.lang.Number] []}
              :init create-integer
              :state binteger))

(gen-class
     :name de.hhu.stups.btypes.BInteger
     :implements [de.hhu.stups.btypes.BObject]
     :extends java.lang.Number
     :constructors {[java.lang.String] []
                    [java.lang.Number] []}
     :init create-integer
     :state binteger  
     :methods [[plus [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BInteger]
               [minus [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BInteger]
               [multiply [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BInteger]
               [divide [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BInteger]
               [modulo [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BInteger]
               [power [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BInteger]
               [less [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BBoolean]
               [lessEqual [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BBoolean] 
               [greater [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BBoolean]
               [greaterEqual [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BBoolean]
               [equal [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BBoolean]
               [unequal [de.hhu.stups.btypes.BInteger] de.hhu.stups.btypes.BBoolean]
               [negative [] de.hhu.stups.btypes.BInteger]
               [positive [] de.hhu.stups.btypes.BInteger]])
   
(defn -create-integer [^String value]
  [[] (try (clojure.lang.BigInt/fromLong (Long/parseLong value)) 
        (catch Exception e (clojure.lang.BigInt/fromBigInteger (biginteger value))))])

(defn -equals [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (and (= (class this) (class other))
       (= (.-binteger this) (.-binteger other))))

(defn -hashCode [^de.hhu.stups.btypes.BInteger this]
  (+ 31 (.hashCode (.-binteger this))))

(defn -longValue [^de.hhu.stups.btypes.BInteger this]
  (.longValue (.-binteger this)))

(defn -doubleValue [^de.hhu.stups.btypes.BInteger this]
  (.doubleValue (.-binteger this)))

(defn -floatValue [^de.hhu.stups.btypes.BInteger this]
  (.floatValue (.-binteger this)))

(defn -intValue [^de.hhu.stups.btypes.BInteger this]
  (.intValue (.-binteger this)))

(defn -compareTo [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (compare (.-binteger this) (.-binteger other)))

(defn -plus [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BInteger. (str (+ (.-binteger this) (.-binteger other)))))

(defn -minus [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BInteger. (str (- (.-binteger this) (.-binteger other)))))

(defn -multiply [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BInteger. (str (* (.-binteger this) (.-binteger other)))))

(defn -divide [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BInteger. (str (quot (.-binteger this) (.-binteger other)))))

(defn -modulo [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BInteger. (str (mod (.-binteger this) (.-binteger other)))))

(defn -power [this other]
  (de.hhu.stups.btypes.BInteger. (str (^ this other))))

(defn -lessEqual [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BBoolean. (<= (.-binteger this) (.-binteger other))))

(defn -less [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BBoolean. (< (.-binteger this) (.-binteger other))))

(defn -greater [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BBoolean. (> (.-binteger this) (.-binteger other))))

(defn -greaterEqual [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BBoolean. (>= (.-binteger this) (.-binteger other))))

(defn -equal [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BBoolean. (= (.-binteger this) (.-binteger other))))

(defn -unequal [^de.hhu.stups.btypes.BInteger this ^de.hhu.stups.btypes.BInteger other]
  (de.hhu.stups.btypes.BBoolean. (not= (.-binteger this) (.-binteger other))))

(defn -negative [^de.hhu.stups.btypes.BInteger this]
  (de.hhu.stups.btypes.BInteger. (str (- (.-binteger this)))))

(defn -positive [^de.hhu.stups.btypes.BInteger this]
  this)





