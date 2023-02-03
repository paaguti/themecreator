(ns app.db
  (:require [reagent.core :as r]))

(def black
  {:themename    "black"
   :mainbg       "#000000"
   :mainfg       "#d4d4d4"
   :builtin      "#d4d4d4"
   :keyword      "#d4d4d4"
   :string       "#d4d4d4"
   :functionname "#d4d4d4"
   :variable     "#d4d4d4"
   :type         "#d4d4d4"
   :constant     "#d4d4d4"
   :comment      "#808080"
   :selection    "#0000ff"
   :warning      "#ff0000"
   :warning2     "#ff8800"})


(def white
  {:themename    "white"
   :mainbg       "#ffffff"
   :mainfg       "#303030"
   :builtin      "#000000"
   :keyword      "#000000"
   :string       "#000000"
   :functionname "#000000"
   :variable     "#000000"
   :type         "#000000"
   :constant     "#000000"
   :comment      "#606060"
   :selection    "#ffff00"
   :warning      "#ff0000"
   :warning2     "#ff8800"})

(def white-sand
  {:themename    "white-sand"
   :mainbg       "#f5ebe1"                ;
   :mainfg       "#585858"
   :keyword      "#4a858c"
   :builtin      "#1a8591"
   :string       "#b3534b"
   :functionname "#bd745e"
   :variable     "#476238"
   :type         "#8c4a79"
   :constant     "#697024"
   :comment      "#a9a9a9"
   :selection    "#d6d883"
   :warning      "#ff1276"
   :warning2     "#ff4d12"})

(def warm-night
  {:themename    "warm-night"
   :mainbg       "#292424"
   :mainfg       "#b1b1b1"
   :keyword      "#96905f"
   :builtin      "#71a46c"
   :string       "#71a19f"
   :functionname "#b680b1"
   :variable     "#c27d7b"
   :type         "#8b8fc6"
   :constant     "#bd845f"
   :comment      "#5d5a58"
   :selection    "#d6d883"
   :warning      "#e81050"
   :warning2     "#e86310"})

(def oldlace
  {:themename    "Oldlace"
   :mainfg       "#525252"
   :mainbg       "#fdf5e6"
   :keyword      "#3f567b"
   :builtin      "#7b4135"
   :string       "#305f5e"
   :functionname "#714355"
   :variable     "#3f5b32"
   :type         "#634575"
   :constant     "#64502f"
   :comment      "#949494"
   :selection    "#d6d883"
   :warning      "#fa0c0c"
   :warning2     "#fa7b0c"})

(def greymatters
  {:themename    "Greymatters"
   :mainfg       "#2f2f2f"
   :mainbg       "#f9fbfd"
   :keyword      "#3f567b"
   :builtin      "#7b4135"
   :string       "#305f5e"
   :functionname "#714355"
   :variable     "#3f5b32"
   :type         "#634575"
   :constant     "#64502f"
   :comment      "#949494"
   :selection    "#d6d883"
   :warning      "#fa0c0c"
   :warning2     "#fa7b0c"})

(def soft-charcoal
  {:themename    "soft-charcoal"
   :mainfg       "#c2c2c2"
   :mainbg       "#191919"
   :keyword      "#8aa234"
   :builtin      "#54686d"
   :string       "#5d90cd"
   :functionname "#7a8bbd"
   :variable     "#8885b2"
   :type         "#8aa6c1"
   :constant     "#8aa6c1"
   :comment      "#808080"
   :selection    "#ffd7c2"
   :warning      "#ff6523"
   :warning2     "#ff2370"})

(def munich
  {:themename    "munich"
   :mainfg       "#b1b1b1"
   :mainbg       "#281123"
   :keyword      "#b39b5f"
   :builtin      "#7aa975"
   :string       "#36aea8"
   :functionname "#40a8d3"
   :variable     "#9798d6"
   :type         "#cf88af"
   :constant     "#d7897c"
   :comment      "#5d5d5d"
   :selection    "#6a245b"
   :warning      "#e81050"
   :warning2     "#e86310"})

