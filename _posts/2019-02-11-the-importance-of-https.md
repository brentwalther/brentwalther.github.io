---
layout: post
title: The importance of HTTPS
author: Brent Walther
---

## Background
Our lives are touching more technology than they ever have. Despite technology providing a tremendous amount of value, there are still risks associated with using it. These risks are minimized when there is some level of verifiable (at least to some degree) trust between you as a client (whether that means browsing the internet or using a mobile app) and the entities hosting webpages and application backends. HTTPS provides a mean of doing this - verifying authenticity and providing fully encrypted security.

Over the years, I've hosted a variety of different websites and web services. Most recently, I've maintained a personal website and an archival collection of projects I've worked on using two different domains and hosting services.  Some of my old projects were served over HTTP by an aging VPS (Virtual Private Server) I had running on [Digital Ocean](https://www.digitalocean.com/) running Apache web server without SSL enabled. Knowing that the internet is moving to increase adoption of HTTPS (A cryptographically secure version of HTTP), I decided to ditch the Digital Ocean VPS and move those projects over to Github Pages which has standard support for [LetsEncrypt SSL certificates](https://github.blog/2018-05-01-github-pages-custom-domains-https/).

## What is HTTPS, and why does it matter?
To answer this questions, I first pose a question to the reader: When you use a browser to access any website (for example, [Google](//www.google.com), [Facebook](//www.facebook.com), or  [Chase Bank](//www.chase.com/)) how do you know that the web page being displayed on your browser is the original, authentic page that the company intended for you to see? How can you be sure that the servers your browser connected to retrieve that page were trustworthy? At least for the examples sites above, it is because the website is hosted using HTTPS (a 'S'ecure version of HTTP).

When you visit a website that is hosted on HTTPS, your browser fetches the webpage from the server using a cryptographically secure exchange of data. In other words, all the requests that your browser makes to the server and all it's responses are fully encrypted end-to-end; they are hidden from anyone trying to snoop on or attack (a [man-in-the-middle attack](https://en.wikipedia.org/wiki/Man-in-the-middle_attack)) the connection. For me, I didn't think that the maintenance burden of securing my own server was worth it -- getting security right by yourself is difficult, and being able to consolidate all the artifacts I serve on the internet into one  place with easily implemented security measures [1] was too good an opportunity to pass up on.

## How do I know if a website is hosted using HTTPS?
If you want to know if the webpage and hosting entity are trustworthy, all you have to do is look closely at the address bar always checking for either https:// and/or padlock icon. For example, accessing Google on Google Chrome using HTTPS looks like this:

![Accessing Google.com over HTTPS shows the padlock icon.](https://brentwalther.net/img/google_https_icon.png)

You should always verify that HTTPS is used if you're transmitting *any* sensitive data including account passwords, credit card details, or personal email. To make this more obvious, Chrome 68 [2] and Safari 11.1 [3] (both released in in 2018) give you warnings when a page is insecure. They look like this:

| | |
| --- | --- |
| Chrome | ![Accessing an unsecure website using Chrome 68+ shows an unsecure warning.](https://brentwalther.net/img/chrome_not_secure.png) |
| Safari | ![Accessing an unsecure website using Safari 11.1+ shows an unsecure warning.](https://brentwalther.net/img/safari_not_secure.png)|

Now that my own personal migration is complete, my entire site is being served over HTTPS which means you can trust that its pages are authentic and created by me:

![Accessing brentwalther.net over HTTPS shows the padlock icon.](https://brentwalther.net/img/https_brentwalther_net.png)

## Give me more details. How does HTTPS work?
Modern HTTPS is based on special certificates (similar to an *original and unique* signature) that both web servers and your browser have. When establishing a connection, your browser first verifies the identity of the server certificate based on what are called [certificate authorities](https://en.wikipedia.org/wiki/Certificate_authority). Certificate Authorities (CAs) are entities (companies, groups) that issue and maintain digital certificate records. CAs work together to form a hierarchy of trust (and actually, there are certificate models that don't require a hierarchy such as what's called a "[Web of Trust](https://en.wikipedia.org/wiki/Web_of_trust)") which is the basis for authenticity. Your browser and operating system have lists of CAs, and they are the ones that validate/prove "the certificate for Google.com is actually owned by Google Inc."
 
Everyone has their own *public* certificate which functions like a digital signature. To form an encrypted channel, you first verify (using a CA) that a servers public certificate is authentic. Then, you do what's called a key exchange with the server which allows you to form the encrypted channel for data to flow through that only you and the server are able to access. The exchange is called a [Diffie-Hellman](//en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange) exchange and relies on some mathematical properties of each public key and their relationship to what's called a *private key*. While a public key is a signature, a private key is an accompanying passphrase that only the entity owning the public key knows. The relation between the public and private key is any data that is encrypted using your public key can be decrypted by your (and only your) private key. An encrypted exchange would go something like this:

1. You (client <sup>A</sup>) have data X that you want send to server <sup>B</sup>
2. You calculate a random value (R)
2. You encrypt the random value with (<sup>B's</sup>) public key (which was validated by the CA). The data is now in encrypted form: R<sup>B</sup>
3. You send it over to server B (which could be any site supporting HTTPS).
4. The server decrypts R<sup>B</sup> with its own *private* key and gets the original R. Using R and adding some of its own data, it produces a new R: R'
5. It encrypts R' with *your* public key. The random value is now in encrypted form: R'<sup>A</sup>
6. You decrypt the random value R'<sup>A</sup> to get R' which is a random value that **only you and the server know**. That random value (it is very large) can be used to encrypt/decrypt any later transmissions of data between the two for some amount of time (called session length).

How did this work? Well, both entities worked together to calculate the random value: You created the first part and the server derived the second. You both also allowed only each other to have knowledge of both versions of the random value because it was encrypted going both directions. Using this exchange, two independent entities are able to compute a shared random value (called a key) that they can then use to encrypt/decrypt data transmissions going back and forth using what's called a [symmetric key algorithm](https://en.wikipedia.org/wiki/Symmetric-key_algorithm).

### Links
[1] [Custom domains on GitHub Pages gain support for HTTPS](https://github.blog/2018-05-01-github-pages-custom-domains-https/). Published 2018-05-01 by Parker Moore. Retrieved 2019-02-10.

[2] [A milestone for Chrome security: marking HTTP as “not secure”.](https://www.blog.google/products/chrome/milestone-chrome-security-marking-http-not-secure/) Published 2018-07-24 by Emily Schechter. Retrieved 2019-02-10.

[3] [Safari Warns about Unsecure Logins](https://www.digicert.com/blog/safari-warns-about-unsecure-logins/) Published 2018-03-30 by Vincent Lynch. Retrieved 2019-02-10