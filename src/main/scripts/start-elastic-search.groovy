description "Starts the Elasticsearch service", "grails start-elastic-search"

def elasticSearchHome = System.getenv("ELASTIC_SEARCH_HOME")
if (!elasticSearchHome){
    println("Cannot start ElasticSearch service. Please set the env variable 'ELASTIC_SEARCH_HOME'.")
    //TODO should exit here
} else {
    println("Starting ElasticSearch service.")
    ant.exec(executable:"${elasticSearchHome}/bin/elasticsearch",osfamily:"unix") {
        arg value:'-f'
    }
    ant.exec(executable:"${elasticSearchHome}/bin/elasticsearch.bat",osfamily:"windows") {
        arg value:'-f'
    }
}