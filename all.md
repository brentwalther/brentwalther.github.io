---
title: Post Index
layout: default
---

### Tags

<ul>
{% for tag in site.tags %}
  <li>
    <a href="#{{ tag[0] }}">{{ tag[0] }}</a>
  </li>
{% endfor %}
  <li><a href="#untagged">untagged</a></li>
</ul>

{% for tag in site.tags %}

### Tag: {{ tag[0] }} {#{{tag[0]}}}

<ul>
  {% for post in tag[1] %}
    <li>
      <a href="{{ post.url }}">{{ post.title }}</a> - {{ post.date | date: "%b %d, %Y" }}
    </li>
  {% endfor %}
</ul>

{% endfor %}

### Untagged {#untagged}

<ul>
  {% for post in site.posts %}
    {% if post.tags.size == 0 %}
      <li>
        <a href="{{ post.url }}">{{ post.title }}</a> - {{ post.date | date: "%b %d, %Y" }}
      </li>
    {% endif %}
  {% endfor %}
</ul>
