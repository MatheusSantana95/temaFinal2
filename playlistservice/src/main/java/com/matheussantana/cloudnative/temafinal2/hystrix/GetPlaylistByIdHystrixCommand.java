package com.matheussantana.cloudnative.temafinal2.hystrix;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

import java.util.Optional;

public class GetPlaylistByIdHystrixCommand extends HystrixObservableCommand<Optional<Playlist>> {

    private PlaylistRepository playlistRepository;
    private final int playlistId;

    public GetPlaylistByIdHystrixCommand(int playlistId, PlaylistRepository playlistRepository) {
        super(HystrixCommandGroupKey.Factory.asKey("Playlist"));
        this.playlistRepository = playlistRepository;
        this.playlistId = playlistId;
        HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true);
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000);
    }

    @Override
    protected Observable construct() {
        return Observable.create(new Observable.OnSubscribe<Optional<Playlist>>() {
            @Override
            public void call(Subscriber<? super Optional<Playlist>> subscriber) {
                try{
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(playlistRepository.findById(playlistId));
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable<Optional<Playlist>> resumeWithFallback(){
        return Observable.just(Optional.empty());
    }
}
