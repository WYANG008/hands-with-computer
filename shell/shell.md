
# shell脚本基础

------



> 从此运营变得很轻松

shell简介：shell是一种脚本语言，可以使用逻辑判断、循环等语法，可以自定义函数，是系统命令的集合

**Table of Contents** 
    - [shell脚本结构和执行方法](#shell%E8%84%9A%E6%9C%AC%E7%BB%93%E6%9E%84%E5%92%8C%E6%89%A7%E8%A1%8C%E6%96%B9%E6%B3%95)
    - [date命令的用法](#date%E5%91%BD%E4%BB%A4%E7%9A%84%E7%94%A8%E6%B3%95)
    - [shell中变量的用法](#shell%E4%B8%AD%E5%8F%98%E9%87%8F%E7%9A%84%E7%94%A8%E6%B3%95)
    - [shell逻辑判断语法](#shell%E9%80%BB%E8%BE%91%E5%88%A4%E6%96%AD%E8%AF%AD%E6%B3%95)
    - [if判断文件或目录的属性](#if%E5%88%A4%E6%96%AD%E6%96%87%E4%BB%B6%E6%88%96%E7%9B%AE%E5%BD%95%E7%9A%84%E5%B1%9E%E6%80%A7)
    - [if判断的一些特殊用法](#if%E5%88%A4%E6%96%AD%E7%9A%84%E4%B8%80%E4%BA%9B%E7%89%B9%E6%AE%8A%E7%94%A8%E6%B3%95)
    - [shell中case判断用法](#shell%E4%B8%ADcase%E5%88%A4%E6%96%AD%E7%94%A8%E6%B3%95)
    - [for、while循环](#forwhile%E5%BE%AA%E7%8E%AF)
    - [break、continue、exit](#breakcontinueexit)
    - [shell中的函数](#shell%E4%B8%AD%E7%9A%84%E5%87%BD%E6%95%B0)
    - [shell中的数组](#shell%E4%B8%AD%E7%9A%84%E6%95%B0%E7%BB%84)


### shell脚本结构和执行方法

1.shell脚本开头需要加#!/bin/bash
2.以#开头的行作为注释
3.脚本的名字以.sh结尾，用于区分这是一个shell脚本
4.执行方法有两种：
1）bash test.sh
2）./test.sh
\#第二种执行方式需要有执行权限（chmod +x test.sh）
5.查看脚本执行过程：bash -x test.sh
6.查看脚本是否语法错误：bash -n test.sh

\#bash可以使用sh命令代替（在/usr/bin目录下，sh是bash的软连接文件）

### date命令的用法

年月日：

```co
[root@linux ~]# date
2019年 11月 19日 星期二 16:20:04 CST
[root@linux ~]# date +%Y-%m-%d
2019-11-19
[root@linux ~]# date +%Y
2019
[root@linux ~]# date +%m
11
[root@linux ~]# date +%d
19
[root@linux ~]# date +%y    #表示2019年，忽略20
19
[root@linux ~]# date +%F
2019-11-19

1234567891011121314
```

时分秒：

```
[root@linux ~]# date
2019年 11月 19日 星期二 16:27:46 CST
[root@linux ~]# date +%H-%M-%S
16-28-04
[root@linux ~]# date +%T
16:28:09

123456
```

周：

```
[root@linux ~]# date +%w    #表示星期几
2
[root@linux ~]# date +%W    表示今年的第多少周
46

1234
```

时间戳：

```
[root@linux ~]# date +%s    #表示1970年1月1日到现在
1574152197
[root@linux ~]# date -d @1534150197        #倒推时间戳
2018年 08月 13日 星期一 16:49:57 CST

1234
```

补充：

```
[root@linux ~]# date
2019年 11月 19日 星期二 16:34:37 CST

[root@linux ~]# date -d "+3day" #三天后
2019 年 11 月 22 日 星期五 16:34:45 CST
[root@linux ~]# date -d "-3day" +%F #三天前
2019-11-16
[root@linux ~]# date -d "-3 month" +%F #三个月前
2019-08-19
[root@linux ~]# date -d "-30 min" +%F_%T #30 分钟前
2019-11-19_16:06:16

1234567891011121314
```

### shell中变量的用法

**使用变量的情况：**

1.当脚本中使用某个字符串较频繁并且字符串长度很长时就应该使用变量代替
2.使用条件语句时，常使用变量判断大小 if [ $a -gt 1 ]; then … ; fi
3.引用某个命令的结果时，用变量替代 n=wc -l test.txt
4.写和用户交互的脚本时，变量也是必不可少的 ：

```
[root@linux ~]# read -p "input a number:" n
input a number:15
[root@linux ~]# echo $n
15

1234
```

如果没有自定义变量，可以使用内置变量$REPLY：

```
[root@linux ~]# read -p "input a number:" 
input a number:9
[root@linux ~]# echo $REPLY
9

1234
```

5.内置变量

$1,$2,$3,$# （$1 第一个参数，$2 第二个参数，$#表示参数的和）：

```shell
#!/bin/bash
echo "第一个参数是$1"
echo "第二个参数是$2"
echo "一共有$#个参数"

1234
```

结果示例：

```
[root@linux ~]# ./test.sh a b c
第一个参数是a
第二个参数是b
一共有3个参数

1234
```

$0表示脚本名（根据执行脚本方式的不同结果不同）：

```shell
#!/bin/bash
echo "\$0是：$0"

12
```

结果示例：

```
[root@linux ~]# /root/test.sh 
$0是：/root/test.sh
[root@linux ~]# ./test.sh 
$0是：./test.sh
[root@linux ~]# sh test.sh 
$0是：test.sh

123456
```

6.数学运算a=1;b=2; c=$(($a+$b))或者$[$a+$b]：

```
[root@linux ~]# a=1
[root@linux ~]# b=2

[root@linux ~]# c=$(($a+$b))
[root@linux ~]# echo $c
3
[root@linux ~]# d=$[$a+$b]
[root@linux ~]# echo $d
3

123456789
```

### shell逻辑判断语法

格式1：if 条件 ; then 语句; fi

```shell
#!/bin/bash
read -p "input a number:" n
if [ $n -gt 5 ]
then
    echo "1"
fi

123456
```

格式2：if 条件; then 语句; else 语句; fi

```shell
#!/bin/bash
read -p "input a number:" n
if [ $n -gt 5 ]
then
    echo "1"
else
    echo "2"
fi

12345678
```

格式3：if …; then … ;elif …; then …; else …; fi

```
#!/bin/bash
read -p "input a number:" n
if [ $n -gt 90 ]
then
    echo "A"
elif [ $n -gt 70 ]
then
    echo "B"
else
    echo "C"
fi

1234567891011
```

格式4：if 中嵌套 if

```shell
#!/bin/bash
read -p "input a number:" n
if [ $n -gt 70 ]
then
    echo "OK"
    if [ $n -gt 90 ]
    then
         echo "A"
    elif [ $n -gt 80 ]
    then
     echo "B"
    else
     echo "C"
    fi
else
    echo "???"
fi

1234567891011121314151617
```

**补充：**
-gt 大于（>）
-lt 小于 （<）
-ge 大于等于 （>=）
-le 小于等于 （<=）
-eq 等于 （==）
-ne 不等于 （!=）

可以使用 && || 结合多个条件

大于5并且小于10：

```shell
if [ $a -gt 5 ] && [ $a -lt 10 ]; then

1
```

第二种写法：

```shell
if [ $a -gt 5 -a $a -lt 10 ]; then

1
```

\#-a表示：and

大于5或小于3：

```shell
if [ $b -gt 5 ] || [ $b -lt 3 ]; then

1
```

第二种写法：

```shell
if [ $b -gt 5 -o $b -lt 3 ]; then

1
```

\#-o表示or

### if判断文件或目录的属性

[ -f file ] 判断是否是普通文件，且存在
[ -d file ] 判断是否是目录，且存在
[ -e file ] 判断文件或目录是否存在
[ -r file ] 判断文件是否可读
[ -w file ] 判断文件是否可写
[ -x file ] 判断文件是否可执行

**补充：**
1.如果判断对象为socket等特殊文件，可以使用-e判断是否存在

2.root用户对文件的读写比较特殊，即使一个文件没有给root用户读或者写的权限，root用户照样可以读或者写（x：执行权限除外）

3.取反使用感叹号：

```shell
if [ ! -f filename ]

1
```

### if判断的一些特殊用法

判断变量为空时：

```shell
if [ -z "$a" ]; then ...

1
```

判断变量不为空时：

```shell
if [ -n "$a" ]; then ...

1
```

判断命令成功时：

```shell
if ls /tmp/test.sh; then ...

1
```

\#判断文件是否存在的用法，当ls命令执行成功时，要怎样…

将产生的信息重定向到/dev/null：

```shell
if ls /tmp/test.sh &> /dev/null; then ...

1
```

\#不管ls命令执行是否成功，都将输出的信息流写入到/dev/null，不打印

判断文件是否包含关键字：

```shell
if grep -q '123' /tmp/test.sh; then ...

1
```

\#当文件包含关键字时，要怎样…，-q：包含关键字时，不输出匹配到的信息

if [ $a -gt 5 ]的另一种写法：

```shell
if (($a>5)); then ...

1
```

\#[ ] 中不能使用<,>,==,!=,>=,<=这样的符号，但双括号中可以

### shell中case判断用法

```shell
case  变量名 in 
   value1)    
        command  
        ;;
   value2)      
        command  
        ;; 
   *)  
        commond 
        ;;     
     esac

在 case 中，可以在条件中使用|，表示或的意思：
2|3)
 command
;;

12345678910111213141516
```

示例：

```shell
#!/bin/bash
read -p "input a number: " n
#判断输入内容是否为空
if [ -z "$n" ]
then
    echo "Please input a number."
    exit 1
fi
#判断输入内容是否含有非数字
n1=`echo $n|sed 's/[0-9]//g'`
if [ -n "$n1" ]
then
 echo "Please input a number."
 exit 1
fi
#当输入内容非空且为纯数字时，开始判断
if [ $n -lt 60 ] && [ $n -ge 0 ]
then
    t=1
elif [ $n -ge 60 ] && [ $n -lt 80 ]
then
    t=2
elif [ $n -ge 80 ]  && [ $n -lt 90 ]
then
    t=3
elif [ $n -ge 90 ] && [ $n -le 100 ]
then
    t=4
else 
    t=0
fi

case $t in
1)
echo "D"
;;
2)
echo "C"
;;
3)
echo "B"
;;
4)
echo "A"
;; *)
echo "The number range must be 0-100."
;;
esac

12345678910111213141516171819202122232425262728293031323334353637383940414243444546474849
```

### for、while循环

for循环语法：for 变量名 in 条件; do …; done

示例一：打印1-100的数字并求和

```shell
#!/bin/bash
sum=0
for i in `seq 1 100`
do
    sum=$[$sum+$i]
    echo $i
done
echo $sum

12345678
```

seq1 2 10：打印结果1,3,5,7,9（2表示步长，打印结果间隔为2）
seq10 -2 1：打印结果10,8,6,4,2（倒序打印步长为负）
seq -w 1 10：打印结果01,02,03,04,05,06,07,08,09,10（-w 表示等宽）

示例二：打印指定目录下的文件

```shell
#!/bin/bash
cd /etc
for i in `ls /etc`
do
    if [ -f $i ]
    then
        ls -l $i
    fi
done

123456789
```

while循环语法：while 条件; do … ; done

示例一：平均1分钟负载大于5时，发送邮件

```shell
#!/bin/bash
while :
do
        load=`w|head -1|awk -F 'load average: ' '{print $2}'|cut -d . -f1`
        if [ $load -gt 5 ]
        then
                d=`date +%F_%T`
                top -nb1|mail -s "$d load is high: $load" asnfy@qq.com
        fi
        sleep 30
done

1234567891011
```

示例二：判断输入内容是否为空或包含非数字

```shell
#!/bin/bash
while :
do
    read -p "Please input a number: " n
    if [ -z "$n" ]
    then
        echo "you need input sth."
        continue
    fi
    n1=`echo $n|sed 's/[0-9]//g'`
    if [ -n "$n1" ]
    then
        echo "you just only input numbers."
        continue
    fi
    break
done
echo "you input number is $n"

123456789101112131415161718
```

\#如果输入的内容为空为包含非数字，continue跳出重新执行，否则break结束循环

**补充：**
当使用for遍历一个文件中的每行内容时（for i in `cat test`），如果某行中有一个或多个空格，这行的内容就会被当做两行或多行打印，为了避免这种情况可以使用while来循环输出文件中的每行内容
示例：

```shell
#!/bin/bash
cat test.txt|while read i
do
    echo $i
done

12345
```

\#使用while遍历文件中的内容，即使某行有空格，也会被正常打印

### break、continue、exit

break：直接结束循环

```shell
#!/bin/bash
for i in `seq 1 5`
do
    echo "第一次输出：$i"
    if(($i==3))
    then
        break
    fi
    echo "第二次输出：$i"
done
echo "循环结束"

1234567891011
```

执行结果：

```shell
[root@linux ~]# sh a.sh 
第一次输出：1
第二次输出：1
第一次输出：2
第二次输出：2
第一次输出：3
循环结束

1234567
```

continue：忽略后面的代码，从新开始循环

```shell
#!/bin/bash
for i in `seq 1 5`
do
    echo "第一次输出：$i"
    if(($i==3))
    then
        continue
    fi
    echo "第二次输出：$i"
done
echo "循环结束"

1234567891011
```

执行结果：

```shell
[root@linux ~]# sh a.sh 
第一次输出：1
第二次输出：1
第一次输出：2
第二次输出：2
第一次输出：3
第一次输出：4
第二次输出：4
第一次输出：5
第二次输出：5
循环结束

1234567891011
```

**注意：循环支持嵌套，如果一个循环中嵌套了多个循环，break或continue只能作用于所在的某个循环中**

exit：直接退出脚本

```shell
#!/bin/bash
for i in `seq 1 5`
do
    echo "第一次输出：$i"
    if(($i==3))
    then
        exit    
    fi
    echo "第二次输出：$i"
done
echo "循环结束"

1234567891011
```

执行结果：

```shell
[root@linux ~]# sh a.sh 
第一次输出：1
第二次输出：1
第一次输出：2
第二次输出：2
第一次输出：3

123456
```

\#循环外的echo语句没有输出，当脚本中遇到exit，会直接退出，后面所有内容都不再执行

**补充：**
当脚本遇到exit直接退出后，echo $? 查看执行结果会显示0，表示执行成功，所以一般使用exit会在后面加上指定的非0数字，方便调用结果（比如：exit 5）

### shell中的函数

函数（也叫方法）就是把一段代码整理到了一个小单元中，并给这个小单元起一个名字，当用到这段代码时直接调用这个小单元的名字即可

格式：

```shell
function f_name() 
{ 
    command
}

1234
```

示例一：调用该函数，输出函数的第一、第二个参数以及参数总数

```shell
#!/bin/bash
function test()
{
    echo $1 $2 $#
}
test a b c

123456
```

执行结果：

```shell
[root@linux ~]# sh fun1.sh 
a b 3

12
```

示例二：将两个参数求和

```shell
#!/bin/bash
function test()
{
    n=$[$1+$2]
    echo $n
}
test 1 2

1234567
```

执行结果：

```shell
[root@linux ~]# sh fun2.sh 
3

12
```

示例三：输出指定网卡的IP地址

```shell
#!/bin/bash
function ip()
{
    ifconfig |grep -A1 "$1:" |tail -1 |awk '{print $2}'
}
read -p "Please input the eth name: " e
eth=`ip $e`
echo "$e address is $eth"

12345678
```

执行结果：

```shell
[root@linux ~]# sh fun3.sh 
Please input the eth name: ens33
ens33 address is 192.168.234.128

123
```

**补充：**
1.函数在脚本中需要放在最前面
2.定义函数时允许只写函数名，可以忽略掉function

### shell中的数组

数组可以理解为变量，与普通变量不同的是，数组可以定义多个值，把每个值叫做元素

定义一个数组：

```shell
[root@linux ~]# array=(a b c)

1
```

\#数组名：array，包含3个元素：a,b,c

获取数组的第一个元素：

```shell
[root@linux ~]# echo ${array[0]}
a

12
```

获取数组的第二个元素：

```shell
[root@linux ~]# echo ${array[2]}
c

12
```

\#元素的排序是以0开始的：0代表第一个元素，1代表第二个元素，以此类推…

获取数组所有元素的两种方式：

```
[root@linux ~]# echo ${array[@]}
a b c
[root@linux ~]# echo ${array[*]}
a b c

1234
```

获取数组元素个数的两种方式：

```
[root@linux ~]# echo ${#array[*]}
3
[root@linux ~]# echo ${#array[@]}
3

1234
```

修改第一个元素的值：

```
[root@linux ~]# array[0]=x
[root@linux ~]# echo ${array[@]}
x b c

123
```

增减一个元素：

```
[root@linux ~]# array[3]=y
[root@linux ~]# echo ${array[@]}
x b c y

123
```

删除第一个元素：

```
[root@linux ~]# unset array[0]
[root@linux ~]# echo ${array[@]}
b c y

123
```

删除数组：

```
[root@linux ~]# unset array

1
```

**数组的分片：**

添加一个数组：

```
[root@linux ~]# array=(`seq 1 5`)
[root@linux ~]# echo ${array[@]}
1 2 3 4 5

123
```

从第1个元素开始截取3个元素：

```
[root@linux ~]# echo ${array[@]:0:3}
1 2 3

12
```

\#0表示第一个元素，3表示截取元素的数量

从第4个元素开始截取2个元素：

```
[root@linux ~]# echo ${array[@]:3:2}
4 5

12
```

\#3表示第四个元素，2表示截取元素的数量

从倒数第3个元素开始截取3个元素：

```
[root@linux ~]# echo ${array[@]:0-3:3}
3 4 5

12
```

\#0-3表示倒数第三个元素，3表示截取元素的数量

**数组的替换：**

将数组中值为3的元素替换为33：

```
[root@linux ~]# echo ${array[@]/3/33}
1 2 33 4 5
[root@linux ~]# echo ${array[@]}
1 2 3 4 5

1234
```

\#该操作只是替换输出的结果，并没有真正替换数组中的元素

替换数组中的元素：

```
[root@linux ~]# array=(${array[@]/5/55})
[root@linux ~]# echo ${array[@]}
1 2 3 4 55

123
```

\#该操作是直接替换数值中的值，而不是只替换输出结果

​                           