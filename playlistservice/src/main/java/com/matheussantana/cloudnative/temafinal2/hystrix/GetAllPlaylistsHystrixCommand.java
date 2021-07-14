package com.matheussantana.cloudnative.temafinal2.hystrix;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;


public class GetAllPlaylistsHystrixCommand extends HystrixObservableCommand<Iterable<Playlist>> {

    private PlaylistRepository playlistRepository;

    public GetAllPlaylistsHystrixCommand(PlaylistRepository playlistRepository) {
        super(HystrixCommandGroupKey.Factory.asKey("Playlist"));
        this.playlistRepository = playlistRepository;
        HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true);
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000);
    }

    @Override
    protected Observable construct() {
        return Observable.create(new Observable.OnSubscribe<Iterable<Playlist>>() {
            @Override
            public void call(Subscriber<? super Iterable<Playlist>> subscriber) {
                try{
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(playlistRepository.findAll());
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable<Iterable<Playlist>> resumeWithFallback(){
        return Observable.empty();
    }
}
