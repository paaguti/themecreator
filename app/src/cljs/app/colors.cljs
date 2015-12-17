(ns app.colors
  (:require [goog.color :as color]
            [app.db :as db]))

(defn hexToRgb
  "convert hex color to rgb in values 0..1"
  [hexcolor]
  (mapv #(/ % 255.0) (color/hexToRgb hexcolor)))

(defn rgbToXyz
  [rgbcolor]
  (let [[r g b] (mapv #(* % 100) (mapv 
                                  #(if (> % 0.04045)
                                     (js/Math.pow (/ (+ % 0.055) 1.055) 2.4)
                                     (/ % 12.92)) rgbcolor))]
    [(+ (* r 0.4124) (* g 0.3576) (* b 0.1805))
     (+ (* r 0.2126) (* g 0.7152) (* b 0.0722))
     (+ (* r 0.0193) (* g 0.1192) (* b 0.9505))]))

(def xyzreferencewhite [95.047 100 108.883])

(defn xyzToLab
  [xyzcolor]
  (let [[x y z] (mapv #(if (> % 0.008856)
                         (js/Math.pow % (/ 1 3))
                         (+ (* % 7.787) (/ 16 116)))
                      (mapv / xyzcolor xyzreferencewhite))]
    [(- (* 116 y) 16)
     (* 500 (- x y)) 
     (* 200 (- y z))]))

(defn radToDegrees
  [h]
  (if (> h 0)
    (* 180 (/ h (.-PI js/Math)))
    (- 360 (* 180 (/ (js/Math.abs h) (.-PI js/Math))))))

(defn labToLch
  [labcolor]
  (let [[l a b] labcolor
        h (radToDegrees (js/Math.atan2 b a))]
    [l
     (js/Math.sqrt (+ (* a a) (* b b)))
     (cond
       (< h 0) (+ h 360)
       (>= h 360) (- h 360)
       :else h)]))

(defn lchToLab
  [lchcolor]
  (let [[l c h] lchcolor
        hrad (/ (* h (.-PI js/Math)) 180)]
    [l
     (* (js/Math.cos hrad) c)
     (* (js/Math.sin hrad) c)]))

(defn labToXyz
  [labcolor]
  (let [[l a b] labcolor
        x (+ (/ a 500) (/ (+ l 16) 116))
        y (/ (+ l 16) 116)
        z (- (/ (+ l 16) 116) (/ b 200))
        xyz [x y z]]
    (mapv * xyzreferencewhite
          (mapv
           (fn [i] (let [cube (js/Math.pow i 3)]
                     (if (> cube 0.008856)
                       cube
                       (/ (- i (/ 16 116)) 116))))
           xyz))))

(defn xyzToRgb
  [xyzcolor]
  (let [[x y z] (mapv #(/ % 100) xyzcolor)
        r (+ (* x 3.2406) (* y -1.5372) (* z -0.4986))
        g (+ (* x -0.9689) (* y 1.8758) (* z 0.0415))
        b (+ (* x 0.0557) (* y -0.2040) (* z 1.0570))
        rgb [r g b]]
    (mapv #(* % 255) (mapv #(if (> % 0.0031308)
                              (- (* 1.055 (js/Math.pow % (/ 1 2.4))) 0.055)
                              (* % 12.92)) rgb))))


(defn hexToLch
  [hexcolor]
  (labToLch (xyzToLab (rgbToXyz (hexToRgb hexcolor)))))

(defn darken
  "darken darkens a rgb color by a given factor.
   if no factor is provided, the color will be darkened 
   with the factor of 0.2."
  ([colorstring] (darken colorstring 0.2))
  ([colorstring factor]
   (color/rgbArrayToHex
    (color/darken (color/hexToRgb colorstring) factor))))

(defn lighten
  "lighten lightens a rgb color by a given factor.
   If no factor is provided, the color will be lightened
   with the factor of 0.2."
  ([colorstring] (lighten colorstring 0.2))
  ([colorstring factor]
   (color/rgbArrayToHex
    (color/lighten (color/hexToRgb colorstring) factor))))


(defn dark-bg?
  "dark-bg? returns true if the given hex color is 'dark'"
  [color]
  (let [[r g b] (color/hexToRgb color)]
    (> (- 1 (/ (+ (* 0.299 r)
                  (* 0.587 g)
                  (* 0.114 b))
               255))
       0.5)))

