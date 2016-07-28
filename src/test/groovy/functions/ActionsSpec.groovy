package functions

import rx.TestUtil
import rx.functions.*
import spock.lang.Specification

import javax.xml.soap.SAAJMetaFactory
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
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        0 == Actions.toFunc(a,0).call(1)
        and: "Finally AtomicLong value equals 1"
        1 == value.get()
    }
    def "Testing action -> func : 2"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action2<Integer,Integer> a = {t1,t2->value.set(t1|t2)}
        def testSum = params.two.sum()
        def onParams = params.two

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }
    def "Testing action -> func : 3"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action3<Integer,Integer,Integer> a = {t1,t2,t3->value.set(t1|t2|t3)}
        def testSum = params.three.sum()
        def onParams = params.three

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }
    def "Testing action -> func : 4"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action4<Integer,Integer,Integer,Integer> a = {t1,t2,t3,t4->value.set(t1|t2|t3|t4)}
        def testSum = params.four.sum()
        def onParams = params.four

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }
    def "Testing action -> func : 5"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action5<Integer,Integer,Integer,Integer,Integer> a = {t1,t2,t3,t4,t5->value.set(t1|t2|t3|t4|t5)}
        def testSum = params.five.sum()
        def onParams = params.five

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }
    def "Testing action -> func : 6"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action6<Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6->value.set(t1|t2|t3|t4|t5|t6)}
        def testSum = params.six.sum()
        def onParams = params.six

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }
    def "Testing action -> func : 7"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action7<Integer,Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6,t7->value.set(t1|t2|t3|t4|t5|t6|t7)}
        def testSum = params.seven.sum()
        def onParams = params.seven

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }
    def "Testing action -> func : 8"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action8<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6,t7,t8->value.set(t1|t2|t3|t4|t5|t6|t7|t8)}
        def testSum = params.eight.sum()
        def onParams = params.eight

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }
    def "Testing action -> func : 9"() {
        given: "Atomic Long and actions"
        AtomicLong value = new AtomicLong(-1L)
        Action9<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> a= {t1,t2,t3,t4,t5,t6,t7,t8,t9->value.set(t1|t2|t3|t4|t5|t6|t7|t8|t9)}
        def testSum = params.nine.sum()
        def onParams = params.nine

        when: "Action converted to func"
        def f = Actions.toFunc(a)
        then: "It's null out after call"
        null == f(onParams)
        and: "ActomicLong has ${testSum} after action call"
        testSum == value.get()
        and: "After setting to Atomic -1 and using convert method m(a,r) we take 0"
        value.set(-1L)
        0 == Actions.toFunc(a,0).call(onParams)
        and: "Finally we have ${testSum} in AtomicLong"
        testSum == value.get()
    }

    Object[] createAndFillOnes(int i){
       Object[] res = new Object[i]
        Arrays.fill(res,1)
        return res
    }

    def "Testing action -> func : N(multiple times)"(){
        expect:
        final AtomicLong value = new AtomicLong(-1L)
        def ActionN act = {args->
            def sum = 0
            for(o in args) sum +=o
            value.set(sum)
        }

        Object[] arr = createAndFillOnes(i)
        assert null == Actions.toFunc(act).call(arr)
        assert  i == value.get()
        value.set(-1L)
        assert  0 == Actions.toFunc(act,0).call(arr)
        assert  i == value.get()
        where:
        i << (0..99)
    }

    def "It's should be private"(){
        expect:
        TestUtil.checkUtilityClass(Actions.class)
    }
}