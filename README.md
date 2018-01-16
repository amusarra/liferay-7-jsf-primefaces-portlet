# Liferay 7: How to access OSGi services from the JSF portlet
This project refers to the article [Liferay 7: How to access OSGi services from the JSF portlet](https://www.dontesta.it/en/2018/01/14/liferay-7-how-to-access-osgi-services-from-jsf-portlet/), published on my blog.

## Quickstart

```
$ git clone https://github.com/amusarra/liferay-7-jsf-primefaces-portlet.git
$ cd liferay-7-jsf-primefaces-portlet
$ git submodule init
$ git submodule update
$ cd sample-temperature-service-builder
$ gradle install
$ cd ..
$ gradle build
$ find . -type f \( -name "it*.jar*" -o -name "*.war" \) -exec cp {} $LIFERAY_HOME/deploy/ \;
```
