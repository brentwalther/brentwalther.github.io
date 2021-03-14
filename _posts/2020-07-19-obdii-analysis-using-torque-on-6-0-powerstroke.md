---
tags:
- project
title: Analyzing my 6.0L Powerstroke ODBII data
---
I recently took my 2003 F-250 on a trip pulling a single-axle camper trailer behind it. I've had the truck now for a few months and am well aware of the shortcomings of the 6.0L Powerstroke engine but wanted to collect some data for my engine to use as justification and prioritization for upgrades. I've been looking at the parts offered by [BulletProofDiesel](https://bulletproofdiesel.com/pages/bullet-proof-your-ford-6-liter) for quite some time but didn't want to unnecessarily upgrade parts without first establishing a baseline for engine health. Using a bluetooth ODBII adapter and the Torque Android app, I collected that data on this recent trip. I estimate the total combine weight of the trailer, cargo, and passengers to be ~3000 pounds (on top of the truck curb weight).

![My 2003 F-250.](/img/2003_f250.jpg)

<!--excerpt_end-->

### The engine oil cooler

As described in this BulletProofDiesel article <a href="#footnote-2" id="fnl-2">\[2\]</a>, the engine oil cooler is one of the main things on a 6.0L Powerstroke that is prone to failures (especially on higher mileage vehicles). If it fails, the failure can quickly cascade to other parts (e.g. the EGR cooler) too. To know whether or not the oil cooler is beginning to cause problems for you, you need to know the temperatures of your engine oil and engine cooleant. It's recommended that the temperature of the two differ no more than about 10-15 degrees Farhenheit <a href="#footnote-1" id="fnl-1">\[1\]</a>.

I used the [Torque Android app](https://play.google.com/store/apps/details?id=org.prowl.torque&hl=en_US) and a bluetooth ODBII adapter to monitor the two temperatures using the truck's own sensors. The app polls the ECM for sensor values on a regular basis and can be configured to log specific values in a CSV file for export later. I monitored the values for an 8 hour trip between <a href="https://goo.gl/maps/nC38TSYrvBAzCsYY6">Palo Duro Canyon State Park</a> (outside of Amarillo, TX) back to our home in Austin, TX.

After collecting the data it's clear that replacing my oil cooler, potentially with an upgrade, is a priority repair for this truck. While we didn't hit any snags along the trip, the engine oil ran quite hot. It ran 224°F on average and was as hot as 236°F during the trip. The coolant averaged 208°F. The difference between the two was 15.6°F on average but was as great as 25.5°F later on in the trip (after pulling for several hours).

<div id="diffchart"></div>

I also measured the temperatures of the engine coolant and oil separately throughout the trip. The big dips you see in the graph are the truck cooling off while we stopped for a running refuel. If you squint, you can actually see average coolant temperature decreasing more quickly than the oil. The ambient outdoor temperature was well over 100°F in the panhandle but cooled down to around 95°F by the time we got home. I think the fact that the two trend lines don't track perfectly is also a symptom of inefficient cooling since the OEM oil cooler cools the oil with the coolant itself <a href="#footnote-2">\[2\]</a>.

<div id="tempchart"></div>

### Footnotes

- <span id="footnote-1">\[1\]</span> According to [this](http://www.powerstrokehub.com/service/6.0-powerstroke-diagnostics-and-troubleshooting.html) troubleshooting guide, over 10 degrees Fahrenheit difference between engine oil and coolant temperatures is cause for concern. <a href="#fnl-1">\[Return\]</a>
- <span id="footnote-2">\[2\]</span> <a href="https://bulletproofdiesel.com/pages/bullet-proof-your-6-liter-engine-oil-cooler">https://bulletproofdiesel.com/pages/bullet-proof-your-6-liter-engine-oil-cooler</a> <a href="#fnl-2">\[Return\]</a>

<script src="https://d3js.org/d3.v5.min.js"></script>

<script type="text/javascript">
d3.csv("/files/2020-07-18-torque-temperature-data.csv").then(rawdata => {
      const height = 300;
      const width = document.querySelector("body > main").offsetWidth;
      const margin = ({
        top: 10,
        right: 10,
        bottom: 20,
        left: 50
      });
      // TODO: Creates tons of garbage Date objects. Should probably simplify to UTC-seconds.
      const diffdata = rawdata.map(row => ({
        time: new Date("2020-07-19 " + row["Time"]),
        value: row["Diff"]
      }));

      const times = rawdata.map(row => new Date("2020-07-19 " + row["Time"]));
      const coolantTemps = rawdata.map(row => row["ECT"]);
      const oilTemps = rawdata.map(row => row["EOT"]);

      const temperatureData = {
        times: times,
        series: [
          {name: "Coolant Temp", values: coolantTemps},
          {name: "Oil Temp", values: oilTemps}
        ],
      };

      x = d3.scaleTime()
        .domain(d3.extent(diffdata, d => d.time))
        .range([margin.left, width - margin.right]);

      diffY = d3.scaleLinear()
        .domain([0, d3.max(diffdata, d => Number(d.value))])
        .nice()
        .range([height - margin.bottom, margin.top]);

      tempY = d3.scaleLinear()
        .domain([150, 250])
        .nice()
        .range([height - margin.bottom, margin.top]);

      xAxis = g => g
        .attr("transform", `translate(0,${height - margin.bottom})`)
        .call(d3.axisBottom(x)
          .tickFormat(d3.timeFormat("%I:%M %p")));

      const createChart = function(yFunc, applyPaths) {
        const svg = d3.create("svg")
          .attr("viewBox", [0, 0, width, height]);

        svg.append("g")
          .call(xAxis);

        svg.append("g")
          .attr("transform", `translate(${margin.left},0)`)
          .call(d3.axisLeft(yFunc));

        svg.append("text")
          .attr("transform", "rotate(-90)")
          .attr("y", 0)
          .attr("x", 0 - (height / 2))
          .attr("dy", "1em")
          .style("text-anchor", "middle")
          .text("Temperature (°F)");

        applyPaths(svg);

        return svg.node();
      };

      const diffchart = createChart(
        diffY,
        svg => 
          svg.append("path")
            .datum(diffdata)
            .attr("fill", "none")
            .attr("stroke", "steelblue")
            .attr("stroke-width", 1.5)
            .attr("stroke-linejoin", "round")
            .attr("stroke-linecap", "round")
            .attr(
              "d",
              d3.line()
                  .defined(d => !isNaN(d.value))
                  .x(d => x(d.time))
                  .y(d => diffY(d.value))));

      const tempchart = createChart(
        tempY,
        svg =>
          svg.append("g")
            .attr("fill", "none")
            .attr("stroke", "steelblue")
            .attr("stroke-width", 1.5)
            .attr("stroke-linejoin", "round")
            .attr("stroke-linecap", "round")
            .selectAll("path")
            .data(temperatureData.series)
            .join("path")
            .style("mix-blend-mode", "multiply")
            .attr(
              "d",
              d => d3.line()
                .defined(d => !isNaN(d))
                .x((d, i) => x(temperatureData.times[i]))
                .y(d => tempY(d))(d.values)));

      document.getElementById('diffchart')
        .appendChild(diffchart);

      document.getElementById('tempchart')
        .appendChild(tempchart);
});
</script>
