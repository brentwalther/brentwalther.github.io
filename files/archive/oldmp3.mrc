;==================================
; Extreme MP3 [ V3 ]
; by Chimpanzee (bmw13cool)
;==================================
; Dialog!

menu * {
  Dialogs
  .Mp3 Player: /mp3pl -o
}
alias mp3play {
  var %a, %b $qt(mp3play.txt), %c !did -ra mp3pl
  if ($1) { 
    if ($1 = -n) && ($2) %a = $2   
    if ($read(%b,w,$+(*,$1-,*))) %a = $readn
    !splay -p $read(%b,%a)
    %c 19
    %c 10 $nopath($read(%b,%a))
    %c 29 $sound($insong.fname).bitrate kbps
    %c 30 $round($calc($sound($insong.fname).sample / 1000),1) kHz
    !did -c mp3pl 1 %a
    .timermp3 -m 27 $ceil($calc($mp3($read(%b,%a)).length / 27)) /did -a mp3pl 19 *
    !set %songtime 0
    !set %songlength $asctime($calc($mp3($read(%b,$did(mp3pl,1).sel)).length / 1000),nn:ss)
    .timermp4 0 1 st
    if ($readini(mp3play.ini, n, settings, adenable)) {
      var %out $replace($readini(mp3play.ini, n, settings, message),@title@,$iif($sound($insong.fname).title,$v1,$iif($sound($insong.fname,TIT2).tag,$v1,N/A)),@artist@,$iif($sound($insong.fname).artist,$v1,$iif($sound($insong.fname,TPE1).tag,$v1,N/A)),@length@,%songlength,@bitrate@,$sound($insong.fname).bitrate kbps,@album@,$iif($sound(%b).album,$v1,$iif($sound(%b,TALB).tag,$v1,N/A)))
      !tokenize 124 $readini(mp3play.ini, n, settings, adchans)
      msg $* %out
    }
  }
}
alias -l st {
  !did -ra mp3pl 27 $asctime(%songtime,nn:ss) - %songlength
  !inc %songtime
}

