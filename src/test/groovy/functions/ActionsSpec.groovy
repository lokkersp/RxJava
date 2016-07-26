package functions

import rx.functions.*
import spock.lang.Specification

/**
 * Created by noctuam on 26.07.16.
 */
class ActionsSpec extends Specification {
    def "Testing empty arities"(){
        given:"Actions"
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
        when: "Actions called"
        a0()
        a1(1)
        a2(1,2)
        a3(1,2,3)
    }
}