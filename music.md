---
layout: default
title: Music
---

<script type="text/javascript">
  document.addEventListener('DOMContentLoaded', function() {
    var data = {
      "Album": {
        "7": [{"item":"A State Of Trance Year Mix 2018 (Mixed by Armin van Buuren)","count":113},{"item":"ASTROWORLD","count":98},{"item":"TESTING","count":77},{"item":"DAMN.","count":77},{"item":"Still Striving","count":65},{"item":"A State Of Trance Episode 871","count":57},{"item":"Starboy","count":54},{"item":"The Turnpike Troubadours","count":52},{"item":"50 Number Ones","count":51},{"item":"Scorpion","count":50},{"item":"Die Lit","count":48}],
        "30": [{"item":"ASTROWORLD","count":191},{"item":"TESTING","count":125},{"item":"A State Of Trance Year Mix 2018 (Mixed by Armin van Buuren)","count":119},{"item":"DAMN.","count":105},{"item":"Still Striving","count":93},{"item":"The Turnpike Troubadours","count":86},{"item":"NOT ALL HEROES WEAR CAPES (Deluxe)","count":70},{"item":"Kamikaze","count":66},{"item":"Not All Heroes Wear Capes","count":61},{"item":"Trilogy","count":61},{"item":"Scorpion","count":60}],
        "90": [{"item":"ASTROWORLD","count":251},{"item":"TESTING","count":197},{"item":"DAMN.","count":162},{"item":"NOT ALL HEROES WEAR CAPES (Deluxe)","count":148},{"item":"Still Striving","count":129},{"item":"A State Of Trance Year Mix 2018 (Mixed by Armin van Buuren)","count":119},{"item":"Trilogy","count":99},{"item":"The Turnpike Troubadours","count":98},{"item":"Goodbye Normal Street","count":96},{"item":"Kamikaze","count":92},{"item":"Diamonds & Gasoline","count":87}],
        "365": [{"item":"ASTROWORLD","count":337},{"item":"Still Striving","count":303},{"item":"TESTING","count":288},{"item":"DAMN.","count":262},{"item":"The Turnpike Troubadours","count":196},{"item":"Goodbye Normal Street","count":169},{"item":"Diamonds & Gasoline","count":157},{"item":"Trilogy","count":155},{"item":"XXYYXX","count":150},{"item":"If You're Reading This It's Too Late","count":149},{"item":"NOT ALL HEROES WEAR CAPES (Deluxe)","count":148}],
      },
      "Artist": {
        "7": [{"item":"Travi$ Scott","count":114},{"item":"Kid Cudi","count":114},{"item":"Flume","count":95},{"item":"A$AP Rocky","count":90},{"item":"Jack Johnson","count":89},{"item":"Pat Green","count":82},{"item":"Drake","count":75},{"item":"Kendrick Lamar","count":68},{"item":"Arkasia","count":63},{"item":"Eminem","count":59},{"item":"Turnpike Troubadours","count":59}],
        "30": [{"item":"Metro Boomin","count":124},{"item":"A$AP Ferg","count":118},{"item":"The Weeknd","count":115},{"item":"Kid Cudi","count":114},{"item":"Travi$ Scott","count":102},{"item":"Pat Green","count":98},{"item":"Kendrick Lamar","count":97},{"item":"Flume","count":95},{"item":"Turnpike Troubadours","count":95},{"item":"Jack Johnson","count":93},{"item":"Drake","count":92}],
        "90": [{"item":"Drake","count":162},{"item":"Metro Boomin","count":158},{"item":"Randy Rogers Band","count":157},{"item":"The Weeknd","count":156},{"item":"A$AP Ferg","count":151},{"item":"Travi$ Scott","count":144},{"item":"Gucci Mane","count":140},{"item":"Pat Green","count":139},{"item":"Ryan Bingham","count":136},{"item":"Turnpike Troubadours","count":135},{"item":"Kendrick Lamar","count":132}],
        "365": [{"item":"Kendrick Lamar","count":474},{"item":"Turnpike Troubadours","count":371},{"item":"J. Cole","count":358},{"item":"Travi$ Scott","count":357},{"item":"The Weeknd","count":343},{"item":"Drake","count":337},{"item":"Pat Green","count":335},{"item":"ScHoolboy Q","count":334},{"item":"Kid Cudi","count":332},{"item":"A$AP Rocky","count":327},{"item":"Ryan Bingham","count":326}],
      }
    };

    var albumsOrArtists = "Album";
    var timePeriod = "90";
    var playCountTypeHeading = document.getElementById('play-count-type-heading');
    var playCountTable = document.getElementById('play-count-table-body');
    var albumButton = document.getElementById('play-count-type-album');
    var artistButton = document.getElementById('play-count-type-artist');
    var timePeriodSelect = document.getElementById('play-count-time-period-select');

    var createRow = function(itemName, count) {
      var row = document.createElement("tr");
      var col1 = document.createElement("td");
      var col2 = document.createElement("td");

      col1.innerText = itemName;
      col2.innerText = count;

      row.appendChild(col1);
      row.appendChild(col2);

      return row;
    };

    var refreshChartTable = function() {
      playCountTypeHeading.innerText = albumsOrArtists;

      while (playCountTable.firstChild) {
        playCountTable.removeChild(playCountTable.firstChild);
      }

      var selectedData = data[albumsOrArtists][timePeriod];
      selectedData.forEach(function(dataPiece) {
        playCountTable.appendChild(
          createRow(dataPiece["item"], dataPiece["count"]));
      });
    };

    albumButton.addEventListener('click', function() {
      albumsOrArtists = "Album";
      refreshChartTable();
    });
    artistButton.addEventListener('click', function() {
      albumsOrArtists = "Artist";
      refreshChartTable();
    });
    timePeriodSelect.addEventListener('change', function() {
      timePeriod = timePeriodSelect.options[timePeriodSelect.selectedIndex].value.toString();
      refreshChartTable(); 
    });

    refreshChartTable();
  });