(def reykjavik
  {:themename    "reykjavik"
   :mainfg       "#b1b1b1"
   :mainbg       "#112328"
   :keyword      "#a3d4e8"
   :builtin      "#c4cbee"
   :string       "#e6c2db"
   :functionname "#f1c1bd"
   :variable     "#e1c9aa"
   :type         "#c1d2b1"
   :constant     "#a3d6cc"
   :comment      "#5d5d5d"
   :selection    "#3e8993"
   :warning      "#e81050"
   :warning2     "#e86310"})

(def bergen
  {:themename    "bergen"
   :mainfg       "#585858"
   :mainbg       "#eae7e9"
   :keyword      "#5c71b3"
   :builtin      "#a35c8e"
   :string       "#b15a56"
   :functionname "#926e31"
   :variable     "#6d3e7e"
   :type         "#00847a"
   :constant     "#0081a6"
   :comment      "#a9a9a9"
   :selection    "#c6ec8e"
   :warning      "#ff1276"
   :warning2     "#ff4d12"})

(def madrid
  {:themename    "madrid"
   :mainfg       "#b1b1b1"
   :mainbg       "#1b1b1b"
   :keyword      "#b7797d"
   :builtin      "#a78360"
   :string       "#818f62"
   :functionname "#96546c"
   :variable     "#4394a7"
   :type         "#718bb6"
   :constant     "#a27ea4"
   :comment      "#5d5d5d"
   :selection    "#334233"
   :warning      "#e81050"
   :warning2     "#e86310"})

(def soft-morning
  {:themename    "soft-morning"
   :mainfg       "#282828"
   :mainbg       "#f2f1f0"
   :keyword      "#8aa234"
   :builtin      "#727170"
   :string       "#3450a2"
   :functionname "#a82e4d"
   :variable     "#f03f3f"
   :type         "#727170"
   :constant     "#f03f3f"
   :comment      "#808080"
   :selection    "#efd6be"
   :warning      "#ff6523"
   :warning2     "#e80f29"})

(def magonyx
  {:themename    "magonyx"
   :mainfg       "#c7c7c7"
   :mainbg       "#353839"
   :keyword      "#9ec065"
   :builtin      "#cbb021"
   :string       "#5ba2b1"
   :functionname "#75b7ff"
   :variable     "#e69ae8"
   :type         "#ff909d"
   :constant     "#eca661"
   :comment      "#5d5d5d"
   :selection    "#3e1504"
   :warning      "#e81050"
   :warning2     "#e86310"})

(def light-kiss
  {:themename    "light-kiss"
   :mainfg       "#303030"
   :mainbg       "#efe6e6"
   :keyword      "#c87758"
   :builtin      "#2a55a8"
   :string       "#2a55a8"
   :functionname "#2a55a8"
   :variable     "#337d92"
   :type         "#337d92"
   :constant     "#337d92"
   :comment      "#909090"
   :selection    "#e0edde"
   :warning      "#ff0000"
   :warning2     "#ff8800"})

(def foggy-night
  {:themename    "foggy-night"
   :mainfg       "#8f8f8f"
   :mainbg       "#292929"
   :keyword      "#6b83ac"
   :builtin      "#997599"
   :string       "#ad7176"
   :functionname "#9e7a5a"
   :variable     "#7b875b"
   :type         "#8d5351"
   :constant     "#3e8c9d"
   :comment      "#626262"
   :selection    "#0b0c37"
   :warning      "#e81050"
   :warning2     "#e86310"})

(def silkworm
  {:themename    "silkworm"
   :mainfg       "#585858"
   :mainbg       "#ece3db"
   :keyword      "#367a7f"
   :builtin      "#0073b5"
   :string       "#3b4bab"
   :functionname "#ad4271"
   :variable     "#a74f31"
   :type         "#76690b"
   :constant     "#a74f31"
   :comment      "#a9a9a9"
   :selection    "#f9f7c8"
   :warning      "#ff1276"
   :warning2     "#ff4d12"})

(def metalheart
  {:themename    "metalheart"
   :mainfg       "#8693ae"
   :mainbg       "#1d272a"
   :keyword      "#6e94ad"
   :builtin      "#9a91b8"
   :string       "#ba899c"
   :functionname "#937aba"
   :variable     "#638f81"
   :type         "#7a9f81"
   :constant     "#5980a3"
   :comment      "#727272"
   :selection    "#163a45"
   :warning      "#ff0000"
   :warning2     "#ff8800"})

