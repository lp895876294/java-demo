package com.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.Future;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) throws Exception {
        CommandHelloWorld helloWorld = new CommandHelloWorld("execute") ;

        System.out.println( helloWorld.execute() );

        helloWorld = new CommandHelloWorld("future") ;
        Future<String> result = helloWorld.queue() ;

        System.out.println( result.get() );


        helloWorld = new CommandHelloWorld("future") ;
        Observable<String> observable = helloWorld.observe() ;


    }
}