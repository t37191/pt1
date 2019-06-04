package com.example.paper_proj;

import com.example.paper_proj.Domain.ESIndexDomain.Author;
import com.example.paper_proj.Domain.ESIndexDomain.AuthorIndex;
import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Repository.OutcomeRepository;
import com.example.paper_proj.Domain.paperClick;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.map.SingletonMap;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdemoApplication.class)
public class EsDemoApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(AuthorIndex.class);
    }

    @Test
    public void testCreateIndex2() {
        elasticsearchTemplate.createIndex(Outcome.class);
    }

    @Test
    public void analyserTest() throws IOException, JSONException {
        String word = "nature of sports life";
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title",word).analyzer("standard");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("aminer");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
//        JSONObject jsonObject = JSONObject.fromObject(searchHits);
        JSONArray jsonArray = new JSONArray();
        List<Outcome> outcomes = new ArrayList<>();
        for(SearchHit hit : searchHits){
            JSONObject jsonObject = JSONObject.fromObject(hit.getSourceAsString());
            jsonArray.add(jsonObject);
        }
    }

    @Test
    public void getByTitleOrAbstr(){
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title","nature of sports").analyzer("standard");
        Iterable<Outcome> outputs = outcomeRepository.search(matchQuery);
        List<Outcome> list = Lists.newArrayList(outputs);
        Set<String> set = new HashSet<>();
        for(Outcome outcome:list){
            set.add(outcome.getId());
        }
        List<Outcome> output = new ArrayList<>();
        for(Object id : set.toArray()){
            output.add(outcomeRepository.findById(id.toString()).get());
        }
    }

    @Test
    public void jsonTest(){
        List<String> list = new ArrayList<>();
        list.add("{1}");
        list.add("{2}");
        System.out.println(list.toString());
    }

    @Test
    public void update() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
        String id = "53e9aea4b7602d97038c60be";
        QueryBuilder matchQuery = QueryBuilders.matchPhraseQuery("id",id);
        UpdateRequest updateRequest = new UpdateRequest("aminer","_doc","RtSiEGsBBvSgxBtLWRon");
        Map<String , Object> parameters = new SingletonMap("count",1);
        Script inline = new Script(ScriptType.INLINE, "painless", "ctx._source.cnt += params.count", parameters);
        updateRequest.script(inline);
        UpdateResponse response = client.update(updateRequest,RequestOptions.DEFAULT);
        GetResult getResult = response.getGetResult();
        SearchRequest searchRequest = new SearchRequest("aminer");
//        sourceBuilder.query(matchQuery);
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("aminer");
//        searchRequest.source(sourceBuilder);
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        SearchHits hits = searchResponse.getHits();
//        SearchHit[] searchHits = hits.getHits();
//        Map source = searchHits[0].getSourceAsMap();
//        JSONObject jsonObject = new JSONObject();
//        String paper_id = (String)source.get("id");
//        jsonObject.put("id",paper_id);
//        jsonObject.put("title",(String)source.get("title"));
//        jsonObject.put("abstr",(String)source.get("abstr"));
//        jsonObject.put("authors",(List<Author>)source.get("authors"));
//        jsonObject.put("keywords",(List<String>)source.get("keywords"));
//        jsonObject.put("pdf",(String)source.get("pdf"));
//        jsonObject.put("year",(Integer)source.get("year"));
//        if(clickRepository.existsByPaperid(paper_id)){
//            Integer cnt = clickRepository.findByPaperid(paper_id).getClickcnt();
//            paperClick paper = clickRepository.findByPaperid(paper_id);
//            if(flag) {
//                jsonObject.put("cnt", cnt + 1);
//                paper.setClickcnt(cnt + 1);
//                clickRepository.save(paper);
//            }
//            else {
//                jsonObject.put("cnt", cnt);
//            }
//        }
//        else{
//            jsonObject.put("cnt",1);
//            paperClick paper = new paperClick();
//            paper.setClickcnt(1);
//            paper.setPaperid(paper_id);
//            clickRepository.save(paper);
//        }
    }
}
