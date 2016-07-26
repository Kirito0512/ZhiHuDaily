# ZhiHuDaily
参考了知乎日报App，在MainActivity中使用ListView以及SwipeRefreshLayout实现了可以下拉刷新的新闻列表。
使用AsyncTask封装了两种网络请求（分别是获取日报的列表和每篇日报的具体内容），使用GSON解析服务器返回的JSON数据。
其中，在自定义的adapter中使用了Glide解析出日报列表的图片。
另外，运用了sqlite数据库，实现了日报本地收藏和取消收藏的功能。
![image](https://github.com/Kirito0512/ZhiHuDaily/blob/master/zhihu.gif)
