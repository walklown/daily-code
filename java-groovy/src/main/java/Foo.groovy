package main.java

class Foo implements IFoo {
    def x
    public Object run(Object foo, Object bar) {
        println 'Hello World!'
        x = 123
        println foo * 10
        println bar
        return 'success'
    }
}
