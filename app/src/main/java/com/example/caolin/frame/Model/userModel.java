package com.example.caolin.frame.Model;


public class userModel {

    public static final String LIMIT = "10"; //分页，limit的默认值10项
    private IQueryCountItemListener queryCountItemListener;
 ;
    //    public void QueryMoreItem(int page) {
//        Map<String, String> queryMap = new HashMap<>();
//        queryMap.put("order", "-updatedAt");
//        queryMap.put("limit", LIMIT);
//        String skip = String.valueOf(page * 10);
//        queryMap.put("skip", skip);
//        RxFactory.getPublicServiceInstance(null)
//                .queryActivity(queryMap)
//                .observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(Activitys -> queryMoreItemListener.onQueryMoreItemSuccess(Activitys.getElements()), throwable -> queryMoreItemListener.onQueryMoreItemFailure());
//    }
    public void setQueryCountItemListener(IQueryCountItemListener queryCountItemListener) {
        this.queryCountItemListener = queryCountItemListener;
    }

    public interface IQueryCountItemListener {
        void onQueryCountItemSuccess(String count);

        void onQueryCountItemFailure();
    }
}
