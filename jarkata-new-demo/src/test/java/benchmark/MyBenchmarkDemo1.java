package benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
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
public class MyBenchmarkDemo1 {

    @Param({"10000"})
    private int nStrings;

    private static ConcurrentHashMap<String, String> holdMap;

    private static String[] holdStrings;

    @Setup(Level.Iteration)
    public void initMethod() {
        holdStrings = new String[nStrings];
        for (int i = 0; i < nStrings; i++) {
            holdStrings[i] = new String("String to intern " + i);;
        }
        holdMap = new ConcurrentHashMap<>();
    }

    @Benchmark
    public void testIntern(Blackhole bh) {
        for (int i = 0; i < nStrings; i++) {
            String t = holdStrings[i].intern();
            bh.consume(t);
        }
    }

    @Benchmark
    public void testInternWithMap(Blackhole bh) {
        for (int i = 0; i < nStrings; i++) {
            String t = holdMap.putIfAbsent(holdStrings[i], holdStrings[i]);
            bh.consume(t);
        }
    }
}