(def breezy-fall
  {:themename    "breezy-fall"
   :mainfg       "#78693c"
   :mainbg       "#f6f3cf"
   :keyword      "#0073a3"
   :builtin      "#3b61b1"
   :string       "#994485"
   :functionname "#a94142"
   :variable     "#922f8c"
   :type         "#454b7a"
   :constant     "#844c41"
   :comment      "#906e86"
   :selection    "#dbd7b3"
   :warning      "#ff1276"
   :warning2     "#ff4d12"})

(def thursday
  {:themename    "thursday"
   :mainfg       "#2f2f2f"
   :mainbg       "#f5f7f9"
   :keyword      "#28728f"
   :builtin      "#636792"
   :string       "#8c5c79"
   :functionname "#935c54"
   :variable     "#7d6740"
   :type         "#56724b"
   :constant     "#28766e"
   :comment      "#949494"
   :selection    "#d8e8f8"
   :warning      "#fa0c0c"
   :warning2     "#fa7b0c"})

(def norte
  {:themename    "norte"
   :mainfg       "#eceff4"
   :mainbg       "#2E3440"
   :keyword      "#88c0d0"
   :builtin      "#81a1c1"
   :string       "#a3be8c"
   :functionname "#ebcb8b"
   :variable     "#e5e9f0"
   :type         "#8fbcbb"
   :constant     "#d4d4d4"
   :comment      "#d8dee9"
   :selection    "#30555a"
   :warning      "#bf616a"
   :warning2     "#d08770"})

(def zeno
  {:themename    "zeno"
   :mainbg       "#282A36"
   :mainfg       "#E8F0FF"
   :builtin      "#BB98FC"
   :keyword      "#66D9EF"
   :string       "#C1CAFF"
   :functionname "#84B5FF"
   :variable     "#5FCA81"
   :type         "#66D9EF"
   :constant     "#A6E22E"
   :comment      "#6F7181"
   :selection    "#000000"
   :warning      "#FF84C9"
   :warning2     "#FF5996"})

(def arjen
  {:themename   "arjengrey"
   :mainfg       "#bdc3ce"
   :mainbg       "#2a2f38"
   :builtin      "#eacc8c"
   :keyword      "#b894b0"
   :string       "#a8c194"
   :functionname "#909fab"
   :variable     "#8294ac"
   :type         "#a0a5a0"
   :constant     "#8b9db0"
   :comment      "#63747c"
   :selection    "#3c4449"
   :warning      "#ff0000"
   :warning2     "#f08040"})


(def contrastcolors
  [:mainfg :keyword :builtin :string :functionname :variable :type
   :constant :comment])

(def randomcolors
  [:keyword :builtin :string :functionname :variable :type :constant])


(def app-db
  (r/atom
   black))

(def adjustbg
  (r/atom
   false))

(def custom-palette-db
  (r/atom
   {:saturation 0 :lightness 0}))

(defn switch-theme
  [theme]
  (reset! app-db theme ))

(defn toggle-adjust []
  (reset! adjustbg (not @adjustbg)))

(defn set-custom-palette
  [lightness saturation]
  (reset! custom-palette-db {:saturation saturation
                             :lightness lightness}))


(def storagename "themecreator")

(defn save-to-storage
  []
  (.setItem js/localStorage storagename
            (js/JSON.stringify
             (clj->js (assoc @app-db :lightness (:lightness @custom-palette-db)
                             :saturation (:saturation @custom-palette-db))))))

(defn string-to-keyword
  "convert a map with strings as keys to a map with keyword keys and string
   values."
  [strmap]
  (into {} (for [[k v] strmap]
             [(keyword k) v])))

(defn load-from-storage
  []
  (let [th (.getItem js/localStorage storagename)]
    (if th
      (-> (.getItem js/localStorage storagename)
          (js/JSON.parse)
          (js->clj)))))

(defn set-db-from-storage
  []
  (if (.getItem js/localStorage storagename)
    (let [storedtheme (string-to-keyword (load-from-storage))]
      (do
        (switch-theme storedtheme)
        (reset! custom-palette-db {:saturation (:saturation storedtheme)
                                   :lightness (:lightness storedtheme)})))))
