package org.grails.plugins.elasticsearch

import grails.converters.JSON
import grails.test.mixin.integration.Integration
import spock.lang.Specification
import grails.transaction.Rollback
import grails.util.GrailsNameUtils
import org.grails.core.DefaultGrailsDomainClass
import grails.core.GrailsApplication
import grails.core.GrailsDomainClass
import org.grails.web.json.JSONObject
import org.elasticsearch.action.admin.cluster.state.ClusterStateRequestBuilder
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.client.AdminClient
import org.elasticsearch.client.ClusterAdminClient
import org.elasticsearch.cluster.ClusterState
import org.elasticsearch.cluster.metadata.IndexMetaData
import org.elasticsearch.cluster.metadata.MappingMetaData
import org.elasticsearch.common.unit.DistanceUnit
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.FilterBuilder
import org.elasticsearch.index.query.FilterBuilders
import org.elasticsearch.search.sort.FieldSortBuilder
import org.elasticsearch.search.sort.SortBuilders
import org.elasticsearch.search.sort.SortOrder
import test.*

@Integration
@Rollback
class AttachmentMappingSpecification extends Specification {

    ElasticSearchService elasticSearchService
    ElasticSearchAdminService elasticSearchAdminService
    ElasticSearchHelper elasticSearchHelper
    GrailsApplication grailsApplication

    void 'Index a File object'() {
        given:
        def contents = "It was the best of times, it was the worst of times"
        def file = new test.File(filename: 'myTestFile.txt', 
                                 attachment:contents.bytes.encodeBase64().toString())
        file.save(failOnError: true)

        when:
        elasticSearchAdminService.refresh() // Ensure the latest operations have been exposed on the ES instance

        and:
        elasticSearchService.search('best', [indices: File, types: File]).total == 1

        then:
        elasticSearchService.unindex(file)
        elasticSearchAdminService.refresh()

        and:
        elasticSearchService.search('best', [indices: File, types: File]).total == 0
    }
}
