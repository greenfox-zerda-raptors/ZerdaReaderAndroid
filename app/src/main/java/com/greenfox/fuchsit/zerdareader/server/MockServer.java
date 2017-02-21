package com.greenfox.fuchsit.zerdareader.server;

import android.support.annotation.NonNull;

import com.greenfox.fuchsit.zerdareader.model.FavoriteRequest;
import com.greenfox.fuchsit.zerdareader.model.FavoriteResponse;
import com.greenfox.fuchsit.zerdareader.model.LoginRequest;
import com.greenfox.fuchsit.zerdareader.model.NewsItem;
import com.greenfox.fuchsit.zerdareader.model.UpdateRequest;
import com.greenfox.fuchsit.zerdareader.model.UserResponse;
import com.greenfox.fuchsit.zerdareader.rest.ReaderApiInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Zsuzska on 2017. 01. 20..
 */

public class MockServer implements ReaderApiInterface {

    @Override
    public MockCall<ArrayList<NewsItem>> getNewsItems(String token) {
        return new MockCall<ArrayList<NewsItem>>() {
            @Override
            public void enqueue(Callback<ArrayList<NewsItem>> callback) {
                ArrayList<NewsItem> newsItems = null;
                try {
                    newsItems = addNewsItems();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Response<ArrayList<NewsItem>> r = Response.success(newsItems);
                callback.onResponse(this, r);
            }
        };
    }

    @Override
    public MockCall<ArrayList<NewsItem>> getFavouriteNewsItems(String token) {
        return new MockCall<ArrayList<NewsItem>>() {
            @Override
            public void enqueue(Callback<ArrayList<NewsItem>> callback) {
                ArrayList<NewsItem> favoriteNewsItems = null;
                try {
                    favoriteNewsItems = addFavoriteNewsItems();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Response<ArrayList<NewsItem>> r = Response.success(favoriteNewsItems);
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
    public void updateOpened(long id, String token, UpdateRequest updateRequest) {

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
        if (loginRequest.getEmail().equals("admin") && loginRequest.getPassword().equals("fuchsit")) {
            userResponse = new UserResponse("success");
        } else {
            userResponse = new UserResponse("fail");
        }
        return userResponse;
    }


    @NonNull
    private ArrayList<NewsItem> addNewsItems() throws ParseException {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        int d1 = 1487079219;
        newsItems.add(new NewsItem(1, "Pofont nem, maximum orrpöckölést kap az európai elit a francia elnökválasztáson",
                "Candy canes danish marzipan cookie caramels jelly beans. Sweet roll lemon drops marzipan cake jelly soufflé tart halvah jujubes. Jelly jelly gummies. Sweet roll pie topping croissant topping gingerbread chocolate cake. Sweet roll macaroon candy canes tart caramels. Tart gummies carrot cake muffin cupcake caramels chocolate bar. Jelly sugar plum chocolate macaroon candy croissant. Soufflé icing apple pie. Dragée fruitcake tart lollipop dessert cupcake lemon drops jelly beans macaroon. Caramels jelly-o soufflé sweet roll halvah cheesecake bear claw bear claw. Candy canes cotton candy cheesecake. Donut cupcake marshmallow. Caramels bonbon sweet.",
                d1, "Fox Crunch", false, false));
        newsItems.add(new NewsItem(2, "Schóbert Norbertnek köszönhetően egy ország lett kémiaszakértő", "Marzipan cotton candy marzipan pie lemon drops. Sweet roll soufflé biscuit bear claw ice cream cotton candy candy canes. Pastry jujubes sweet roll muffin cookie sweet roll muffin. Cotton candy danish caramels apple pie pastry cake. Wafer brownie oat cake tart chocolate cake. Marzipan jujubes cake soufflé. Jujubes sweet fruitcake gingerbread sesame snaps wafer. Bonbon liquorice muffin cake. Gingerbread tart chupa chups candy canes cheesecake cotton candy halvah jelly-o chocolate cake. Jelly jelly muffin soufflé jelly pastry topping. Candy halvah gummies. Danish cake biscuit cake tiramisu.",
                d1, "Fox Brunch", false, false));
        newsItems.add(new NewsItem(3, "Title 3", "Pastry danish caramels lollipop gummi bears muffin. Dragée caramels marshmallow pudding bonbon. Sweet roll dessert liquorice bear claw oat cake carrot cake. Icing sweet roll chupa chups wafer. Sugar plum ice cream gingerbread. Tart gummies tootsie roll pie chocolate. Chupa chups jujubes chocolate bar ice cream sugar plum gingerbread jujubes brownie chocolate cake. Carrot cake jujubes carrot cake gummi bears donut apple pie. Cupcake tart chocolate bar bear claw brownie chupa chups chupa chups. Chocolate gummi bears liquorice cake halvah jelly-o marshmallow oat cake candy canes. Topping ice cream candy canes powder wafer sweet roll cupcake. Brownie candy sugar plum. Tart gummies oat cake pastry jelly-o pie cake fruitcake topping. Pastry marshmallow biscuit croissant cake.",
                d1, "Fox Crunch", true, false));
        newsItems.add(new NewsItem(4, "Title 4", "Icing halvah apple pie tiramisu cake macaroon oat cake. Ice cream dragée croissant marzipan. Caramels icing marshmallow ice cream. Wafer candy candy marzipan caramels. Sweet roll liquorice chupa chups marshmallow brownie cotton candy tiramisu. Donut pastry dragée candy canes chocolate cake gingerbread sweet roll ice cream apple pie. Cake pastry liquorice toffee toffee jelly beans danish. Muffin jujubes bonbon marshmallow lollipop. Liquorice croissant icing ice cream. Cake jujubes tootsie roll sesame snaps fruitcake macaroon. Lemon drops macaroon candy cotton candy bear claw icing tart icing. Pastry tiramisu halvah dragée candy tart tiramisu tart cupcake.",
                d1, "Fox Lunch", true, true));
        newsItems.add(new NewsItem(5, "Title 5", "Pastry candy canes oat cake icing sugar plum jelly-o biscuit danish. Dessert icing cookie bear claw jelly. Carrot cake icing sweet. Croissant jelly-o cheesecake biscuit dessert caramels wafer dragée tootsie roll. Lollipop pastry soufflé. Wafer cotton candy caramels apple pie sugar plum pie sesame snaps candy. Jelly-o tootsie roll ice cream croissant dessert. Jujubes cheesecake toffee pudding. Carrot cake bear claw gingerbread jelly chupa chups. Candy canes jelly beans candy canes soufflé. Liquorice donut donut. Sweet apple pie carrot cake pastry biscuit marshmallow.",
                d1, "Fox Crunch", false, true));
        return newsItems;
    }

    private ArrayList<NewsItem> addFavoriteNewsItems() throws ParseException {
        ArrayList<NewsItem> newsItems = addNewsItems();
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

    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}


