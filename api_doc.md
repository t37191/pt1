* 这玩意不一定哪天哪行就变了（我尽量避免），关注diff文件吧。

* 所有时间类型全部返回 xxxx-xx-xx-xx-xx-xx 吧，年月日时分秒，类似1999-08-12-23-14-15，没有的设成00，年4位其余两位

* /bbs_topic

      "topic_id",
      "user_id",
      "topic_name",
      "user_name",
      "reply_number",
      "vote_number"

eg:

/bbs_topic   返回这个的数组

/bbs_topic/1 根据tipic_id查询单条

* /bbs_reply

      "reply_id"，
      "user_id",
      "user_name",
      "r_topic_id",
      "reply_content",
      "r_reply_id",
      "reply_time",
      "r_reply_user_name",
      "r_reply_user_id"

结构应该和数据库一样，但是格式详见https://github.com/typicode/json-server，有一些查询参数，类似下面这种

/bbs_reply?r_topic_id=1&r_reply_id=1&_sort=reply_time&_limit=10

这里应该会用到_sort, _limit, _start, 话说这些玩意可能莫名其妙的地方就用到了，最好有什么方法弄个函数处理掉。不行再说吧

除了本条评论的用户信息，还需要知道回复的评论的用户信息

* /bbs_reply/count

"reply_number"

为了用/bbs_reply/count?r_reply_id=1 这种东西获取回复数量。如果觉得这种方法太扯麻烦联系我。。。

* /paper

      "id",
      "title",
      "author": [
        {
          "author_id",
          "author_name"
        }
      ],
      "abstract",
      "keyword": [
        {
          "keyword"
        }
      ]
      
author和keyword都是数组，因为可能不止一个。

* /search

      "outcomeId",
      "title",
      "time",
      "author": [
            {
                  "author_id",
                  "author_name"
            }
      ],
      "source"
      
* /search/count

这两个除了上面三个，还应该有搜索类型_type还有搜索内容_content

eg：/search/count?type=fulltext&_contenet=asadsasdad&_start=0&_limit=10

搜索类型先默认全文检索吧

有来源就写一下，没有就全为空就行了。。

* /user/login

      "token",
      "user_id",
      "user_name"
      
* /user/signup-user

      "token",
      "user_id",
      "user_name"
