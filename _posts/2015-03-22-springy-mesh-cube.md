---
title: Springy Mesh Cube
tags: ["project"]
---

A simulation of a cube represented as a springy mesh (connected corners) that I
built Spring 2015 for Dr. Keyser's Physically Based Modeling (CSCE-649) class at
Texas A&amp;M using three.js and vanilla Javascript. It uses
[Runge-Kutta](http://web.mit.edu/10.001/Web/Course_Notes/Differential_Equations_Notes/node5.html)
Euler method to integrate movement over time. It uses Runge Kutta second-order
by default but can optionally use fourth-order for greater stability.

You can use A and D keys to rotate the view.

[Demo]({{ site.url | append: '/projects/springy_mesh'}})
