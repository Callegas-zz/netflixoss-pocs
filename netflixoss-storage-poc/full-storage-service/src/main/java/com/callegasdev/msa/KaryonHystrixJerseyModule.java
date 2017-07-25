package com.callegasdev.msa;

import io.netty.buffer.ByteBuf;
import netflix.karyon.transport.http.KaryonHttpModule;

public abstract class KaryonHystrixJerseyModule extends KaryonHttpModule<ByteBuf, ByteBuf> {

    public KaryonHystrixJerseyModule() {
        super("KaryonHystrixJerseyModule", ByteBuf.class, ByteBuf.class);
    }

    protected KaryonHystrixJerseyModule(String moduleName) {
        super(moduleName, ByteBuf.class, ByteBuf.class);
    }

    @Override
    protected void configure() {
        bindRouter().to(JerseyHystrixBasedRouter.class);
        super.configure();
    }
}
