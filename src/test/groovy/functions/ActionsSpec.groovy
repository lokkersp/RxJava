package functions

import rx.functions.*
import spock.lang.Specification

import java.util.concurrent.atomic.AtomicLong

/**
 * Created by noctuam on 26.07.16.
 */
class ActionsSpec extends Specification {
    def W = {Action a,p-> Actions.toFunc(a).call(p)}
    def params = [zero:0,one:1,two:[1,2],three:[1,2,4],four:[1,2,4,8],five:[1,2,4,8,16],six:[1,2,4,8,16,32],seven:[1,2,4,8,16,32,64],eight:[1,2,4,8,16,32,64,128],nine:[1,2,4,8,16,32,64,128,256]]

    def "Testing empty arities"(){
        when:"Actions"
        Action0 a0 = Actions.empty()
        Action1<Integer> a1 = Actions.empty()
        Action2<Integer,Integer> a2 = Actions.empty()
        Action3<Integer,Integer,Integer> a3 = Actions.empty()
        Action4<Integer,Integer,Integer,Integer> a4 = Actions.empty()
        Action5<Integer,Integer,Integer,Integer,Integer> a5 = Actions.empty()
        Action6<Integer,Integer,Integer,Integer,Integer,Integer> a6 = Actions.empty()
        Action7<Integer,Integer,Integer,Integer,Integer,Integer,Integer> a7 = Actions.empty()
        Action8<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> a8 = Actions.empty()
        Action9<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> a9 = Actions.empty()
        ActionN an0 = Actions.empty()
        ActionN an1 = Actions.empty()
        ActionN ann = Actions.empty()
        ActionN annn = Actions.empty()
        a0()
        a1(1)
        a2(1,2)
        a3(1,2,3)
        a4(1,2,3,4)
        a5(1,2,3,4,5)
        a6(1,2,3,4,5,6)
        a7(1,2,3,4,5,6,7)
        a8(1,2,3,4,5,6,7,8)
        a9(1,2,3,4,5,6,7,8,9)
        an0()
        an1(1)
        ann(1,2,3,4,5,6,7,8,9,10)
        annn(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        then: "They aren't failed"


    }

    def "Testing action -> func : 0"(){
       given: "Atomic Long and actions"
       AtomicLong value = new AtomicLong(-1L)
       Action0 a = {value.set(0)}
        when: "Converting Action to Func"
        def f = Actions.toFunc(a)
        then: "it null"
        null == f()
        and: "Atomic contains zero"
        0 == value.get().intValue()
        and: "When we set -1 to atomic and call action one more time it's 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call()
        and: "Atomic has zero"
        0 == value.get().intValue()
    }
    def "Testing action -> func : 1"() {
        given: "AtomicLong and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action1<Integer> a = {t1-> value.set(t1)}
        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(params.one)
        and: "After calling action AtomicLong has 1"
        1 == value.get()
        and: "After setting to Atomic -1 and using convert method we take 0"
        0 == Actions.toFunc(a,0).call(1)
        and: "Finally AtomicLong value equals 1"
        1 == value.get()
    }
    def "Testing action -> func : 2"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action2<Integer,Integer> a = {t1,t2->value.set(t1|t2)}
    }
    def "Testing action -> func : 3"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action3<Integer,Integer,Integer> a = {t1,t2,t3->value.set(t1|t2|t3)}
    }
    def "Testing action -> func : 4"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action4<Integer,Integer,Integer,Integer> a = {t1,t2,t3,t4->value.set(t1|t2|t3|t4)}
    }
    def "Testing action -> func : 5"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action5<Integer,Integer,Integer,Integer,Integer> a = {t1,t2,t3,t4,t5->value.set(t1|t2|t3|t4|t5)}
    }
    def "Testing action -> func : 6"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action6<Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6->value.set(t1|t2|t3|t4|t5|t6)}
    }
    def "Testing action -> func : 7"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action7<Integer,Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6,t7->value.set(t1|t2|t3|t4|t5|t6|t7)}
    }
    def "Testing action -> func : 8"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action8<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6,t7,t8->value.set(t1|t2|t3|t4|t5|t6|t7|t8)}
    }
    def "Testing action -> func : 9"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action9<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6,t7,t8,t9->value.set(t1|t2|t3|t4|t5|t6|t7|t8|t9)}
    }
}