# 特别有用的git进阶命令

> 你了解git的力量吗



### 1. git stash save/pop

------

> git stash save -u 'let me take a break'

**实用度**

★★★★★

**作用**
把写到一半的代码暂存起来。<code>-u</code>参数表示不仅是修改的文件，连同新增的文件也一并保存。最后还可以加上一段注释，以便日后回忆暂存的这些代码的作用。这条命令可以简化为 <code>git stash -u</code>，只是这样就没法添加注释了

> git stash pop

**作用**
取出最近一条 stash 记录，并将其从 stash 记录列表中删除。和 <code>git stash -u</code> 结合使用，一般准没错

> git stash list

**作用**
列出所有 stash 记录。上面如果在 <code>git stash</code> 时输入了注释的话，这时候就可以看到。

> git stash apply

**作用**
和 <code>git stash pop</code> 基本相同，不过不会把 stash 记录从列表中删除，也就是说可以多次 <code>git apply</code> 同一条 stash 记录。主要用于你不确定最近的一条 stash 记录是否正确时。


### 2. git reflog

------
**实用度**

★★★★
**作用**
列出某个指针的所有移动记录。指针包括 <code>HEAD</code>，branch，tag 等。“移动“包括 <em>commit</em>, <em>checkout</em>, <em>pull</em> 等。用文字很难描述，还是给一张图吧
![alt text](../blob/master/img/git-reflog.png)

![](/Users/ali-220237n/dev/hands-with-computer/img/git-reflog.png)

![git-reflog](/Users/ali-220237n/dev/hands-with-computer/img/git-reflog.png)