</script>

<div class="text-center"><a href="#charts">Charts</a> - <a href="#ratings">Ratings</a> - <a href="#concerts">Concerts</a></div>

## Charts {#charts}

This is a snapshot of my top albums and artists based on my
[LastFM](https://www.last.fm/user/brentwalther) listening history. LastFM is a
wonderful tool into understanding what music you actually listen to but you have
to pay to access advanced reporting. I was curious to know what my most "dense"
artists and albums are (which ones I have listened to the most in a given time
period) so I wrote a script to do it for me. Below are the results of an
analysis of over 85,000 personally logged tracks.

<button id="play-count-type-album">Albums</button>
<button id="play-count-type-artist">Artists</button>
<label for="play-count-time-period-select">Time Period:</label>
<select id="play-count-time-period-select">
  <option value="7">7 days</option>
  <option value="30">30 days</option>
  <option value="90" selected="selected">90 days</option>
  <option value="365">365 days</option>
</select>

<table class="full-width">
  <thead>
    <tr>
      <th id="play-count-type-heading" class="full-width">Album</th>
      <th>Play Count</th>
    </tr>
  </thead>
  <tbody id="play-count-table-body" class="full-width">
  </tbody>
</table>
<p class="muted">* Data last updated on Jan 23, 2019.</p>

## Album Ratings {#ratings}

I'm a huge fan of "New Music Friday" (did you know most new music is released on
Friday?). Spotify has a neat page that gives you a list of the top (and maybe
paid too, it's hard to tell) new albums every week that you can browse through.
I try to keep up with interesting new releases in Hip-hop, Rap, Country,
Electronic, and more. This section is a log of some of the things I've listened
to and my specific opinions of them.

My full listen log is on my [LastFM](https://www.last.fm/user/brentwalther). My
Rap/Hip-Hop playlist (which I add too frequently) can be found
[here on Spotify](https://open.spotify.com/user/121624922/playlist/5Rup4WYBgxlnXh1kCV0WCw?si=YYg3wxCzTYOzSYBArmBwoQ).
Be sure to sort by recently added because I've been working on it for 4+ years!

My "rating" system is pretty simple. I rate albums on a five star scale:

- 1/5 - Pass. I probably won't listen to this again.
- 2/5 - Meh. I didn't really like it but I could be convinced to give it another
  try
- 3/5 - Decent. I enjoyed it but it wasn't anything special.
- 4/5 - Yeah! This album was good, and I probably added some songs from it to a
  playlist.
- 5/5 - FIRE! I have it on repeat.

<br><!-- hack <br> to prevent the first review from being scooped up in the generated <li> above. I don't know why it does that. -->

{% for review in site.tags.albumreview %}
  {% include album_review.html page=review %}
{% endfor %}

## Concerts {#concerts}

In addition to listening to a lot of music, I enjoy attending live performances
as well. Below is a collection of concerts I've attended which received a
writeup.

{% for concert in site.tags.concert %}
  {% include embedded_post.html page=concert %}
{% endfor %}
