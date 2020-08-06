package com.ss.server;

import com.google.common.collect.Sets;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ServerDemo {

    public static Set<SocketChannel> socketChannels = Sets.newHashSet() ;

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open() ;
        // 是否需要阻塞
        serverSocketChannel.configureBlocking( false ) ;
        // 绑定本地端口
        serverSocketChannel.bind( new InetSocketAddress(8080) ) ;

        while (true){
            Thread.currentThread().sleep(1000) ;
            // 获取接入连接
            SocketChannel socketChannel = serverSocketChannel.accept() ;
            if( socketChannel==null ){
                continue;
            }
            socketChannel.configureBlocking( false ) ;

            // 添加 socketChannel
            if( !socketChannels.contains( socketChannel ) ){
                socketChannels.add( socketChannel ) ;
            }

            System.out.println("连接数量->"+ socketChannels.size()) ;

            Set<SocketChannel> removeSocketChannels = Sets.newHashSet() ;
            // 读取内容
            for (SocketChannel channel : socketChannels) {
                ByteBuffer byteBuffer = ByteBuffer.allocate( 4096 ) ;
                int num = channel.read( byteBuffer ) ;
                System.out.println("读取内容长度->"+num);
                if( num > 0 ){
                    byteBuffer.flip() ;
                    byte[] bytes = new byte[ byteBuffer.limit() ] ;
                    byteBuffer.get( bytes ) ;
                    String outStr = new String( bytes ) ;
                    System.out.println( channel.socket().getPort() + " : " + outStr );
                }else if( num == -1 ){
                    System.out.println("移除当前连接->"+channel.socket().getPort());
                    // 如果已经读取结束，则移除
                    removeSocketChannels.add( channel ) ;
                }
            }
            // 移除 channel
            socketChannels.removeAll( removeSocketChannels ) ;
        }

    }

}
