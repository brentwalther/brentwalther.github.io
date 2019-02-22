menu query,nicklist,channel,status { 
  .ChimpanzeeAmp: /mp3 -o 
} 
menu @mp3,@playlist {
  Themes
  .Background
  ..White: mc 1 0
  ..Black: mc 1 1 
  ..Navy: mc 1 2 
  ..Green: mc 1 3 
  ..Red: mc 1 4 
  ..Maroon: mc 1 5 
  ..Purple: mc 1 6 
  ..Orange: mc 1 7 
  ..Yellow: mc 1 8 
  ..Light Green: mc 1 9 
  ..Teal: mc 1 10 
  ..Cyan: mc 1 11 
  ..Blue: mc 1 12 
  ..Magenta: mc 1 13 
  ..Dark Grey: mc 1 14 
  ..Light Grey: mc 1 15 
  .Foreground
  ..White: mc 2 0 
  ..Black: mc 2 1 
  ..Navy: mc 2 2 
  ..Green: mc 2 3 
  ..Red: mc 2 4 
  ..Maroon: mc 2 5 
  ..Purple: mc 2 6 
  ..Orange: mc 2 7 
  ..Yellow: mc 2 8 
  ..Light Green: mc 2 9 
  ..Teal: mc 2 10 
  ..Cyan: mc 2 11 
  ..Blue: mc 2 12 
  ..Magenta: mc 2 13 
  ..Dark Grey: mc 2 14 
  ..Light Grey: mc 2 15 
  Play Method
  .$iif($songend = continuous,$style(1)) Continuous: se Continuous
  .$iif($songend = repeat,$style(1)) Repeat: se Repeat
  .$iif($songend = shuffle,$style(1)) Shuffle: se Shuffle
  Define Element
  .Revert to Default: define default
  .-
  .Time Display: define time
  .Now Playing Bar: define nowplay
  .Button Cluster: define buttons
  .Volume Bar: define vol
  .Seek Bar: define seek
  .Playlist: define play
  .-
  .Main Window: define mainwin
  .Mini Window: define miniwin
  Songs
  .Add
  ..File: mp3pla -f
  ..Directory: mp3pla -d
  .Clear Playlist: .remove mp3play.txt
  Configure Keys
  .Coming Soon!
  -
  $iif($hget(mp3,ontop) == true,$style(1)) Always on Top: top
  $iif($hget(mp3,filex) == true,$style(1)) Remove File Extensions: filex
}
alias -l se hadd mp3 songend $1
alias -l songend return $hget(mp3,songend)
alias -l mc hadd mp3 c $+ $1 $2 | refresh
alias -l refresh mp3 -o $1 $2
alias -l drf drawrect -nf @mp3 $1-
alias -l w window $1-
alias -l size return $iif($hget(mp3,size),$v1,reg)
alias -l song noop $read(mp3play.txt,w,$+(*,$nopath($insong.fname),*)) | mp3play -n $calc($readn [ $1 ] 1)
alias -l st drawtext -bc @mp3 $hget(mp3,c2) $hget(mp3,c1) $ceil($calc($hget(mp3,time.x)+(($hget(mp3,time.w)-20)/2))) $ceil($calc($hget(mp3,time.y)+(($hget(mp3,time.h)-14)/2))) 20 10 $asctime($hget(mp3,songtime),n:ss) | hinc mp3 songtime 
alias -l up drawdot @mp3
alias -l top hadd mp3 ontop $iif($hget(mp3,ontop) == true,false,$v2) | window $iif($hget(mp3,ontop) == true, -o @mp3,-u @mp3)
alias -l filex hadd mp3 filex $iif($hget(mp3,filex) == true,false,$v2)
alias mp3play { 
  .timerticker off
  if (!$ebeeps) ebeeps on
  var %a, %b $qt(mp3play.txt)
  if ($1) {  
    if ($1 = -n) && ($2) %a = $2    
    if ($read(%b,w,$+(*,$1-,*))) %a = $readn 
    .timermp4 0 1 st 
    splay -p $qt($read(%b,%a))
    .timermp3 -m 0 $ceil($calc($iif($sound($insong.fname).length,$v1,$insong.length)/($hget(mp3,seek.w)-12) )) trackbar 
    drf $hget(mp3,c1) 1 $calc($hget(mp3,nowplay.x)+1) $calc($hget(mp3,nowplay.y)+1) $calc($hget(mp3,nowplay.w)-2) $calc($hget(mp3,nowplay.h)-2)
    up
    if ($width($remove($nopath($insong.fname),.mp3,.wav,.wma),tahoma,10) < $hget(mp3,nowplay.w)) {
      drawtext -bc @mp3 [ $hget(mp3,c2) $hget(mp3,c1) $calc(($hget(mp3,nowplay.x)+(( $hget(mp3,nowplay.w)- $v1)/2))) $calc($hget(mp3,nowplay.y)+(( $hget(mp3,nowplay.h)-12)/2)) $v1 10 ] $remove($nopath($insong.fname), [ $iif($hget(mp3,filex) == true,$+(.mp3,$chr(44),.wav,$chr(44),.wma)) ] )
    }
    else {
      hadd mp3 tick 0
      hadd mp3 tickway hinc
      ticker $remove($nopath($read(%b,%a)), [ $iif($hget(mp3,filex) == true,$+(.mp3,$chr(44),.wav,$chr(44),.wma)) ] )
    } 
    hadd mp3 pos 1
    hadd mp3 songtime 0 
  } 
} 
alias mp3 {
  if (!$hget(mp3)) { 
    hmake mp3 70
    if ($isfile(mp3.conf)) {
      hload mp3 mp3.conf
    }
    else { 
      define default
    }
  }
  if ($hget(mp3,c1) == $null) { hadd mp3 c1 0 | hadd mp3 c2 2 }
  var %a drawtext -n @mp3, %b $qt(mp3play.txt)
  if ($1 = -o) {
    .timerticker off
    w -pBdk0 $+ $iif($hget(mp3,ontop) == true,o) +b @mp3 $iif($2,$v1,$hget(mp3,mainwin.x)) $iif($3,$v1,$hget(mp3,mainwin.y)) $hget(mp3,mainwin.w) $iif($size == reg, $hget(mp3,mainwin.h), $hget(mp3,miniwin.h)) Tahoma 10
    clear @mp3
    drawfill -n @mp3 $hget(mp3,c1) $hget(mp3,c1) 1 1
    drf $hget(mp3,c1) 1 $calc($hget(mp3,nowplay.x)+1) $calc($hget(mp3,nowplay.y)+1) $calc($hget(mp3,nowplay.w)-2) $calc($hget(mp3,nowplay.h)-2)
    if ($nopath($insong.fname)) { $iif($width($remove($nopath($insong.fname), [ $iif($hget(mp3,filex) == true,$+(.mp3,$chr(44),.wav,$chr(44),.wma)) ] ),tahoma,10) < $hget(mp3,nowplay.w),drawtext -bc @mp3 $hget(mp3,c2) $hget(mp3,c1) $calc(($hget(mp3,nowplay.x)+(( $hget(mp3,nowplay.w)- $v1)/2))) $calc($hget(mp3,nowplay.y)+(( $hget(mp3,nowplay.h)-12)/2)) $v1 10 $remove($nopath($insong.fname), [ $iif($hget(mp3,filex) == true,$+(.mp3,$chr(44),.wav,$chr(44),.wma)) ] ),ticker $remove($nopath($insong.fname),[ $iif($hget(mp3,filex) == true,$+(.mp3,$chr(44),.wav,$chr(44),.wma)) ] )) }
    drf $hget(mp3,c2) 1 0 0 $hget(mp3,mainwin.w) 11 $iif($size = reg,$calc(($hget(mp3,play.x) + $hget(mp3,play.w))-13) $calc($hget(mp3,play.y) +2) 11 10) $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-13) $calc(($hget(mp3,play.y) + $hget(mp3,play.h))-12) 11 10 $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-11) $ceil($calc((($hget(mp3,playpos) * ($hget(mp3,play.h) -24)) / $lines(mp3play.txt))+( $hget(mp3,play.y) +11))) 7 9
    %a $hget(mp3,c1) 10 -1 ChimpanzeeAmp Version 7.13
    drf $hget(mp3,c1) 1 $calc($hget(mp3,vol.x)+1) $calc($hget(mp3,vol.y)+1) $calc($hget(mp3,vol.w)-2) $calc($hget(mp3,vol.h)-2)
    drawline -n @mp3 $hget(mp3,c1) 1 $calc($hget(mp3,mainwin.w)-4) 2 $calc($hget(mp3,mainwin.w)-10) 8 $calc($hget(mp3,mainwin.w)-7) 5 $calc($hget(mp3,mainwin.w)-4) 8 $calc($hget(mp3,mainwin.w)-11) 1
    drawrect -n @mp3 $hget(mp3,c2) 1 $hget(mp3,nowplay.x) $hget(mp3,nowplay.y) $hget(mp3,nowplay.w) $hget(mp3,nowplay.h) $hget(mp3,time.x) $hget(mp3,time.y) $hget(mp3,time.w) $hget(mp3,time.h) $hget(mp3,vol.x) $hget(mp3,vol.y) $hget(mp3,vol.w) $hget(mp3,vol.h) $hget(mp3,seek.x) $hget(mp3,seek.y) $hget(mp3,seek.w) $hget(mp3,seek.h) 0 0 $calc($hget(mp3,mainwin.w)-2) $iif($size ==  mini,$calc($hget(mp3,miniwin.h)-2),$calc($hget(mp3,mainwin.h)-2) $hget(mp3,play.x) $hget(mp3,play.y) $hget(mp3,play.w) $hget(mp3,play.h)) $calc($hget(mp3,play.x)+($hget(mp3,play.w) -13)) $calc($hget(mp3,play.y)+12) 11 $calc($hget(mp3,play.h) -23) 
    drawrect -n @mp3 $hget(mp3,c1) 1 $calc($hget(mp3,mainwin.w)-22) 1 9 9 $calc($hget(mp3,mainwin.w)-22) 1 9 5
    drawline -n @mp3 $hget(mp3,c1) 1 $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-11) $calc($hget(mp3,play.y) +8) $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-8) $calc($hget(mp3,play.y) +5) $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-4) $calc($hget(mp3,play.y) +9)
    drawline -n @mp3 $hget(mp3,c1) 1 $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-11) $calc(($hget(mp3,play.y) + $hget(mp3,play.h))-9) $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-8) $calc(($hget(mp3,play.y) + $hget(mp3,play.h))-6) $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-4) $calc(($hget(mp3,play.y) + $hget(mp3,play.h))-10)
    %a $hget(mp3,c2) Webdings 14 $hget(mp3,buttons.x) $hget(mp3,buttons.y) 7 < ; 4 8
    drf $hget(mp3,c2) 1 $iif($hget(mp3,pos),$calc(11 + $hget(mp3,pos)),12) $calc($hget(mp3,seek.y)+2) 10 $calc($hget(mp3,seek.h)-4) $floor($calc((($vol(master)/65535)*( $hget(mp3,vol.w)-7))+( $hget(mp3,vol.x)+1))) $calc($hget(mp3,vol.y)+2) 5 $calc($hget(mp3,vol.h)-4)
    drawsongs s $hget(mp3,playpos) $calc($hget(mp3,playpos) +$floor($calc($hget(mp3,play.h) /10)))
    up
  }
}
menu * {
  sclick: {
    if ($active == @mp3) {
      if ($mouse.key & 1) {
        if ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,mainwin.w)-11),1,8,8)) {
          w -c @mp3
          .timermp* off
          splay stop
          unset %paused
          hsave mp3 mp3.conf
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,buttons.x)+62),$calc($hget(mp3,buttons.y)+3),15,15)) {
          if (%paused == 5) { 
            !splay -p resume 
            unset %paused 
            .timermp3 -r 
            .timermp4 -r 
          } 
          elseif ($click(@mp3,$calc($click(@mp3,0) -1)).y) {
            mp3play $read(mp3play.txt,$ceil($calc((($v1 -83)/10)+( $hget(mp3,playpos) -1))))
          }
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,buttons.x)+21),$calc($hget(mp3,buttons.y)+3),15,15)) {
          if ($insong) { 
            !splay stop 
            .timerm* off 
            .timerticker off
            unset %paused
            drf $hget(mp3,c1) 1 $calc($hget(mp3,seek.x)+1) $calc($hget(mp3,seek.y)+1) $calc($hget(mp3,seek.w)-2) $calc($hget(mp3,seek.h)-2) $calc($hget(mp3,nowplay.x)+1) $calc($hget(mp3,nowplay.y)+1) $calc($hget(mp3,nowplay.w)-2) $calc($hget(mp3,nowplay.h)-2) $calc($hget(mp3,time.x)+1) $calc($hget(mp3,time.y)+1) $calc($hget(mp3,time.w)-2) $calc($hget(mp3,time.h)-2)
            drf $hget(mp3,c2) 1 $calc($hget(mp3,seek.x)+2) $calc($hget(mp3,seek.y)+2) 10 $calc($hget(mp3,seek.h)-4)
            up
          } 
        }
        elseif ($inrect($mouse.x,$mouse.y,$hget(mp3,buttons.x),$calc($hget(mp3,buttons.y)+3),15,15)) {
          if ($songend == continuous) {       
            song -
          }
          elseif ($songend == shuffle) {
            mp3play $read(mp3play.txt)
          }
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,buttons.x)+83),$calc($hget(mp3,buttons.y)+3),15,15)) { 
          if ($songend == continuous) {       
            song +
            echo -s next!
          }
          elseif ($songend == shuffle) {
            mp3play $read(mp3play.txt)
          }
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,buttons.x)+42),$calc($hget(mp3,buttons.y)+3),15,15)) {
          mp3pause
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,seek.x)+2),$hget(mp3,seek.y),$calc($hget(mp3,seek.w)-2),$hget(mp3,seek.h))) {
          if ($mouse.x < $calc(($hget(mp3,seek.x)+ $hget(mp3,seek.w))-2)) {
            if ($insong) {
              %seek = on
              .timermp3 -p 
              .timermp4 -p 
              seek
            }
          }
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,vol.x)+2),$calc($hget(mp3,vol.y)+2),$calc($hget(mp3,vol.w)-4),$hget(mp3,vol.h))) {
          vc $mouse.x
          %volmov = on
        }
        elseif ($inrect($mouse.x,$mouse.y,192,367,44,20)) {
          set %rem $input(Would you like to remove the file from your computer as well?,nv)
          if (%rem != $cancel) {
            if ($sline(@Playlist,1).ln) { 
              var %l $v1
              if (%rem == $yes) { .remove $qt($read(mp3play.txt,%l )) }
              write -dl [ $+ [ %l ] ] mp3play.txt
              drawsongs s $hget(mp3,playpos) $calc($hget(mp3,playpos) +27)
            }
          }
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc($hget(mp3,mainwin.w)-22),1,9,9)) {
          hadd mp3 size $iif($size == mini,reg,mini)
          refresh $window(@mp3).dx $window(@mp3).dy
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc(($hget(mp3,play.x) + $hget(mp3,play.w))-13),$calc($hget(mp3,play.y) +2),11,10)  && $hget(mp3,playpos) != 1) {
          hdec mp3 playpos
          drawsongs s $hget(mp3,playpos) $calc($hget(mp3,playpos) +$floor($calc($hget(mp3,play.h) /10)))
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc(($hget(mp3,play.x) + $hget(mp3,play.w))-13),$calc(($hget(mp3,play.y) + $hget(mp3,play.h))-12),11,10) && $hget(mp3,playpos) != $calc($lines(mp3play.txt)-20)) {
          hinc mp3 playpos
          drawsongs s $hget(mp3,playpos) $calc($hget(mp3,playpos) + $floor($calc($hget(mp3,play.h) /10)))
        }
        elseif ($inrect($mouse.x,$mouse.y,$calc(($hget(mp3,play.x) + $hget(mp3,play.w))-13),$calc($hget(mp3,play.y) +12),11,$calc($hget(mp3,play.h) -34))) {
          %scroll = on
          scroll
        }
        elseif ($inrect($mouse.x,$mouse.y,9,83,220,277)) {
          if ($read(mp3play.txt,$ceil($calc((($mouse.y -83)/10)+($hget(mp3,playpos) -1))))) {

          }
        }
        else { drag $mouse.x $mouse.y } 
      }
    }
  }
  mouse: {
    if ($inrect($mouse.x,$mouse.y,$calc(2 + $hget(mp3,vol.x)),$hget(mp3,vol.y),$calc($hget(mp3,vol.w)-2),$hget(mp3,vol.h)) && %volmov == on) {
      vc $mouse.x 
    }
  }
  uclick: {
    if (%volmov == on)  unset %volmov
    if (%scroll == on)  unset %scroll 
    if (%seek == on) {
      unset %seek
      .timermp3 -r 
      .timermp4 -r
    } 
  }
  leave: {
    if (%volmov == on)  unset %volmov
    if (%scroll == on)  unset %scroll
  }
  dclick: {
    if ($active == @mp3) {
      if ($inrect($mouse.x,$mouse.y,$hget(mp3,play.x),$hget(mp3,play.y),$hget(mp3,play.w),$hget(mp3,play.h))) {
        mp3play $read(mp3play.txt,$ceil($calc((($mouse.y - $hget(mp3,play.y))/10)+($hget(mp3,playpos) -1))))
      }
    }
  }
}
alias trackbar {
  if ($hget(mp3,pos) = $calc(($hget(mp3,seek.x)+ $hget(mp3,seek.w))-22)) { 
    .timermtrack off 
    halt
  }
  drf $hget(mp3,c1) 1 $calc($hget(mp3,seek.x)+1) $calc($hget(mp3,seek.y)+1) $calc($hget(mp3,seek.w)-2) $calc($hget(mp3,seek.h)-2)
  drf $hget(mp3,c2) 1 $calc(($hget(mp3,seek.x)+1) + $hget(mp3,pos)) $calc($hget(mp3,seek.y)+2) 10 $calc($hget(mp3,seek.h)-4)
  up
  hinc mp3 pos
}
on *:CLOSE:@mp3: {
  .timermp* off
  splay stop
  unset %paused
  hsave mp3 mp3.conf
}
alias mp3pause { 
  var %a !splay -p 
  if ($insong) { 
    if (%paused = 5) && ($insong.pause) { 
      %a resume 
      !unset %paused 
      .timermp3 -r 
      .timermp4 -r 
    }  
    else { 
      %a pause 
      !set %paused 5 
      .timermp3 -p 
      .timermp4 -p 
    } 
  } 
} 
alias mp3pla {  
  var %a 1, %b $qt(mp3play.txt), %c write %b, %d $qt($iif($1 = -d,$sdir(C:\),$sfile($mircdir))), %m 0, %l $lines(%b)
  if ($1 = -d) { 
    while ($findfile(%d,*.mp3;*.wma;*.wav,%a)) { 
      !var %f $v1 
      if (!$read(%b,w,$+(*,%f,*))) { 
        %c %f
        inc %m
      } 
      inc %a
    } 
    !echo -ac info You have added %m files to the playlist. 
    !echo -ac info Your files will now being to be indexed.
    index $calc(%b + 1)
  } 
  elseif (!$read(%b,w,$+(*,$remove(%d,"),*))) { 
    %c $remove(%d,") 
    !echo -ac info File $qt($nopath(%d)) was added to the playlist and will now be indexed. 
    index $calc(%b + 1)
  } 
  drawsongs s $hget(mp3,playpos) $calc($hget(mp3,playpos) +27)
} 
alias -l index {
  if ($read(mp3play.txt,$1) && !$readini(mp3play.ini,$read(mp3play.txt,$1),artist)) {

  }
}
on *:KEYDOWN:@mp3:*:{ 
  if ($keyval == 37) { 
    song -
  } 
  if ($keyval == 38) {
    vc $calc($floor($calc((($vol(master)/65535)*43)+190))+6)
  }
  if ($keyval == 39) { 
    song +
  } 
  if ($keyval == 40) {
    vc $calc($floor($calc((($vol(master)/65535)*43)+190))-1)
  }
}
on *:mp3end:{ 
  .timerticker off
  var %a mp3play, %b $qt(mp3play.txt)
  drf $hget(mp3,c1) 1 $calc($hget(mp3,seek.x)+1) $calc($hget(mp3,seek.y)+1) $calc($hget(mp3,seek.w)-2) $calc($hget(mp3,seek.h)-2) $calc($hget(mp3,nowplay.x)+1) $calc($hget(mp3,nowplay.y)+1) $calc($hget(mp3,nowplay.w)-2) $calc($hget(mp3,nowplay.h)-2) $calc($hget(mp3,time.x)+1) $calc($hget(mp3,time.y)+1) $calc($hget(mp3,time.w)-2) $calc($hget(mp3,time.h)-2)
  drf $hget(mp3,c2) 1 $calc($hget(mp3,seek.x)+2) $calc($hget(mp3,seek.y)+2) 10 $calc($hget(mp3,seek.h)-4)
  .timermp4 off
  if ($songend) { 
    noop $read(%b,w,$+(*,$nopath($filename),*)) 
    if ($v1 = continuous) && ($readn != $lines(%b)) %a -n $calc($readn + 1)
    elseif ($v1 = repeat) %a $readn 
    elseif ($v1 = shuffle) {
      %a $read(%b)
    } 
    if ($lines(%b) > 15) {
      if ($numtok(%e,63) < 15) {
        !writeini -n mp3play.ini settings recent $addtok(%e,$nopath($filename),63)
      }
      else { 
        !writeini -n mp3play.ini settings recent $deltok($addtok(%e,$nopath($filename),63),1,63)
      }
    }
  } 
}
alias -l drag { 
  if ($window(@mp3) && ($mouse.key & 1) && !%scroll && !%seek) { 
    hadd mp3 mainwin.x $calc($mouse.dx - $1)
    hadd mp3 mainwin.y $calc($mouse.dy - $2)
    w @mp3 $calc($mouse.dx - $1) $calc($mouse.dy - $2)
    .timer -m 1 0 drag $1 $2
  } 
}
alias -l scroll { 
  if ($window(@mp3) && $mouse.key & 1 && %scroll == on) { 
    if ($mouse.y isnum $calc($hget(mp3,play.y) +13) $+ - $+ $calc(($hget(mp3,play.y) + $hget(mp3,play.h))-24)) {
      drf $hget(mp3,c1) 1 $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-12) $calc($hget(mp3,play.y) +12) 9 $calc($hget(mp3,play.h) -25) 
      drf $hget(mp3,c2) 1 $calc(($hget(mp3,play.x) + $hget(mp3,play.w))-11) $calc($v1 +1) 7 9 
      up 
      hadd mp3 playpos $ceil($calc((($v1 - $calc($hget(mp3,play.y) +11)) * $lines(mp3play.txt))/($hget(mp3,play.h) -24))) 
      drawsongs s $hget(mp3,playpos) $calc($hget(mp3,playpos) +$floor($calc($hget(mp3,play.h) /10))) 
    }
    .timer -m 1 0 scroll 
  } 
}
alias -l ticker {
  hadd mp3 extra $calc($width($1-,tahoma,10) - $calc($hget(mp3,nowplay.w)-3))
  drawtext -nbc @mp3 $hget(mp3,c2) $hget(mp3,c1) $calc($hget(mp3,nowplay.x)+2) $calc($hget(mp3,nowplay.y)+4) $calc(($hget(mp3,nowplay.w)-4)) 10 $iif($right($nopath($1-),- [ $+ [ $hget(mp3,tick) ] ] ),$v1,$nopath($1-))
  up
  hadd mp3 tickway $iif($width($iif($right($nopath($1-),- [ $+ [ $hget(mp3,tick) ] ] )   || $hget(mp3,tick) <= -20,$v1,$nopath($1-)),tahoma,10) >= $hget(mp3,nowplay.w),$hget(mp3,tickway),$iif($hget(mp3,tickway) == hinc,hdec,$v2))
  $hget(mp3,tickway) mp3 tick
  .timerticker -m  1 100 ticker $1-
}
alias -l seek {
  if ($window(@mp3) && $mouse.key & 1 && %seek == on) { 
    if ($mouse.x isnum $calc($hget(mp3.vol.x)+2) $+ - $+ $calc($hget(mp3,seek.x)+ $hget(mp3,seek.w))) {
      hadd mp3 pos $iif($v1 < $calc(($hget(mp3,seek.x)+ $hget(mp3,seek.w))-12),$calc($v1 - 10),$calc($v1 - 20))
      splay seek $ceil($calc(($v1 -($hget(mp3,seek.x)+2)) * ($mp3($insong.fname).length /(($hget(mp3,seek.x)+ $hget(mp3,seek.w))-12))))
      hadd mp3 songtime $ceil($calc(($v1 - ($hget(mp3,seek.x)+2)) * ($mp3($insong.fname).length / ((($hget(mp3,seek.x)+ $hget(mp3,seek.w))-12)*1000))))
      drawtext -bc @mp3 $hget(mp3,c2) $hget(mp3,c1) $ceil($calc($hget(mp3,time.x)+(($hget(mp3,time.w)-20)/2))) $ceil($calc($hget(mp3,time.y)+(($hget(mp3,time.h)-14)/2))) 20 10 $asctime($hget(mp3,songtime),n:ss)
      drf $hget(mp3,c1) 1 $calc($hget(mp3,seek.x)+1) $calc($hget(mp3,seek.y)+1) $calc($hget(mp3,seek.w)-2) $calc($hget(mp3,seek.h)-2)
      drf $hget(mp3,c2) 1 $calc(($hget(mp3,seek.x)+1) + $hget(mp3,pos)) $calc($hget(mp3,seek.y)+2) 10 $calc($hget(mp3,seek.h)-4)
      up
    }
    .timer -m 1 0 seek
  }
}
alias -l vc {
  if ($1 isnum $calc($hget(mp3,vol.x)+2) $+ - $+ $calc($hget(mp3,vol.x)+($hget(mp3,vol.w)-2))) {
    drf $hget(mp3,c1) 1 $calc($hget(mp3,vol.x)+1) $calc($hget(mp3,vol.y)+1) $calc($hget(mp3,vol.w)-2) $calc($hget(mp3,vol.h)-2)
    drf $hget(mp3,c2) 1 $calc(10 + $iif($1 < $calc($hget(mp3,vol.x)+( $hget(mp3,vol.w)-10)),$calc($v1 - 10),$calc($v1 - 15))) $calc($hget(mp3,vol.y)+2) 5 $calc($hget(mp3,vol.h)-4)
    up
    vol -v $ceil($calc((($1 - $hget(mp3,vol.x)) * 65535)/ $hget(mp3,vol.w)))
  }
}
alias -l drawsongs {
  var %b mp3play.txt
  if ($1 == s) {
    drf $hget(mp3,c1) 1 $calc($hget(mp3,play.x) +1) $calc($hget(mp3,play.y) +1) $calc($hget(mp3,play.w) -14) $calc($hget(mp3,play.h) -2)
    if ($isfile(%b)) { 
      var %x $2, %m 1
      while ($read(%b,%x) && %x < $3) { 
        drawtext -nbc @mp3 $hget(mp3,c2) $hget(mp3,c1) $calc($hget(mp3,play.x) +1) $calc(($hget(mp3,play.y) -9)+(%m *10)) $calc($hget(mp3,play.w) -16) 10 $remove($nopath($read(%b,%x)), [ $iif($hget(mp3,filex) == true,$+(.mp3,$chr(44),.wav,$chr(44),.wma)) ] )
        inc %x 
        inc %m
      } 
    }
    up
  }
}
alias -l define {
  if ($1 == default) {
    hadd mp3 time.x 10
    hadd mp3 time.y 45
    hadd mp3 time.w 50
    hadd mp3 time.h 20
    hadd mp3 nowplay.x 10
    hadd mp3 nowplay.y 20
    hadd mp3 nowplay.h 20
    hadd mp3 nowplay.w 230
    hadd mp3 buttons.x 74
    hadd mp3 buttons.y 43
    hadd mp3 buttons.w 0
    hadd mp3 buttons.h 0
    hadd mp3 mainwin.x 300
    hadd mp3 mainwin.y 500
    hadd mp3 mainwin.w 250
    hadd mp3 mainwin.h 370
    hadd mp3 vol.x 188 
    hadd mp3 vol.y 50 
    hadd mp3 vol.w 52
    hadd mp3 vol.h 10
    hadd mp3 seek.x 10 
    hadd mp3 seek.y 70 
    hadd mp3 seek.w 230 
    hadd mp3 seek.h 10
    hadd mp3 play.x 9 
    hadd mp3 play.y 83  
    hadd mp3 play.w 232 
    hadd mp3 play.h 277
    hadd mp3 miniwin.x 300
    hadd mp3 miniwin.y 500
    hadd mp3 miniwin.w 250
    hadd mp3 miniwin.h 90
  }
  elseif $1 != k {
    var %x $1
    :ask
    if ($input(Input is defined as $qt(x value y value width height),eq,Define Element,$hget(mp3,$1 $+ .x) $hget(mp3,$1 $+ .y) $hget(mp3,$1 $+ .w) $hget(mp3,$1 $+ .h))) {
      var %o $v1
      if ($regex($v1,/^(\d+ ){3}\d+$/)) {
        tokenize 32 %o
        hadd mp3 %x $+ .x $1
        hadd mp3 %x $+ .y $2
        hadd mp3 %x $+ .w $3
        hadd mp3 %x $+ .h $4
      }
      else {
        noop $input(Invalid input. Please try again.,io,Invalid input!)
        goto ask
      }
    }
  }
  else {
  }
  refresh
}
on *:unload: {
  if ($input(Would you like to remove the associated configuration and library files?,yv,Remove?) == $yes && $isfile(mp3.conf) && $isfile(mp3play.txt) {
    .remove mp3.conf
    .remove mp3play.txt
  }
}
