---
title: Tearable Cloth Simulation
tags: ["project"]
---

A physics simulation of a tearable (and gravity affected) cloth. The cloth is represented as a large spring mesh. I built this in Spring 2015 for Dr. Keyser's Physically Based Modeling (CSCE-649) class at Texas A&amp;M using three.js and vanilla Javascript. It uses [Runge-Kutta](http://web.mit.edu/10.001/Web/Course_Notes/Differential_Equations_Notes/node5.html) fourth-order method to integrate movement over time. 

It's not optimized and evaluating as many nodes as the simulation has in a browser using Javascript is difficult. Thus you'll notice it is a bit buggy, especially on slower computers or mobile browsers. You can use A and D keys to rotate the view and the T key to "tear" the cloth.

[Demo]({{ site.url | append: '/projects/cloth_sim'}})
