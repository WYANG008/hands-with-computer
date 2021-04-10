import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsefulKnowledge {

    /**
    1. switch必须加上break才结束
    */
    public static void example1() {
        int count = 1;
        switch(count){
            case 1:
            System.out.println("one");
            case 2:
            System.out.println("two");
            case 3:
            System.out.println("three");
        }
        /**
         output:
         ----------
         one
         two
         three
         */
    }

    /**
    2. 逻辑运算符的“短路”现象
    */
    public static void example2() {

        /**
         2.1. 逻辑运算符的“短路”现象
         */
        int num1 = 1;
        System.out.println(false && ((num1++)==1));
        System.out.println(num1);

        /**
         output:
         ----------
         false
         1
         */

        /**
         * 2.2. 逻辑运算符的“短路”现象  pre-increment
         */
        int num2 = 1;
        System.out.println(true && ((++num2)==1));
        System.out.println(num2);

        /**
         output:
         ----------
         false
         2
         */

        /**
         * 2.2. 逻辑运算符的“短路”现象  post-increment
         */
        int num3 = 1;
        System.out.println(true && ((num3++)==1));
        System.out.println(num3);

        /**
         output:
         ----------
         true
         2
         */
    }

    /**
    3. ArrayList遍历删除时报错
    */
    public static void example3() {

        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("bc");
        list.add("bc");
        list.add("abcd");
        list.add("bc");
        list.add("abcdef");


        /**
         * 3.1. 抛出了IndexOutOfBoundsException异常，
         * 原因是ArrayList在删除元素后会重新计算数量，把list.size放在for循环中即可
         */
        int length = list.size();
        for(int i = 0;i < length;i++){
            if(list.get(i).equals("bc")){
                list.remove(i);
            }
        }

        /**
         * 3.2.
         * 把list.size放在for循环中即可, 当然，这种方法也存在问题，建议使用迭代器的方素。
         */
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
            if(list.get(i).equals("bc")){
                list.remove(i);
            }
        }
        System.out.println(list);


        /**
         * 3.3.
         * 迭代器
         */
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("bc")) {
                iterator.remove();//使用迭代器的删除方法删除
            }
        }
        System.out.println(list);


    }

    /**
     * 4 字符转成数字的坑
     * ASCII
     */
    public static void example4() {
        char symbol = '8';
        System.out.println((int) symbol);
    }

    /**
     * 5 while循环体的“障眼法”
     * 对于while循环语句，如果你没有加上大括号，即使后面的语句挨在一起，也只会执行第一条statement
     * @output AAAB
     */
    public static void example5() {
        int i = 0;
        while(i++<3)
            System.out.print("A");System.out.print("B");
    }

    /**
     * 6 Integer类有缓存
     * @output
     * true
     * false
     *
     * Integer中有一个静态内部类IntegerCache，在类加载的时候，它会把[-128, 127]之间的值缓存起来，
     * 而Integer a = 100这样的赋值方式，会首先调用Integer类中的静态valueOf方法，这个方法会尝试从缓存里取值，
     * 如果在这个范围之内就不用重新new一个对象了
     */
    public static void example6() {
        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;

        System.out.println(a==b);
        System.out.println(c==d);  // c和d的地址不一样, 因此为false
        System.out.println(c.equals(d));
    }

    /** integer cache
    public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
    */

    /**
     * 7 神奇的=+
     * @output
     * 2
     *
     * 下面的代码既不会输出102，也不会报错，而是输出2
     */
    public static void example7() {
        int i = 100;
        i =+ 2;  //注意，加号在后面
        System.out.println(i);
    }

    /**
     * 8 Java注释能够识别Unicode
     * @output
     * Hello World!
     *
     * unicode解码发生在代码编译之前，编译器将\u样式的代码进行文本转义，即使是注释也是这样，然后\u000d被转换成\n换行符，所以println代码得以正常执行。
     */
    public static void example8() {
        // \u000d System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        // example1();
        // example2();
        // example3();
        //example4();
        //example5();
        //example6();
        //example7();
        //example8();

    }

}

