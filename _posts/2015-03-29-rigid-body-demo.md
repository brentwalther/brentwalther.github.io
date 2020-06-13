---
title: Rigid Body Demo
tags: ["project"]
---

A simulation of a rigid-body cube that I built Spring 2015 for Dr. Keyser's
Physically Based Modeling (CSCE-649) class at Texas A&amp;M using three.js and
vanilla Javascript. It uses basic euler integration by default which can be
unstable but has an option to use
[Runge-Kutta](http://web.mit.edu/10.001/Web/Course_Notes/Differential_Equations_Notes/node5.html)
second-order method for greater stability.

You can use A and D keys to rotate the view. A rigid body (in physical
simulation) is most basically described as a dice or other hard object which
simply transfers energy within itself rather than absorbing it via a spring
force.

[Demo]({{ site.url | append: '/projects/rigid_body'}})
