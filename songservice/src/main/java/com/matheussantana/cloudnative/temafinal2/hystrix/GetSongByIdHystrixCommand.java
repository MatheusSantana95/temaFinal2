package com.matheussantana.cloudnative.temafinal2.hystrix;

import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.repository.SongRepository;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

import java.util.Optional;

public class GetSongByIdHystrixCommand extends HystrixObservableCommand<Optional<Song>> {

    private SongRepository songRepository;
    private final int songId;

    public GetSongByIdHystrixCommand(int songId, SongRepository songRepository) {
        super(HystrixCommandGroupKey.Factory.asKey("song"));
        this.songRepository = songRepository;
        this.songId = songId;
        HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true);
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000);
    }

    @Override
    protected Observable<Optional<Song>> construct() {
        return Observable.create(new Observable.OnSubscribe<Optional<Song>>() {
            @Override
            public void call(Subscriber<? super Optional<Song>> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(songRepository.findById(songId));
                        subscriber.onCompleted();
                    }
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    protected Observable<Optional<Song>> resumeWithFallback(){
        return Observable.just(Optional.empty());
    }
}
