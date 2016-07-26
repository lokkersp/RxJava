package functions

import rx.functions.*
import rx.functions.Action
import rx.functions.Function
import spock.lang.Specification

import java.util.concurrent.atomic.AtomicLong

class FunctionsSpec extends Specification {
    def g = { Action a, p->Functions.fromAction(a).call(p as Object[])}
    def u = { Function f, p-> return Functions.fromFunc(f).call(p as Object[])}
    def params = [zero:0,one:1,two:[1,2],three:[1,2,4],four:[1,2,4,8],five:[1,2,4,8,16],six:[1,2,4,8,16,32],seven:[1,2,4,8,16,32,64],eight:[1,2,4,8,16,32,64,128],nine:[1,2,4,8,16,32,64,128,256]]

    def "Testing \'From\' for function of 0"() {
        when: "Created function with zero input args"
        def Func0<Integer> f = {return 0}
        then: "Zero was returned"
        f() == params.zero
        and: "Returned value is Integer"
        f() instanceof Integer
    }

    def "Testing \'From\' for function of 1"() {
        when: "Created function with one input arg: a->a"
        def Func1<Integer,Integer> f = {a->return a}
        then: "Should return argument ${params.one}"
        params.one == u(f, params.one)
    }

    def "Testing \'From\' for function of 2"() {
        when: "Created function f(a,b) = a|b"
        def Func2<Integer,Integer,Integer> f = {a,b-> return  a | b}
        then: "Should return sum of args for ${params.two}"
        params.two.sum() == u(f,params.two)

    }
    def "Testing \'From\' for function of 3"() {
        when: "Created func f(a,b,c) = a | b | c"
        def Func3 <Integer,Integer,Integer,Integer> f = {a,b,c-> return a|b|c}
        then:"Should return true on ${params.three} args"
        params.three.sum() == u(f,params.three)
    }
    def "Testing \'From\' for function of 4"() {
        when: "Created func f(t1,t2,t3,t4) = t1 | t2 | t3 | t4"
        def Func4<Integer,Integer,Integer,Integer,Integer> f = {t1,t2,t3,t4-> return t1 | t2 | t3 | t4 }
        then: "Should return true on ${params.four}"
        params.four.sum() == u(f,params.four)
    }
    def "Testing \'From\' for function of 5"(){
        when: "Created function f(t1..t5) = t1 | t2 |..| t5"
        def Func5<Integer, Integer, Integer, Integer, Integer, Integer> f = {t1,t2,t3,t4,t5-> return t1 | t2 | t3 | t4 | t5 }
        then: "Should return true on ${params.five}"
        params.five.sum() == u(f,params.five)
    }
    def "Testing \'From\'for function of 6"(){
        when: "Created function f(t1..t6) = t1 | t2 | .. | t6"
        def Func6<Integer, Integer, Integer, Integer, Integer, Integer,Integer> f = {t1,t2,t3,t4,t5,t6->return t1 | t2 | t3 | t4 | t5 | t6 }
        then: "Should return true on ${params}"
        params.six.sum() == u(f,params.six)
    }
    def "Testing \'From\' for function of 7"(){
        when: "Created function f(t1..t7) = t1 | t2 | .. | t7"
        def Func7<Integer, Integer, Integer, Integer, Integer, Integer,Integer,Integer> f = {t1,t2,t3,t4,t5,t6,t7->return t1 | t2 | t3 | t4 | t5 | t6 | t7 }
        then: "Should return true on ${params}"
        params.seven.sum() == u(f,params.seven)
    }
    def "Testing \'From\' for function of 8"(){
        when: "Created function f(t1..t8) = t1 | t2 | .. | t8"
        def Func8<Integer, Integer, Integer, Integer, Integer, Integer,Integer,Integer,Integer> f = {t1,t2,t3,t4,t5,t6,t7,t8->return t1 | t2 | t3 | t4 | t5 | t6 | t7 | t8 }
        then: "Should return true on ${params}"
         params.eight.sum() == u(f,params.eight)
    }
    def "Testing \'From\' for function of 9"(){
        when: "Created function f(t1..t9) = t1 | t2 | .. | t9"
        def Func9<Integer, Integer, Integer, Integer, Integer, Integer,Integer,Integer,Integer,Integer> f = {t1,t2,t3,t4,t5,t6,t7,t8,t9->return t1 | t2 | t3 | t4 | t5 | t6 | t7 | t8 | t9}
        then: "Should return true on ${params}"
        params.nine.sum() == u(f,params.nine)
    }

    def "Testing \'From\' for action of 0"(){
        when: "action created and called"
        final  AtomicLong value = new AtomicLong()
        def Action0 a = {value.set(params.zero)}
        g(a,[])
        then: "Should be true"
        params.zero.longValue() == value.get()
    }

    def "Testing \'From\' for action of 1"(){
        when: "action created"
        def AtomicLong value = new AtomicLong()
        def Action1<Integer> a = {t1->value.set(t1)}
        g(a,params.one)
        then: "Should be true"
        params.one.longValue() == value.get()
    }

    def "Testing \'From\' for action of 2"(){
        when: "action created"
        def AtomicLong value = new AtomicLong()
        def Action2<Integer,Integer> a = {t1,t2->value.set(t1|t2)}
        g(a,params.two)
        then: "Should be true"
        params.two.sum().longValue() == value.get()
    }

    def "Testing \'From\' for action of 3"(){
        when: "action created"
        def AtomicLong value = new AtomicLong()
        def Action3<Integer,Integer,Integer> a = {t1,t2,t3->value.set(t1|t2|t3)}
        g(a,params.three)
        then: "Should be true"
        params.three.sum().longValue() == value.get()
    }
}