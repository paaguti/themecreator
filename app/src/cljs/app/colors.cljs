(ns app.colors
  (:require [goog.color :as color]))

(defn darken
  ([colorstring] (darken colorstring 0.2))
  ([colorstring factor]
   (color/rgbArrayToHex
    (color/darken (color/hexToRgb colorstring) factor))))