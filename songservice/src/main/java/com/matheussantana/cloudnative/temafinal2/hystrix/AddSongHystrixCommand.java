package com.matheussantana.cloudnative.temafinal2.hystrix;

import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.repository.SongRepository;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

import java.util.Optional;

public class AddSongHystrixCommand extends HystrixObservableCommand<Optional<Song>> {

    private SongRepository songRepository;
    private final Song song;

    public AddSongHystrixCommand(Song song, SongRepository songRepository) {
        super(HystrixCommandGroupKey.Factory.asKey("Song"));
        this.songRepository = songRepository;
        this.song = song;
        HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(true);
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000);
    }

    @Override
    protected Observable construct() {
        return Observable.create(new Observable.OnSubscribe<Optional<Song>>() {
            @Override
            public void call(Subscriber<? super Optional<Song>> subscriber) {
                try{
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(Optional.of(songRepository.save(song)));
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
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
