package com.nba.news.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by allen on 16/12/16.
 */

public class ApiManage {

   private static final class ApiHolder{
       private static final ApiManage INSTANCE = new ApiManage();
   }

    public static ApiManage getInstance(){
        return ApiHolder.INSTANCE;
    }

    public ApiManage() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "fileCache");
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        Cache cache = new Cache(httpCacheDirectory, cacheSize);
//
//        OkHttpClient client = new OkHttpClient.Builder()
////                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
////                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//                .cache(cache)
//                .build();

        topNewsServices = new Retrofit.Builder()
                .baseUrl("http://route.showapi.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(TopNewsServices.class);
    }

//    public  Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Response originalResponse = chain.proceed(chain.request());
//            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) {
//                int maxAge = 60 * 5; // 在线缓存在5分钟内可读取
//                return originalResponse.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, max-age=" + maxAge)
//                        .build();
//            } else {
//                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
//                return originalResponse.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .build();
//            }
//        }
//    };






    public TopNewsServices topNewsServices;




    public TopNewsServices getTopNewsService() {



        return topNewsServices;
    }





}
