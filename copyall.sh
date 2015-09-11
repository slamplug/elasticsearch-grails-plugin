#!/bin/bash
cp -rf ../elasticsearch-grails-plugin-2.x/src/groovy/* src/main/groovy
cp -rf ../elasticsearch-grails-plugin-2.x/src/java/* src/main/groovy
cp -rf ../elasticsearch-grails-plugin-2.x/grails-app/* grails-app
mkdir -p src/main/groovy/elasticsearch
cp -rf ../elasticsearch-grails-plugin-2.x/ElasticsearchGrailsPlugin.groovy src/main/groovy/elasticsearch
# then the tests
cp -rf ../elasticsearch-grails-plugin-2.x/test/unit/* src/test/groovy
mkdir -p src/integration-test/groovy
cp -rf ../elasticsearch-grails-plugin-2.x/test/integration/* src/integration-test/groovy
# then templates / other resources
cp -rf ../elasticsearch-grails-plugin-2.x/src/templates/* src/main/templates