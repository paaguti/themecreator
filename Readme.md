ThemeCreator
============
Forked from [ThemeCreator](https://mswift42.github.io/themecreator/)

![Screenshot](https://github.com/mswift42/themecreator/raw/master/screenshots/tc1chromehamburg.png)

Create Color Themes for IntelliJ Editors, Atom, Emacs, TextAdept, Textmate, Vim and Gnome Terminal.


Installation Instructions:
--------------------------

No installation necessary. [ThemeCreator](https://mswift42.github.io/themecreator/) is hosted on gh-pages. ThemeCreator supports Firefox, Chrome, Edge and Opera, as these are so far the only ones to support [input-color](https://caniuse.com/#feat=input-color).

Usage:
------

Add themename and themeauthor, pick or enter the desired colors, then download the theme for the editor of your choice.

### JetBrains Editors:

Download Intellij theme and go to `File > Settings > Editor > Color Scheme` click on the Gear Icon and Import the downloaded icls file.

To create an UI Theme plugin for jetbrains editors >= 191, go to [iui](https://github.com/mswift42/iui) to generate one
with your newly generated theme.

### Textmate:
Download the textmate theme, then follow your editors instructions. For Visual Studio Code you can use the [yeoman code](https://code.visualstudio.com/docs/tools/yocode) generator.

### Atom:
[Generate](https://atom.io/docs/latest/hacking-atom-creating-a-theme) a new color theme from within atom. Then Download the atom theme you made with ThemeCreator, and download [base.less](https://github.com/mswift42/themecreator/raw/master/base.less) and [syntax-variables.less](https://github.com/mswift42/themecreator/raw/master/syntax-variables.less). Now copy the 3 files, `colors.less`, `base.less` and `syntax-variables.less` into the `styles/` folder of the Atom generated theme.

### Emacs:
Download the emacs file. Add a fitting package description to the first line. You can try your new theme by visiting your new theme with `C-x C-f <filename.el>`. You can install your new theme with: `M-x package-install-file <filename.el>`.

*Note*: Use the `my-themes` branch for streamlined themes. This is an attempt to see how well face definitions integrate into themes.

### TextAdept:
Download the TextAdept file. Copy it into your ~/.textadept/themes directory, edit your preferences to use it and restart TextAdept.

### Vim:
Download the vim theme and copy it to your `colors` folder. For Vim that's usually `~/.vim/colors`, for Neovim it's `~/.config/nvim/colors` .

Make sure `termguicolors` is enabled by adding

`set termguicolors`

to your `.vimrc` / `init.vim`.

### Gnome Terminal:
Download the gnome terminal file. Make the script executable with `chmod +x <file>`. Run the script: </folder/to/script/themefile.sh> .


Random Colors:
--------------

ThemeCreator supports random colors from 4 different color palettes, or you can generate random colors from a custom color palette by providing [CIE Lch](http://www.colourphil.co.uk/lab_lch_colour_space.shtml) Lightness and Saturation values for your theme colors. Hue values will be assigned randomly. Lightness values range from 0 (black) to 100 (white), Saturation from 0 (grey) to 100 (full saturation).

The used values for the warm, soft, pop and muted palettes can be found in [colors.cljs](https://github.com/mswift42/themecreator/blob/master/app/src/cljs/app/colors.cljs#L210-231). For example the soft palette uses 57.292 and 25.738 Lightness and saturation values for a dark background, and 44.921 and 25.738 for a light background.

Storage:
--------

By pressing the `store theme` button, you can store your theme and custom Lightness and Saturation values to local Storage.


Contributing:
-------------

**All contributions are welcome**.

To run themecreator locally:

clone or download this repo.

[install](https://clojure.org/guides/getting_started) clojure und the clojure cli tools.

go into the `app.core` directory and run `clj -M:fig:build`.

Extras:
-------

`Dockerfile` and the companion scripts `build-run.sh` and `kill-clean.sh` allow you
to deploy ThemeCreator in a Docker container. You will need the Docker CLI tools to
use them.

`build-sh.run` will expose the container port `9500` to the host/VM where you are running
ThemeCreator. If you are running ThemeCreator inside Docker inside a VM, you may also
need to add a port translation for your virtual machine to access ThemeCreator from you host.

This has been tested on a Ubuntu 20.04 or 22.04 server VM running on VirtualBox and on KVM directly.


Screenshots:
------------

Custom Palette (Chrome):
![Screenshot ](https://github.com/mswift42/themecreator/raw/master/screenshots/tc1customchrome.png)

Light Theme (Firefox):
![Screenshot ](https://github.com/mswift42/themecreator/raw/master/screenshots/tc1white-sandfirefox.png)

Download Theme (Firefox):
![Screenshot ](https://github.com/mswift42/themecreator/raw/master/screenshots/tc1firefoxdownload.png)

Editor Comparisons:
-------------------

You can find screenshots of the same theme with different Editors in the [Wiki](https://github.com/mswift42/themecreator/wiki).


Implementation:
---------------

ThemeCreator is a Clojurescript app, in particular it uses the wonderful [reagent](http://reagent-project.github.io/) framework. The themetemplates are compiled and rendered using [mustache](https://github.com/janl/mustache.js/), [goog.net.XhrIo](https://developers.google.com/closure/library/docs/xhrio?hl=en) is used to load the templates, and [goog.color](https://google.github.io/closure-library/api/namespace_goog_color.html) was helpful for some color conversions.


Thanks:
-------

ThemeCreator's maintenance has been made a lot easier with the support of

<a href="https://www.jetbrains.com"> <img src="https://github.com/mswift42/themecreator/raw/master/jetbrains.png" width="200" height = "200" alt="JetBrains" /> </a>

Thank you JetBrains.
