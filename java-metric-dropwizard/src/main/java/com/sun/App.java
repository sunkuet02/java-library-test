package com.sun;

import com.codahale.metrics.*;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 */
class GaugeFactory {
    private int val = 0;

    public GaugeFactory (MetricRegistry metrics, String name) {
        this.val = 0;

        metrics.register(MetricRegistry.name(GaugeFactory.class, name, "size"),
            new Gauge<Integer>() {
                public Integer getValue () {
                    return Integer.valueOf(val);
                }
            }
        );
    }

    public void SetValue(int n){
        this.val = n;
    }

    public int GetValue(){
        return this.val ;
    }
}

public class App {

    static MetricRegistry metrics = new MetricRegistry();

    public static void main (String[] args) {
        new App();

    }

    public App () {
        for (int i = 0; i < 7; i++) {
            Scanner reader = new Scanner(System.in);
            String name = reader.next();

            testGauge(name);
        }
    }

    public void testCounter (String name) {
        Counter counter = metrics.counter(name);
        counter.inc(1);
        System.out.println(counter.getCount());
    }

    public void testMeter (String name) {
        Meter meter = metrics.meter(name);
        meter.mark();
        System.out.println(meter.getOneMinuteRate());
    }

    public void testGauge (String name) {
        System.out.println( new GaugeFactory(metrics, name) );

       // System.out.println(gauge. + "   ." + queueManager.toString());
    }

}
