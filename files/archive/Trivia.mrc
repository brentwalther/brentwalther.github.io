alias -l d1 { return $+($chr(3),%col1,$1-) }
alias -l d2 { return $+($chr(3),%col2,$1-) }
alias -l d3 { return $+($chr(3),%col3,$1-) }
on *:text:!start:%tchan:{ 
  if (!%triv && $nick isop $chan) {
    unset %queson
    start.triv
  }
  else { 
    msg $chan Access Denied. 
  }
}
on *:text:!stop:%tchan:{ 
  if (%triv && $nick isop $chan) {
    stop.triv
  }
  else {
    msg $chan Access Denied.
  }
}
alias start.triv {
  inc %totalq 1
  msg %tchan Trivia has begun.
  set %triv on
  set %rlo $read(questions.txt)
  set %time $ticks
  .timertriv 1 30 /timeup [ $+ [ $rand(1,2) ] ]
  ques [ $+ [ $rand(1,2) ] ]
  .timerthint 1 15 /hint
}

alias stop.triv {
  msg %tchan Trivia has ended.
  unset %triv
  unset %rlo
  unset %time
  .timert* off
}
alias next.triv {
  inc %totalq 1
  set %rlo $read(questions.txt,$r(1,$lines(questions.txt)))
  unset %queson
  set %time $ticks
  ques [ $+ [ $rand(1,2) ] ]
  .timertriv 1 30 /timeup [ $+ [ $rand(1,2) ] ]
  .timerthint 1 15 /hint
}
on *:text:*:%tchan:{ 
  if (%triv && $me == $scon(1).me) {
    if ($1- == $gettok(%rlo,2,58) && !%queson) {
      set %queson on
      ans [ $+ [ $rand(1,2) ] ] $nick
      set %time $round($calc((%time - $ticks )/1000),0)
      writeini -n scores.ini $chan $nick $calc($readini(scores.ini, $chan, $nick) + $calc(30 + %time ))
      point [ $+ [ $rand(1,2) ] ]
      nextq
    }
    elseif ($1 isin $gettok(%rlo,2,58) && !%queson) {
      .notice $nick You are close!
    }
  }
  elseif ($regex($1,/[!@](suggest(ion)?)/Si)) {
    if (%gon. [ $+ [ $nick ] ]) { notice $nick You have suggested a quesiton in the last 60 seconds. Please wait before submitting another. | halt }
    elseif ($chr(58) !isin $2-) { notice $nick Incorrect syntax. The syntax is: "Question:Answer" } 
    else { 
      notice $nick Thank you for the suggestion! It will be reviewed and possibly added to the quesiton list.
      write suggs.txt $2-
      set -u60 %gon. [ $+ [ $nick ] ] 1
    }
  }
}



alias -l ques1 { msg %tchan $d1(Question $+($chr(35),%totalq,$chr(58))) $d2($gettok(%rlo,1,58)) $d1(You have 30 seconds to answer.) }
alias -l ques2 { msg %tchan $d1(Next question:) $d2($gettok(%rlo,1,58)) $d1(30 seconds and counting...) }
alias -l ans1 { msg %tchan $d1(Congratulations) $d2($1) $d1(The answer was:) $d2($gettok(%rlo,2,58)) }
alias -l ans2 { msg %tchan $d1(Nice Job) $d2($1) $d1(Your answer was:) $d2($gettok(%rlo,2,58)) }
alias -l point1 { msg %tchan $d1(You managed to answer the question in) $d2($abs(%time)) $d1(seconds which earns you) $d2($calc(30 + %time )) $d1(points. Your new score is:) $d2($bytes($readini(scores.ini, %tchan, $nick),db)) }
alias -l point2 { msg %tchan $d1(Your response time was) $d2($abs(%time)) $d1(seconds which will get you) $d2($calc(30 + %time )) $d1(points. Your scores is now:) $d2($bytes($readini(scores.ini, %tchan, $nick),db)) } 
alias -l timeup1 { set %queson lol | msg %tchan $d1(Times up! The correct answer was:) $d2($gettok(%rlo,2,58)) | /nextq }
alias -l timeup2 { set %queson lol | msg %tchan $d1(Looks like no one knew it... It was:) $d2($gettok(%rlo,2,58)) | /nextq }
alias -l almost { msg %tchan $d1(Trivia will being again in) $d2(30) $d1(seconds!) }
alias -l hint { if ($gettok(%rlo,3,58)) { msg %tchan $d1(Hint:) $d2($v1) } }
alias -l nextq {
  if 7 // %totalq {
    .timert* off
    /highscores
    .timert 1 15 /next.triv
  }
  elseif 40 // %totalq {
    .timert* off
    msg %tchan $d1(The trivia will begin again after this 3 minute break.)
    /highscores
    .timert 1 180 /next.triv
    .timert 1 150 /almost
  }
  else {
    .timert* off
    .timert 1 5 /next.triv
  }
}
alias -l highscores {
  var %c 1
  while ($ini(scores.ini,%tchan,%c)) {
    var %o $+(%o,@,$readini(scores.ini,%tchan,$v1)) - $v1
    inc %c
  }
  var %o $sorttok(%o,64,nr)
  tokenize 64 %o
  msg %tchan $d1(Top 5 trivia scores: $chr(91)) $d2($bytes($gettok($1,1,45),db) - $gettok($1,2,45) $chr(124) $bytes($gettok($2,1,45),db) - $gettok($2,2,45) $chr(124) $bytes($gettok($3,1,45),db) - $gettok($3,2,45) $chr(124) $bytes($gettok($4,1,45),db) - $gettok($4,2,45) $chr(124) $bytes($gettok($5,1,45),db) - $gettok($5,2,45)) $d1($chr(93))
}
alias -l scramble {
  %2 = $iif($2,$2,32)
  %c = 1
  if $3 = %` && %2 $+ . isnum 1-255 {
    while $gettok($1,%c,%2) {
      %g = $v1
      %a = $chr(%2)
      while $len(%a) <= $len(%g) {
        %o = $r(,ÿ)
        if $countcs(%g,%o) > $countcs(%a,%o) && (%o isincs %g) %a = %a $+ %o
      }
      var %` %` $+ %a
      inc %c
    }
    unset %2
    unset %c
    unset %g
    unset %a
    unset %o
    return $mid(%`,2)
  }
}
