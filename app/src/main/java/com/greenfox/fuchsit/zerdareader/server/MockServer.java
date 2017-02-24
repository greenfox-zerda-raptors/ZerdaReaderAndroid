package com.greenfox.fuchsit.zerdareader.server;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.greenfox.fuchsit.zerdareader.model.AddSubsRequest;
import com.greenfox.fuchsit.zerdareader.model.AddSubsResponse;
import com.greenfox.fuchsit.zerdareader.ZerdaReaderApp;
import com.greenfox.fuchsit.zerdareader.model.FavoriteRequest;
import com.greenfox.fuchsit.zerdareader.model.FavoriteResponse;
import com.greenfox.fuchsit.zerdareader.model.FeedResponse;
import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteRequest;
import com.greenfox.fuchsit.zerdareader.model.SubsDeleteResponse;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionModel;
import com.greenfox.fuchsit.zerdareader.model.SubscriptionResponse;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import dagger.Module;
import io.kimo.lib.faker.component.number.NumberComponent;
import io.kimo.lib.faker.component.text.LoremComponent;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 20..
 */
@Module
public class MockServer implements ReaderApiInterface {
    ArrayList<SubscriptionModel> subscriptionModels = new ArrayList<>();

    LoremComponent loremComponent;
    NumberComponent numberComponent;
    ArrayList<NewsItem> newsItems;

    public MockServer() {
        loremComponent = new LoremComponent(ZerdaReaderApp.getApplication());
        numberComponent = new NumberComponent(ZerdaReaderApp.getApplication());
        newsItems = addNewsItems();
    }

    @Override
    public MockCall<FeedResponse> getNewsItems(String token) {
        return new MockCall<FeedResponse>() {
            @Override
            public void enqueue(Callback<FeedResponse> callback) {
                Response<FeedResponse> r = Response.success(new FeedResponse(newsItems));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public MockCall<FeedResponse> getFavouriteNewsItems(String token) {
        return new MockCall<FeedResponse>() {
            @Override
            public void enqueue(Callback<FeedResponse> callback) {
                Response<FeedResponse> r = Response.success(new FeedResponse(addFavoriteNewsItems()));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public MockCall<FavoriteResponse> createFavoriteItem(String token, final FavoriteRequest favoriteRequest) {
        return new MockCall<FavoriteResponse>() {
            @Override
            public void enqueue(Callback<FavoriteResponse> callback) {
                Response<FavoriteResponse> r = Response.success(getFavoriteResponse());
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<FavoriteResponse> deleteFavoriteItem(String token, FavoriteRequest favoriteRequest) {
        return new MockCall<FavoriteResponse>() {
            @Override
            public void enqueue(Callback<FavoriteResponse> callback) {
                Response<FavoriteResponse> r = Response.success(getFavoriteResponse());
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public MockCall<UserResponse> loginUser(final LoginRequest loginRequest) {
        return new MockCall<UserResponse>() {
            @Override
            public void enqueue(Callback<UserResponse> callback) {

                Response<UserResponse> r = Response.success(checkUser(loginRequest));
                callback.onResponse(this, r);
            }
        };
    }


    @Override
    public MockCall<okhttp3.ResponseBody> updateOpened(long id, String token, UpdateRequest updateRequest) {
        return new MockCall<ResponseBody>() {
            @Override
            public void enqueue(Callback<ResponseBody> callback) {

            }
        };
    }

    public MockCall<UserResponse> signUpUser(final LoginRequest loginRequest) {
        return new MockCall<UserResponse>() {
            @Override
            public void enqueue(Callback<UserResponse> callback) {
                Response<UserResponse> r = Response.success(checkUsername(loginRequest));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<SubscriptionResponse> getSubscriptions(@Query("token") String token) {
        return new MockCall<SubscriptionResponse>() {
            @Override
            public void enqueue(Callback<SubscriptionResponse> callback) {
                ArrayList<SubscriptionModel> subscriptionModels = null;
                try {
                    subscriptionModels = addSubscriptions();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Response<SubscriptionResponse> r = Response.success(new SubscriptionResponse(subscriptionModels));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<AddSubsResponse> addNewSubscription(String token, final AddSubsRequest addSubsRequest) {
        return new MockCall<AddSubsResponse>() {
            @Override
            public void enqueue(Callback<AddSubsResponse> callback) {

                Response<AddSubsResponse> r = Response.success(checkSubsResponse(addSubsRequest));
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public Call<SubsDeleteResponse> deleteSubscription(long id, final SubsDeleteRequest subsDeleteRequest, String token) {
        return new MockCall<SubsDeleteResponse>() {
            @Override
            public void enqueue(Callback<SubsDeleteResponse> callback) {
                Response<SubsDeleteResponse> r = Response.success(checkDelResponse(subsDeleteRequest));
                callback.onResponse(this, r);
            }
        };
    }


    private UserResponse checkUsername(LoginRequest loginRequest) {
        UserResponse userResponse;
        if (loginRequest.getEmail().equals("admin")) {
            userResponse = new UserResponse("fail");
        } else {
            userResponse = new UserResponse("success");
        }
        return userResponse;
    }

    private UserResponse checkUser(LoginRequest loginRequest) {
        UserResponse userResponse;
        if (loginRequest.getEmail().equals("admin@admin.com") && loginRequest.getPassword().equals("fuchsit")) {
            userResponse = new UserResponse("success");
        } else {
            userResponse = new UserResponse("fail");
        }
        return userResponse;
    }

    private AddSubsResponse checkSubsResponse(AddSubsRequest addSubsRequest) {
        AddSubsResponse addSubsResponse;
        if (addSubsRequest.getUrl().equals("blabla.hu")) {
            addSubsResponse = new AddSubsResponse("fail", "Failed to subscribe");
        } else {
            addSubsResponse = new AddSubsResponse("subscribed", 2587L);
        }

        return addSubsResponse;
    }

    private SubsDeleteResponse checkDelResponse(SubsDeleteRequest subsDeleteRequest) {
        SubsDeleteResponse subsDeleteResponse = new SubsDeleteResponse("success");
        return subsDeleteResponse;
    }

    @NonNull
    private ArrayList<SubscriptionModel> addSubscriptions() throws ParseException {

        subscriptionModels.add(new SubscriptionModel("www.index.hu/feed", 1L));
        subscriptionModels.add(new SubscriptionModel("www.hvg.hu/feed", 2L));
        subscriptionModels.add(new SubscriptionModel("www.origo.hu/feed", 3L));
        subscriptionModels.add(new SubscriptionModel("www.444.hu/feed", 4L));
        subscriptionModels.add(new SubscriptionModel("www.444.hu/feed", 5L));
        return subscriptionModels;
    }

    @NonNull
    private ArrayList<NewsItem> addNewsItems() {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        //kimo faker
        for (int i = 0; i < 20; i++) {
            newsItems.add(new NewsItem(i,
                    loremComponent.sentence(),
                    loremComponent.paragraphs(6),
                    System.currentTimeMillis(),
                    loremComponent.words(3),
                    getRandomBoolean(),
                    getRandomBoolean()));
        }
        return newsItems;
    }

    private ArrayList<NewsItem> addFavoriteNewsItems() {
        ArrayList<NewsItem> favoriteNewsItems = new ArrayList<>();
        for (NewsItem newsItem : newsItems) {
            if (newsItem.isFavorite()) {
                favoriteNewsItems.add(newsItem);
            }
        }
        return favoriteNewsItems;
    }

    private FavoriteResponse getFavoriteResponse() {
        String[] responses = {"success", "error_message"};
        return new FavoriteResponse(getRandom(responses));
    }

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}


