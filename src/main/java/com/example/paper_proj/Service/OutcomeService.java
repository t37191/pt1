package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.ESIndexDomain.Author;
import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Repository.OutcomeRepository;
import com.google.common.collect.Lists;
import org.apache.http.HttpHost;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    private  RestHighLevelClient client = null;

    public OutcomeService(){
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
    }
    //根据标题和摘要查找论文
    public Tuple<String,Integer> getByTitleOrAbstr(String word) throws IOException, JSONException {
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title","nature of sports").analyzer("standard");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("aminer");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        JSONArray jsonArray = new JSONArray();
        for(SearchHit hit : searchHits){
            Map<String,Object> sourceAsMap = hit.getSourceAsMap();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",(String)sourceAsMap.get("id"));
            jsonObject.put("title",(String) sourceAsMap.get("title"));
            jsonObject.put("abstr",(String)sourceAsMap.get("abstr"));
            jsonObject.put("authors",(List<Author>)sourceAsMap.get("authors"));
            jsonObject.put("keywords",(List<String>)sourceAsMap.get("keywords"));
            jsonObject.put("pdf",(String)sourceAsMap.get("pdf"));
            jsonObject.put("year",(Integer)sourceAsMap.get("year"));
            jsonObject.put("url",(List<String>)sourceAsMap.get("url"));
            jsonObject.put("doi",(String)sourceAsMap.get("doi"));
        }
        return null;
    }
    //根据关键字查找论文
    public List<Outcome> getByKeyWords(String keywords){
        return outcomeRepository.findAllByKeywords(keywords);
    }
    //根据作者查找论文
    public List<Outcome> getByAuthors(String author){
        QueryBuilder authorsQuery = QueryBuilders.nestedQuery("authors",
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("authors.name", author)),
                ScoreMode.Total);

        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(authorsQuery);

        Iterable<Outcome> output = outcomeRepository.search(queryBuilder);
        List<Outcome> list = Lists.newArrayList(output);
        return list;
    }

    //获取单片论文信息
    public Outcome getOutcome(String id){
        return outcomeRepository.findById(id).get();
    }

    //增加论文
    public Outcome addOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

    //修改论文
    public Outcome updateOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

    //删除论文
    public void deleteOutcome(String id){
        outcomeRepository.deleteById(id);
    }
    /*
    //搜索
    public List<Outcome> serarchOutcome(String words){
        List<Outcome> l1 = outcomeRepository.findAllByTitleContaining(words);
        l1.addAll(outcomeRepository.findAllByAbstrContaining(words));
        Set<Integer> ids=new TreeSet<>();
        for(Outcome outcome :l1){
            ids.add(outcome.getOutcome_id());
        }
        List<Integer> id=new ArrayList<>(ids);
        Collections.reverse(id);
        l1.clear();
        for(Integer i :id){
            l1.add(outcomeRepository.findById(i).get());
        }
        return l1;
    }

    //搜索关键字
    public List<Outcome> seearchOutcomeByKeyword(String keyword){
        String[] sets=keyword.split(" ");
        List<Outcome> outcomes=new ArrayList<>();
        Set<Integer> ids=new TreeSet<>();
        for(String key :sets){
            List<Keyword> keys=keywordRepository.findByKeyword(key);
            for(Keyword k:keys){
                ids.add(k.getOutcome_id());
            }
        }
        List<Integer> tst=new ArrayList<>(ids);
        Collections.reverse(tst);
        for(Integer i:tst){
            outcomes.add(outcomeRepository.findById(i).get());
        }
        return outcomes;
    }

    //查询所有
    public List<Outcome> getAll(){
        return outcomeRepository.findAll();
    }
    public Keyword addKeyword(Keyword keyword){return keywordRepository.save(keyword);}*/
}
