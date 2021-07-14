package com.matheussantana.cloudnative.temafinal2.hystrix;

import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.repository.SongRepository;
import com.matheussantana.cloudnative.temafinal2.service.SongService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

public class GetAllSongsHystrixCommand extends HystrixObservableCommand<Iterable<Song>> {

    private SongRepository songRepository;

    public GetAllSongsHystrixCommand(SongRepository songRepository) {
        super(HystrixCommandGroupKey.Factory.asKey("song"));
        this.songRepository = songRepository;
        HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true);
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000);
    }

    @Override
    protected Observable<Iterable<Song>> construct() {
        return Observable.create(new Observable.OnSubscribe<Iterable<Song>>() {
            @Override
            public void call(Subscriber<? super Iterable<Song>> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(songRepository.findAll());
                        subscriber.onCompleted();
                    }
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable<Iterable<Song>> resumeWithFallback(){
        return Observable.empty();
    }
}
