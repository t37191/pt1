* 这玩意不一定哪天哪行就变了（我尽量避免），关注diff文件吧。

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
      "reply_time"

结构应该和数据库一样，但是格式详见https://github.com/typicode/json-server，有一些查询参数，类似下面这种

/bbs_reply?r_topic_id=1&r_reply_id=1&_sort=reply_time&_limit=10

这里应该会用到_sort, _limit, _start，话说这些玩意可能莫名其妙的地方就用到了，最好有什么方法弄个函数处理掉。不行再说吧

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