(defn lighten-color-in-db [color]
  (swap! db/app-db assoc color (lighten (color @db/app-db) 0.03)))

(defn darken-color-in-db [color]
  (swap! db/app-db assoc color (darken (color @db/app-db) 0.03)))

(defn inc-contrast []
  (if (dark-bg? (:mainbg @db/app-db))
    (if @db/adjustbg
      (do
        (doseq [i db/contrastcolors]
          (lighten-color-in-db i))
        (darken-color-in-db :mainbg))
      (doseq [i db/contrastcolors]
        (lighten-color-in-db i)))
    (if @db/adjustbg
      (do
        (doseq [i db/contrastcolors]
          (darken-color-in-db i))
        (lighten-color-in-db :mainbg))
      (doseq [i db/contrastcolors]
        (darken-color-in-db i)))))

(defn red-contrast []
  (if (dark-bg? (:mainbg @db/app-db))
    (if @db/adjustbg
      (do
        (doseq [i db/contrastcolors]
          (darken-color-in-db i))
        (lighten-color-in-db :mainbg))
      (doseq [i db/contrastcolors]
        (darken-color-in-db i)))
    (if @db/adjustbg
      (do
        (doseq [i db/contrastcolors]
          (lighten-color-in-db i))
        (darken-color-in-db :mainbg))
      (doseq [i db/contrastcolors]
        (lighten-color-in-db i)))))

(defn random-hue
  "return a random int between 0 and 360 "
  []
  (rand-int 360))

(defn hue-range
  "hue-range returns a vecor of equidistant huevalues."
  [length startvalue]
  (let [distance (Math/floor (/ 360 length))]
    (take length (iterate #(+ % (mod distance 360)) startvalue))))

(defn color-list
  "color-list returns a vector of 7 hex-colors with the same 
   saturation and lightness values and equidistant hue values."
  [saturation lightness]
  (let [hr (hue-range (count db/randomcolors) (random-hue))]
    (mapv #(color/hslToHex % saturation lightness) hr)))

(defn soft-palette
  "soft-palette returns a vector of 7 random soft colors."
  []
  (if (dark-bg? (:mainbg @db/app-db))
    (color-list 0.37 0.71)
    (color-list 0.37 0.44)))

(defn warm-palette
  "warm-palette returns a vector of 7 random warm colors." 
  []
  (if (dark-bg? (:mainbg @db/app-db))
    (color-list 0.40 0.57)
    (color-list 0.40 0.38)))

(defn pop-palette
  "pop-palette returns a vector of 7 random 'pop' colors."
  []
  (if (dark-bg? (:mainbg @db/app-db))
    (color-list 0.82 0.41)
    (color-list 0.82 0.39)))

(defn muted-palette
  "muted-palette returns a vector of 7 random muted colors."
  []
  (if (dark-bg? (:mainbg @db/app-db))
    (color-list 0.22 0.60)
    (color-list 0.22 0.4)))

(defn set-random-palette
  "set :keyword :builtin :string :functionname :variable
   :type and constant colors in app-db to colors of a
   random color palette"
  [palette]
  (doseq [[colorname colorstring] (map list db/randomcolors palette)]
    (swap! db/app-db assoc colorname colorstring)))


(defn derive-colors-from-theme
  "return a theme map with additional lighter/darker 
   variants of the background and foreground colors."
  [theme]
  (if (dark-bg? (:mainbg theme))
    (assoc theme
           :fg2 (darken (:mainfg theme)0.08)
           :fg3 (darken (:mainfg theme) 0.16)
           :fg4 (darken (:mainfg theme) 0.24)
           :bg2 (lighten (:mainbg theme) 0.08)
           :bg3 (lighten (:mainbg theme) 0.16)
           :bg4 (lighten (:mainbg theme) 0.24)
           :vsui "vs-dark")
    (assoc theme
           :fg2 (lighten (:mainfg theme) 0.08)
           :fg3 (lighten (:mainfg theme) 0.16)
           :fg4 (lighten (:mainfg theme) 0.24)
           :bg2 (darken (:mainbg theme) 0.08)
           :bg3 (darken (:mainbg theme) 0.16)
           :bg4 (darken (:mainbg theme) 0.24)
           :vsui "vs")))
