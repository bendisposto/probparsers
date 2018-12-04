(defproject btypes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :aot [btypes.bobject
        btypes.bcouple
        btypes.benum
        btypes.binteger
        btypes.bboolean
        btypes.bset
        btypes.butils])
  
