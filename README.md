# Spectrmap
Map for displaying graphical information about status and etc.
Example: map of your house (with real time information, open doors, windows), game map, galaxy map, strategic map.

[Site page](http://frogling.com/spectrmap.php)

![alt tag](http://i.imgur.com/wwpirSC.png)
Spectrmap can get updates from web site.

### Commands
Enter in console `help` and see all commands

### How to use?
- Create cool map in editor mode
- Create web page with update info
- Connect your devices, sensors or another stuff to your web service
- Connect SpectrMap to your web service. Use `CONNECT-PASSIVE==http://site...`
- Set auto view mode. Use `AUTO-MODE=view/editor`
- Enjoy real time map
If you want change update time, use `PASSIVE-UPDATE-TICK==1000` (ms)

### Examples

Map file:
```
SPECTRMAP-FILE
MAP-NAME==House system map
CONNECT-PASSIVE==http://frogling.com/spectrmaps/simplemap.php
1==0==260 220 400 220 3 #ffffff
2==0==400 240 400 220 3 #ffffff
3==0==440 240 440 220 3 #ffffff
4==0==600 220 440 220 3 #ffffff
5==0==600 220 600 340 3 #ffffff
```
(all file - [see](https://gist.githubusercontent.com/levohup/7ab22c19449d1c62a3dd3d95c0a4512c/raw/78b39f4db0dfb8fbbe621bc527f0533d8734424a/house.txt))

On site passive update file:
```
<?php
print "SPECTRMAP-UPDATE\n";
print "45==3==380 190 d0 #00d824\n";
print "46==3==540 390 d1 #00d824\n";
print "40==0==400 230 440 230 2 #00d824\n";
print "43==0==540 430 500 430 2 #00d824\n";
 ?>
```