alias mp3pause {
  var %a !splay -p
  if ($inmp3) {
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

alias mp3stop {
  if ($inmp3) {
    !splay stop
    .timermp* off
    !did -r mp3pl 19
  }
}
alias mp3pl {
  !var %b $qt(mp3play.txt), %c $readini(mp3play.ini, n, settings, display)
  if ($1 = -o) {
    if ($dialog(mp3pl)) dialog -v mp3pl mp3pl
    else {
      !dialog -md mp3pl mp3pl
      if ($isfile(%b)) {
        if (%c = filename) || (%c = $null) {
          !var %x 1
          while ($read(%b,%x)) {
            !did -za mp3pl 1 $nopath($v1)
            !inc %x
          }
        }
        elseif (song isin %c) && (artist isin %c) {
          !var %x 1
          while ($read(%b,%x)) {
            !did -za mp3pl 1 $replace(%c,song,$iif($sound($read(%b,%x)).title,$v1,$iif($sound($read(%b,%x),TIT2).tag,$v1,N/A)),artist,$iif($sound($read(%b,%x)).artist,$v1,$iif($sound($read(%b,%x),TPE1).tag,$v1,N/A)))
            !inc %x
          }
        }
      }
    }
  }
  elseif ($1 = -c) && ($isfile(%b)) {
    .remove %b
    if ($dialog(mp3pl)) !did -r mp3pl 1 
  }
}
alias mp3pla { 
  var %a 1, %b $qt(mp3play.txt), %c !write %b, %d $qt($iif($1 = -d,$sdir(C:\),$sfile($mircdir))), %e !did -az mp3pl 1
  if ($1 = -d) {
    while ($findfile(%d,*.mp3,%a)) {
      !var %f $v1
      if (!$read(%b,w,$+(*,%f,*))) {
        %c %f
        if ($dialog(mp3pl)) %e $nopath(%f)
      }
      !inc %a
    }
    !echo -ac info You have added %a files to the playlist.
  }
  elseif (!$read(%b,w,$+(*,$remove(%d,"),*))) {
    %c %d
    if ($dialog(mp3pl)) %e $remove($nopath(%d),")
    !echo -ac info File $qt($nopath(%d)) was added to the playlist.
  }
}
alias mp3plr {
  !var %a $qt(mp3play.txt)
  if ($1) && ($read(%a,w,$+(*,$1-,*))) {
    !var %c $input(Would you like to continue with the removal of $qt($v1) ?,ynv,Remove?)
    if (%c == $yes) {
      !write -dl $+ $readn %a
      if ($dialog(mp3pl)) !did -d mp3pl 1 $readn
    }
  }
}
alias mp3info {
  if (!$dialog(mp3f)) dialog -mv mp3f mp3f
  var %a !did -ra mp3f, %b $iif($isfile($qt($1-)),$1-,$iif($inmp3,$insong.fname,$null))
  %a 1 $nopath(%b)
  %a 3 Title: $iif($sound(%b).title,$v1,$iif($sound(%b,TIT2).tag,$v1,N/A))
  %a 4 Track length: $asctime($calc($mp3(%b).length / 1000),nn:ss)
  %a 2 Artist: $iif($sound(%b).artist,$v1,$iif($sound(%b,TPE1).tag,$v1,N/A))
  %a 5 Bitrate: $+($sound(%b).bitrate,kbps) / Mode: $sound(%b).mode / Size: $bytes($file(%b)).suf
  %a 6 $str(-,25)
  %a 7 Year: $iif($sound(%b).year,$v1,$iif($sound(%b,TYER).tag,$v1,N/A))
  %a 8 Genre: $iif($sound(%b).genre,$v1,N/A)
  %a 10 $iif($sound(%b).comment,$v1,$iif($sound(%b,COMM).tag,$v1,N/A))
  %a 11 Copyright: $iif($sound(%b).copyright,$iif($v1 = $false,No,Yes),N/A)
  %a 12 Album: $iif($sound(%b).album,$v1,$iif($sound(%b,TALB).tag,$v1,N/A))
}
dialog -l mp3f {
  title "File info"
  size -1 -1 127 132
  option dbu
  edit "", 1, 1 1 126 10, read autohs
  text "Title: N/A", 3, 1 11 125 9
  text "Artist: N/A", 2, 1 20 125 9
  text "Track length: 00:00", 4, 1 29 125 9
  text "Bitrate: 0kbps / Mode: N/A / Freq: 0 Hz", 5, 1 38 125 9
  text "", 6, 1 47 125 9
  text "Year: N/A", 7, 1 56 125 9
  text "Genre: N/A", 8, 1 65 125 9
  text "Comment:", 9, 1 92 125 9
  edit "", 10, 1 101 125 21, read autovs multi
  text "Copyright: N/A", 11, 1 74 125 9
  text "Album: N/A", 12, 1 83 125 9
  button "Done", 13, 48 122 30 10, cancel
}
dialog mp3pl {
  title "Extreme MP3 [ V3 ]"
  size -1 -1 133 186
  option dbu
  list 1, 1 49 131 124, hsbar vsbar size
  menu "&File", 2
  menu "Add/Del", 3, 2
  item "Directory", 4, 3
  item "File", 5, 3
  item "break", 6, 2
  item "Delete Selected", 7, 2
  item "break", 8, 2
  item "Clear", 9, 2
  edit "", 10, 1 11 128 10, center
  text "Now Playing:", 11, 3 4 40 7
  button "-", 12, 1 23 10 10
  button "+", 13, 27 23 10 10
  button "<<", 14, 39 23 15 10
  button "Stop", 15, 55 23 16 10
  icon 16, 72 23 19 10, PauseNormal.png, C:\Program Files\mirc.bmw\mp3player\, noborder
  icon 17, 92 23 16 10, untitled.png, C:\Program Files\mirc.bmw\, noborder
  button ">>", 18, 109 23 15 10
  edit "", 19, 39 34 85 6
  menu "Way", 21, 2
  item "Continuous", 22, 21
  item "Repeat", 23, 21
  item "Shuffle", 24, 21
  edit "", 25, 26 175 94 9, center
  text "Search:", 26, 7 176 17 7
  text "", 27, 39 41 85 7, center
  edit "", 28, 11 24 16 8, center
  text "", 29, 1 33 37 8, center
  text "", 30, 1 40 37 8, center
  item "Song Info", 32, 2
  item "Advert Options", 33, 2
  item "Lyrics Searcher", 34, 2
  menu "Display Mode", 35, 2
  item "Filename", 36, 35
  item "Song - Artist", 37, 35
  item "Artist - Song", 38, 35
  menu "Open .m3u playlist" 39, 2
}
dialog -l mp3o {
  title "Advert Options"
  size -1 -1 80 152
  option dbu
  box "Advert Channels", 1, 5 14 72 124
  check "Enable Adverts", 2, 5 4 50 10
  text "Msg an advert in:", 3, 10 22 50 8
  list 4, 8 30 66 40, size, hsbar
  text "Availabe channels:", 5, 10 75 50 8
  list 6, 8 83 66 40, size, hsbar
  button "Delete", 7, 13 125 25 10
  button "Done", 8, 13 140 25 10, cancel
  button "Add", 9, 43 125 25 10
  button "Message", 10, 43 140 25 10
}
dialog -l mp3am {
  title "Advert Message"
  size -1 -1 80 67
  option dbu
  text "Insert these into your personal message to get the respective feature:", 1, 2 2 76 20, center
  combo 2, 5 22 70 10, drop, center
  text "Message:", 3, 2 35 30 10
  edit "", 4, 5 42 70 10, autohs
  button "Submit", 5, 22 55 25 10
}
dialog -l mp3ly {
  title "Lyrics Searcher"
  size -1 -1 200 247
  option dbu
  edit "", 1, 5 10 190 230, center, read, vsbar, multi
  text "Lyrics Searcher for Extreme MP3", 2, 5 240 190 8, center
}
on *:dialog:*:init:0:{
  if ($dname = mp3pl) {
    !did -b $dname 7,19,32,34
    !did -a $dname 10 Pick a song and press Play to begin
    if (!$readini(mp3play.ini, n, settings, volume)) !writeini -n mp3play.ini settings volume 100
    !did -a $dname 28 $+($readini(mp3play.ini, n, settings, volume),$chr(37))
    !vol -v $ceil($calc(($readini(mp3play.ini, n, settings, volume) * 65535) / 100))
  }
  if ($dname = mp3o) {
    if (!$readini(mp3play.ini, n, settings, adenable)) !did -b $dname 4,6
    else !did -c $dname 2
    if ($readini(mp3play.ini, n, settings, adchans)) {
      tokenize 124 $v1
      !did -a $dname 4 $*
    }
    var %x 1
    while ($chan(%x)) {
      !did -a $dname 6 $v1
      inc %x
    }
  }
  if ($dname = mp3am) {
    !tokenize 124 @title@ - title|@artist@ - artist|@album@ - album|@bitrate@ - bitrate|@length@ - length of song
    !did -a mp3am 2 $*
    !did -a mp3am 4 $readini(mp3play.ini, n, settings, message)
  }
  if ($dname = mp3ly) {
    if ($sock(lyr)) .sockclose lyr
    sockopen lyr www.azlyrics.com 80
  }
}
on *:dialog:*:close:0: {
  if ($dname = mp3pl) {
    .timermp* off
    !splay stop
  }
  if ($dname = mp3o) {
    !dialog -v mp3pl
  }
  if ($dname = mp3ly) {
    !dialog -v mp3pl
  }
}
on *:dialog:mp3pl:edit:25: {
  !var %a $read(mp3play.txt,w,$+(*,$did(25).text,*))
  !did -c mp3pl 1 $readn
}
on *:dialog:mp3pl:menu:*:{
  if ($did = 4) mp3pla -d
  if ($did = 5) mp3pla -f
  if ($did = 7) mp3plr $did($dname,1).seltext
  if ($did = 9) mp3pl -c
  if ($did isnum 22-24) !writeini -n mp3play.ini settings songend $did($v1).text
  if ($did = 32) mp3info $read(mp3play.txt,$did(mp3pl,1).sel)
  if ($did = 33) { dialog -m mp3o mp3o }
  if ($did = 34) { dialog -m mp3ly mp3ly }
  if ($did isnum 36-38) !writeini -n mp3play.ini settings display $did($v1).text
}
on *:dialog:*:sclick:*: {
  var %i $readini(mp3play.ini, n, settings, volume)
  if ($dname = mp3pl) {
    if ($did = 1) { did -e $dname 7,32,34 }
    if ($did = 12) && (%i > 4) {
      !writeini -n mp3play.ini settings volume $calc(%i - 5)
      !did -ra $dname 28 $calc(%i - 5)
      !vol -v $ceil($calc((%i * 65535) / 100))
    }
    if ($did = 13) && (%i < 96) {
      !writeini -n mp3play.ini settings volume $calc(%i + 5)
      !did -ra $dname 28 $calc(%i + 5)
      !vol -v $ceil($calc((%i * 65535) / 100))
    }
    if ($did = 14) {
      if ($insong.fname) {
        !var %b $calc($readn - 1)
        mp3play $did(mp3pl,1,%b).text
        !did -c mp3pl 1 %b
      }
      else {
        !did -ra mp3pl 10 You aren't playing a song!
      }
    }
    if ($did = 15) {
      mp3stop
      !unset %paused
    }
    if ($did = 16) {
      mp3pause
    }
    if ($did = 17) {
      if (%paused == 5) {
        !splay -p resume
        unset %paused
        .timermp3 -r
        .timermp4 -r
      }
      elseif ($did(1).sel) {
        mp3play -n $v1
      }
      else {
        !did -ra mp3pl 10 You didn't select a song to play!
      }
    }
    if ($did = 18) {
      if ($insong.fname) {
        !var %b $calc($readn + 1)
        mp3play $did(mp3pl,1,%b).text
        !did -c mp3pl 1 %b
      }
      else {
        !did -ra mp3pl 10 You aren't playing a song!
      }
    }
  }
  if ($dname = mp3o) {
    if ($did = 2) {
      if ($readini(mp3play.ini, n, settings, adenable) != $null) {
        !remini mp3play.ini settings adenable
        !did -b $dname 4,6
      }
      else {
        !writeini -n mp3play.ini settings adenable 1
        !did -e $dname 4,6
      }
    }
    if ($did = 7) && ($readini(mp3play.ini, n, settings, adchans) != $null) {
      !writeini -n mp3play.ini settings adchans $deltok(%adchans,$findtok($readini(mp3play.ini, n, settings, adchans),$iif($did(mp3o,6).seltext,$v1,$did(mp3o,4).seltext),1,124),124)
      tokenize 124 $readini(mp3play.ini, n, settings, adchans)
      !did -r $dname 4
      !did -a $dname 4 $*
    }
    if ($did = 9) && (!$findtok($readini(mp3play.ini, n, settings, adchans),$did(mp3o,6).seltext,1,124)) {
      !writeini -n mp3play.ini settings adchans $addtok($readini(mp3play.ini, n, settings, adchans),$did(mp3o,6).seltext,124)
      !did -a mp3o 4 $did(mp3o,6).seltext
    }
    if ($did = 10) !dialog -m mp3am mp3am
  }
  if $dname = mp3am && $did = 5 !writeini -n mp3play.ini settings message $did(4).text
}

on *:dialog:mp3f:close:0:!dialog -v mp3pl mp3pl
on *:dialog:mp3pl:dclick:1:mp3play $read("mp3play.txt",$did($dname,1).sel)
on *:mp3end:{
  !var %a mp3play, %b $qt(mp3play.txt), %d $did(mp3pl,1).sel
  if ($readini(mp3play.ini, n, settings, songend) != $null) {
    if ($v1 = continuous) && (%d != $lines(%b)) %a $read(%b,$calc(%d + 1))
    elseif ($v1 = repeat) %a $read(%b,w,$+(*,$nopath($insong.fname),*))
    elseif ($v1 = shuffle) %a $read(%b,$r(1,$lines(%b)))
  }
}
on *:SOCKOPEN:lyr:{
  !var %x $read(mp3play.txt,$did(mp3pl,1).sel), %a $remove($iif($sound(%x).artist,$v1,$iif($sound(%x,TPE1).tag,$v1,N/A)),$chr(32),$chr(47),$chr(45)), %t $remove($iif($sound(%x).title,$v1,$iif($sound(%x,TIT2).tag,$v1,N/A)),$chr(32),$chr(47),$chr(45))
  !sockwrite -nt $sockname GET $lower($+(/lyrics/,%a,/,%t,.html)) HTTP/1.1
  !sockwrite -nt $sockname Host: www.azlyrics.com
  !sockwrite -nt $sockname $crlf
}
on *:SOCKREAD:lyr: {
  if ($sockerr) !did -a mp3ly 1 $sockerr
  else {
    !var %v, %t
    !sockread %v
    if (*Not Found* iswm %v) { !did -a mp3ly 1 Lyrics not found. | halt }
    elseif (*<FONT size=2>* iswm %v) %b = yes
    elseif (%b == yes) {
      if (*[ <a href=* iswm %v) || (*[Thanks* iswm %v) %b = no
      else {
        !did -a mp3ly 1 $htmlfree(%v) $crlf
      }
    }
  }
}

alias htmlfree { var %x, %i = $regsub($1-,/(^[^<]*>|<[^>]*>|<[^>]*$)/g,$null,%x), %x = $remove(%x,&nbsp;) | return %x }
