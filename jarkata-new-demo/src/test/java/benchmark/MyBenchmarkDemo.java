package benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Fork(5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 7)
public class MyBenchmarkDemo {

    @Param({"10000"})
    private int nStrings;

    @Benchmark
    public void testIntern(Blackhole bh) {
        for (int i = 0; i < nStrings; i++) {
            String s = new String("String to intern " + i);
            String t = s.intern();
            bh.consume(t);
        }
    }

    private static ConcurrentHashMap<String, String> holdMap = new ConcurrentHashMap<>();

    @Benchmark
    public void testInternWithMap(Blackhole bh) {
        for (int i = 0; i < nStrings; i++) {
            String s = new String("String to intern " + i);
            String t = holdMap.putIfAbsent(s, s);
            bh.consume(t);
        }
    }
}
