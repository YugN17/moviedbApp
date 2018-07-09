package com.ia.data.repository.datasource.cloud;

import com.ia.data.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiDataSourceInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl httpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();

        Request.Builder requestBuilder = original.newBuilder().url(httpUrl);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
